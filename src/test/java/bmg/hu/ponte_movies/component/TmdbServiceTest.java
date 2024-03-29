package bmg.hu.ponte_movies.component;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.dto.Pagination;
import bmg.hu.ponte_movies.exception.IllegalTmdbRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TmdbServiceTest {

    @Value("${tmdb.api_baseUrl}")
    private String apiBaseUrl;

    @Value("${tmdb.api_key}")
    private String apiKey;

    private TmdbService tmdbService;

    @BeforeEach
    void init() {
        tmdbService = new TmdbService();
    }

    @Test
    void testGetTopRatedMovies() {
        // Given
        ReflectionTestUtils.setField(tmdbService, "apiBaseUrl", apiBaseUrl);
        ReflectionTestUtils.setField(tmdbService, "apiKey", apiKey);

        // When
        Pagination pagination = tmdbService.getTopRatedMovies(2);

        // Then
        assertFalse(pagination.getMovieListItemList().isEmpty());

    }

    @Test
    void testGetTopRatedMoviesThrowsException() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalTmdbRequestException.class,
                () -> tmdbService.getTopRatedMovies(1));
        assertEquals("no protocol: null/top_rated?language=en-US&page=1&api_key=null", exception.getMessage());

    }

    @Test
    void testGetActorsForMovie() {
        // Given
        ReflectionTestUtils.setField(tmdbService, "apiBaseUrl", apiBaseUrl);
        ReflectionTestUtils.setField(tmdbService, "apiKey", apiKey);

        Pagination pagination = tmdbService.getTopRatedMovies(1);

        // When
        tmdbService.getActorsForMovie(pagination.getMovieListItemList().get(0));

        // Then
        assertFalse(pagination.getMovieListItemList().get(0).getCast().isEmpty());

    }

    @Test
    void testGetActorsForMovieThrowsException() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalTmdbRequestException.class,
                () -> tmdbService.getActorsForMovie(new MovieListItem()));
        assertEquals("no protocol: null/null/credits?language=en-US&api_key=null", exception.getMessage());

    }

    @Test
    void testGetProductionCompaniesForMovie() {
        // Given
        ReflectionTestUtils.setField(tmdbService, "apiBaseUrl", apiBaseUrl);
        ReflectionTestUtils.setField(tmdbService, "apiKey", apiKey);

        Pagination pagination = tmdbService.getTopRatedMovies(2);

        // When
        tmdbService.getProductionCompaniesForMovie(pagination.getMovieListItemList().get(0));

        // Then
        assertFalse(pagination.getMovieListItemList().get(0).getProductionCompanies().isEmpty());

    }

    @Test
    void testGetProductionCompaniesForMovieThrowsException() {
        // Given

        // When

        // Then
        Exception exception = assertThrows(IllegalTmdbRequestException.class,
                () -> tmdbService.getProductionCompaniesForMovie(new MovieListItem()));
        assertEquals("no protocol: null/null?language=en-US&api_key=null", exception.getMessage());

    }

    @Test
    void testGetImagesForMovie() {
        // Given
        ReflectionTestUtils.setField(tmdbService, "apiBaseUrl", apiBaseUrl);
        ReflectionTestUtils.setField(tmdbService, "apiKey", apiKey);

        Pagination pagination = tmdbService.getTopRatedMovies(1);

        // When
        tmdbService.getImagesForMovie(pagination.getMovieListItemList().get(0));

        // Then
        assertFalse(pagination.getMovieListItemList().get(0).getImages().isEmpty());

    }


    @Test
    void testGetImagesForMovieThrowsException() {
        // Given

        // When


        // Then
        Exception exception = assertThrows(IllegalTmdbRequestException.class,
                () -> tmdbService.getImagesForMovie(new MovieListItem()));
        assertEquals("no protocol: null/null/images?api_key=null", exception.getMessage());

    }

}
