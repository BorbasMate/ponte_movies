package bmg.hu.ponte_movies.service;

import bmg.hu.ponte_movies.domain.Rating;
import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.repository.RatingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration
class RatingServiceTest {

    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepositoryMock;

    @BeforeEach
    public void setup() {
        ratingService = new RatingService(ratingRepositoryMock);
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testCreateRating() {
        //given
        RatingCreationCommand command = new RatingCreationCommand();
        command.setMovieId(100L);
        command.setRatingValue(4);
        command.setText("Very good");
        command.setEmail("test@test.hu");

        when(ratingRepositoryMock.save(any(Rating.class))).thenAnswer(returnsFirstArg());

        //when
        Rating rating = ratingService.createRating(command);

        //then
        assertEquals(100L, rating.getMovieId());
        assertEquals(4, rating.getRatingValue());
        assertEquals("Very good", rating.getText());
        assertEquals("test@test.hu", rating.getEmail());

        verify(ratingRepositoryMock, times(1)).save(any(Rating.class));
        verifyNoMoreInteractions(ratingRepositoryMock);

    }

    @Test
    void testGetAllRatingsForMovie() {
        //given
        Rating rating1 = new Rating();
        rating1.setMovieId(100L);
        rating1.setRatingValue(3);
        rating1.setText("Average");
        rating1.setEmail("test1@test.com");

        Rating rating2 = new Rating();
        rating2.setMovieId(100L);
        rating2.setRatingValue(4);
        rating2.setText("Very good");
        rating2.setEmail("test2@test.com");

        Rating rating3 = new Rating();
        rating3.setMovieId(100L);
        rating3.setRatingValue(5);
        rating3.setText("Excellent");
        rating3.setEmail("test3@test.com");

        when(ratingRepositoryMock.findAllByMovieId(100L)).thenReturn(List.of(rating3, rating2, rating1));

        //when
        List<RatingListItem> ratingList = ratingService.getAllRatingsForMovie(100L);

        //then
        assertEquals(3, ratingList.size());
        assertEquals(5, ratingList.get(0).getRatingValue());
        assertEquals(4, ratingList.get(1).getRatingValue());
        assertEquals(3, ratingList.get(2).getRatingValue());

        verify(ratingRepositoryMock, times(1)).findAllByMovieId(100L);
        verifyNoMoreInteractions(ratingRepositoryMock);

    }

}
