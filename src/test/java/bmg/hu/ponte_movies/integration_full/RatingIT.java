package bmg.hu.ponte_movies.integration_full;

import bmg.hu.ponte_movies.controller.RatingController;
import bmg.hu.ponte_movies.domain.Rating;
import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.repository.RatingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@ContextConfiguration
class RatingIT {

    @Autowired
    private RatingController ratingController;

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void testCreateNewRating() {
        //given
        RatingCreationCommand command = new RatingCreationCommand();
        command.setMovieId(100L);
        command.setRatingValue(4);
        command.setText("Very good");
        command.setEmail("test@test.hu");

        //when
        ResponseEntity<Void> response = ratingController.createNewRating(command);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAllRatingsForMovie() {
        //given
        Rating rating1 = new Rating();
        rating1.setMovieId(100L);
        rating1.setRatingValue(3);
        rating1.setText("Average");
        rating1.setEmail("test1@test.com");
        ratingRepository.save(rating1);

        Rating rating2 = new Rating();
        rating2.setMovieId(101L);
        rating2.setRatingValue(4);
        rating2.setText("Very good");
        rating2.setEmail("test2@test.com");
        ratingRepository.save(rating2);

        Rating rating3 = new Rating();
        rating3.setMovieId(100L);
        rating3.setRatingValue(5);
        rating3.setText("Excellent");
        rating3.setEmail("test3@test.com");
        ratingRepository.save(rating3);

        //when
        ResponseEntity<List<RatingListItem>> response = ratingController.getAllRatingsForMovie(100L);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }



}
