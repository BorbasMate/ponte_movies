package bmg.hu.ponte_movies.service;

import bmg.hu.ponte_movies.component.TmdbService;
import bmg.hu.ponte_movies.dto.MovieListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private final TmdbService tmdbService;

    @Autowired
    public MovieService(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }


    public List<MovieListItem> findMovies() throws Exception {
        List<MovieListItem> movies = tmdbService.getTopRatedMovies();
        return movies;
    }
}
