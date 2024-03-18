package bmg.hu.ponte_movies.repository;

import bmg.hu.ponte_movies.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    void testSaveAndFindAllRatings() {
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
        rating3.setMovieId(102L);
        rating3.setRatingValue(5);
        rating3.setText("Excellent");
        rating3.setEmail("test3@test.com");
        ratingRepository.save(rating3);

        //when
        List<Rating> ratingList = ratingRepository.findAll();

        //then
        assertEquals(3, ratingList.size());
    }

    @Test
    void testFindAllByMovieId() {
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
        List<Rating> ratingList = ratingRepository.findAllByMovieId(100L);

        //then
        assertEquals(2, ratingList.size());
        assertEquals(5, ratingList.get(0).getRatingValue());
        assertEquals("Excellent", ratingList.get(0).getText());
        assertEquals("test3@test.com", ratingList.get(0).getEmail());
        assertEquals(3, ratingList.get(1).getRatingValue());
        assertEquals("Average", ratingList.get(1).getText());
        assertEquals("test1@test.com", ratingList.get(1).getEmail());
    }


}
