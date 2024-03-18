package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.dto.MovieListItem;
import bmg.hu.ponte_movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieListItem>> getMovies() throws Exception {
        List<MovieListItem> movieListItems = movieService.findMovies();
        return new ResponseEntity<>(movieListItems, HttpStatus.OK);
    }
}
