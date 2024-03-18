package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.domain.Rating;
import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.exception.GlobalExceptionHandler;
import bmg.hu.ponte_movies.repository.RatingRepository;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    private MockMvc mockMvc;
    @Mock
    private RatingService ratingServiceMock;


    @BeforeEach
    public void setUp() {
        RatingCreationCommandValidator ratingCreationCommandValidator =
                new RatingCreationCommandValidator();

        RatingController ratingController =
                new RatingController(ratingServiceMock, ratingCreationCommandValidator);

        mockMvc = MockMvcBuilders.standaloneSetup(ratingController)
                .setControllerAdvice(new GlobalExceptionHandler(messageSource()))
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testCreateNewRating() throws Exception {
        //given
        RatingCreationCommand command = new RatingCreationCommand();
        command.setMovieId(100L);
        command.setRatingValue(4);
        command.setText("Very good");
        command.setEmail("test@test.hu");

        Rating rating = new Rating();
        rating.setMovieId(100L);
        rating.setRatingValue(4);
        rating.setText("Very good");
        rating.setEmail("test@test.hu");

        when(ratingServiceMock.createRating(command)).thenReturn(rating);

        //when
        this.mockMvc.perform(
                        post("/api/ratings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(command)))

                //then
                .andExpect(status().is(201));

        verify(ratingServiceMock, times(1)).createRating(command);
        verifyNoMoreInteractions(ratingServiceMock);
    }

    @Test
    void testCreateNewRatingWithValidationErrors() throws Exception {
        //given
        RatingCreationCommand command = new RatingCreationCommand();
        command.setMovieId(null);
        command.setRatingValue(6);
        command.setText("Very good");
        command.setEmail("testtest.hu");

        //when
        this.mockMvc.perform(
                        post("/api/ratings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(command)))

                //then
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fieldErrors", hasSize(3)))
                .andExpect(jsonPath("$.fieldErrors[0].field", is("movieId")))
                .andExpect(jsonPath("$.fieldErrors[0].message", is("Movie id is missing")))
                .andExpect(jsonPath("$.fieldErrors[1].field", is("ratingValue")))
                .andExpect(jsonPath("$.fieldErrors[1].message", is("Rating value more than 5 is not supported")))
                .andExpect(jsonPath("$.fieldErrors[2].field", is("email")))
                .andExpect(jsonPath("$.fieldErrors[2].message", is("Email address has invalid format")));


        verifyNoMoreInteractions(ratingServiceMock);
    }

    @Test
    void testGetAllRatingsForMovie() throws Exception {
        //given
        RatingListItem item1 = new RatingListItem();
        item1.setRatingValue(5);
        item1.setText("Excellent");
        item1.setEmail("test1@test.hu");

        RatingListItem item2 = new RatingListItem();
        item2.setRatingValue(2);
        item2.setText("Poor");
        item2.setEmail("test2@test.hu");

        when(ratingServiceMock.getAllRatingsForMovie(1L)).thenReturn(List.of(item1, item2));

        //when
        this.mockMvc.perform(get("/api/ratings/1"))

                //then
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ratingValue", is(5)))
                .andExpect(jsonPath("$[0].text", is("Excellent")))
                .andExpect(jsonPath("$[0].email", is("test1@test.hu")))
                .andExpect(jsonPath("$[1].ratingValue", is(2)))
                .andExpect(jsonPath("$[1].text", is("Poor")))
                .andExpect(jsonPath("$[1].email", is("test2@test.hu")));

        verify(ratingServiceMock, times(1)).getAllRatingsForMovie(1L);
        verifyNoMoreInteractions(ratingServiceMock);

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
