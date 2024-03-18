package bmg.hu.ponte_movies.integration_services;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
class MovieServiceIT {

    @Autowired
    private MovieService movieService;

    @Test
    void testFindMovies() {
        //given

        //when
        List<MovieListItem> movieListItems = movieService.findMovies();

        //then
        assertFalse(movieListItems.isEmpty());
        for (MovieListItem movieListItem : movieListItems) {
            assertNotNull(movieListItem.getMovieId());
            assertNotNull(movieListItem.getTitle());
            assertNotNull(movieListItem.getReleaseDate());
            assertNotNull(movieListItem.getOverView());
            assertNotNull(movieListItem.getOriginalLanguage());
            assertNotNull(movieListItem.getPosterPath());
            assertNotNull(movieListItem.getCast());
            assertNotNull(movieListItem.getProductionCompanies());
            assertNotNull(movieListItem.getImages());

        }

    }

}
