package bmg.hu.ponte_movies.service;

import bmg.hu.ponte_movies.component.TmdbService;
import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.dto.Pagination;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration
class MovieServiceTest {

    private MovieService movieService;

    @Mock
    private TmdbService tmdbServiceMock;


    @BeforeEach
    public void setup() {
        movieService = new MovieService(tmdbServiceMock);
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testFindMovies() {
        //given
        MovieListItem item1 = new MovieListItem();
        item1.setMovieId(100L);
        item1.setTitle("Star Wars IV");
        item1.setReleaseDate("1980-01-01");
        item1.setOverView("A new hope");
        item1.setOriginalLanguage("en");
        item1.setPosterPath("/xxx.jpg");

        MovieListItem item2 = new MovieListItem();
        item2.setMovieId(200L);
        item2.setTitle("Star Wars VII");
        item2.setReleaseDate("2010-01-01");
        item2.setOverView("The force awakens");
        item2.setOriginalLanguage("en");
        item2.setPosterPath("/yyy.jpg");

        Pagination pagination = new Pagination();
        pagination.setMovieListItemList(List.of(item1, item2));
        pagination.setTotal(100L);


        when(tmdbServiceMock.getTopRatedMovies(1)).thenReturn(pagination);

        item1.setCast(List.of("Mark Hamill", "Harrison Ford"));
        item2.setCast(List.of("Adam Driver", "Daisy Ridley"));

        doNothing().when(tmdbServiceMock).getActorsForMovie(any(MovieListItem.class));

        item1.setProductionCompanies(List.of("20 Century fox", "Visage studio"));
        item2.setProductionCompanies(List.of("Paramount", "Bad Robot"));

        doNothing().when(tmdbServiceMock).getProductionCompaniesForMovie(any(MovieListItem.class));

        item1.setImages(List.of("/aaa.jpg", "/bbb.jpg"));
        item2.setImages(List.of("/ccc.jpg", "/ddd.jpg"));

        doNothing().when(tmdbServiceMock).getImagesForMovie(any(MovieListItem.class));


        //when
        Pagination paginationResult = movieService.findMovies(1);

        //then
        assertEquals(2, paginationResult.getMovieListItemList().size());
        assertEquals("Star Wars IV", paginationResult.getMovieListItemList().get(0).getTitle());
        assertEquals("Star Wars VII", paginationResult.getMovieListItemList().get(1).getTitle());
        assertFalse(paginationResult.getMovieListItemList().get(0).getCast().isEmpty());
        assertFalse(paginationResult.getMovieListItemList().get(1).getCast().isEmpty());
        assertFalse(paginationResult.getMovieListItemList().get(0).getProductionCompanies().isEmpty());
        assertFalse(paginationResult.getMovieListItemList().get(1).getProductionCompanies().isEmpty());
        assertFalse(paginationResult.getMovieListItemList().get(0).getImages().isEmpty());
        assertFalse(paginationResult.getMovieListItemList().get(1).getImages().isEmpty());


        verify(tmdbServiceMock, times(1)).getTopRatedMovies(1);
        verify(tmdbServiceMock, times(2)).getActorsForMovie(any(MovieListItem.class));
        verify(tmdbServiceMock, times(2)).getProductionCompaniesForMovie(any(MovieListItem.class));
        verify(tmdbServiceMock, times(2)).getImagesForMovie(any(MovieListItem.class));
        verifyNoMoreInteractions(tmdbServiceMock);

    }



}
