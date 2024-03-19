package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.dto.Pagination;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.exception.GlobalExceptionHandler;
import bmg.hu.ponte_movies.service.MovieService;
import bmg.hu.ponte_movies.service.RatingService;
import bmg.hu.ponte_movies.validator.RatingCreationCommandValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    private MockMvc mockMvc;
    @Mock
    private MovieService movieServiceMock;


    @BeforeEach
    public void setUp() {
        MovieController movieController =
                new MovieController(movieServiceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setControllerAdvice(new GlobalExceptionHandler(messageSource()))
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testGetMovies() throws Exception {
        //given
        MovieListItem item1 = new MovieListItem();
        item1.setMovieId(100L);
        item1.setTitle("Star Wars IV");
        item1.setReleaseDate("1980-01-01");
        item1.setOverView("A new hope");
        item1.setOriginalLanguage("en");
        item1.setPosterPath("/xxx.jpg");
        item1.setCast(List.of("Mark Hamill", "Harrison Ford"));
        item1.setProductionCompanies(List.of("20 Century fox", "Visage studio"));
        item1.setImages(List.of("/aaa.jpg", "/bbb.jpg"));

        MovieListItem item2 = new MovieListItem();
        item2.setMovieId(200L);
        item2.setTitle("Star Wars VII");
        item2.setReleaseDate("2010-01-01");
        item2.setOverView("The force awakens");
        item2.setOriginalLanguage("en");
        item2.setPosterPath("/yyy.jpg");
        item2.setCast(List.of("Adam Driver", "Daisy Ridley"));
        item2.setProductionCompanies(List.of("Paramount", "Bad Robot"));
        item2.setImages(List.of("/ccc.jpg", "/ddd.jpg"));

        Pagination pagination = new Pagination();
        pagination.setMovieListItemList(List.of(item1, item2));
        pagination.setTotal(100L);

        when(movieServiceMock.findMovies(1)).thenReturn(pagination);

        //when
        this.mockMvc.perform(get("/api/movies?page=1"))

                //then
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieListItemList", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[0].movieId", is(100)))
                .andExpect(jsonPath("$.movieListItemList[0].title", is("Star Wars IV")))
                .andExpect(jsonPath("$.movieListItemList[0].releaseDate", is("1980-01-01")))
                .andExpect(jsonPath("$.movieListItemList[0].overView", is("A new hope")))
                .andExpect(jsonPath("$.movieListItemList[0].originalLanguage", is("en")))
                .andExpect(jsonPath("$.movieListItemList[0].posterPath", is("/xxx.jpg")))
                .andExpect(jsonPath("$.movieListItemList[0].cast", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[0].productionCompanies", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[0].images", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[1].movieId", is(200)))
                .andExpect(jsonPath("$.movieListItemList[1].title", is("Star Wars VII")))
                .andExpect(jsonPath("$.movieListItemList[1].releaseDate", is("2010-01-01")))
                .andExpect(jsonPath("$.movieListItemList[1].overView", is("The force awakens")))
                .andExpect(jsonPath("$.movieListItemList[1].originalLanguage", is("en")))
                .andExpect(jsonPath("$.movieListItemList[1].posterPath", is("/yyy.jpg")))
                .andExpect(jsonPath("$.movieListItemList[1].cast", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[1].productionCompanies", hasSize(2)))
                .andExpect(jsonPath("$.movieListItemList[1].images", hasSize(2)))
                .andExpect(jsonPath("$.total", is(100)));


        verify(movieServiceMock, times(1)).findMovies(1);
        verifyNoMoreInteractions(movieServiceMock);

    }


    private MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
