package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "The controller of the movies")
public class MovieController {

    private final MovieService movieService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @Operation(summary = "Fetch current top rated movies")
    @ApiResponse(responseCode = "200", description = "Movie list has been fetched successfully")
    @ApiResponse(responseCode = "500", description = "An error occured during fetching the list")
    public ResponseEntity<List<MovieListItem>> getMovies() {
        LOGGER.info("Movie list requested");
        List<MovieListItem> movieListItems = movieService.findMovies();
        return new ResponseEntity<>(movieListItems, HttpStatus.OK);
    }
}
