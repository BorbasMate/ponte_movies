package bmg.hu.ponte_movies.integration_full;

import bmg.hu.ponte_movies.controller.MovieController;
import bmg.hu.ponte_movies.dto.MovieListItem;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
@ExtendWith(SpringExtension.class)
@ContextConfiguration
class MovieIT {

    @Autowired
    private MovieController movieController;

    @Test
    void testGetMovies() {
        //given

        //when
        ResponseEntity<List<MovieListItem>> response = movieController.getMovies();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()));
    }


}
