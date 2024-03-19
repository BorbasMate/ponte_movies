package bmg.hu.ponte_movies.service;

import bmg.hu.ponte_movies.component.TmdbService;
import bmg.hu.ponte_movies.dto.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieService {

    private final TmdbService tmdbService;

    @Autowired
    public MovieService(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    public Pagination findMovies(int page) {
        Pagination pagination = tmdbService.getTopRatedMovies(page);
        pagination.getMovieListItemList().forEach(tmdbService::getActorsForMovie);
        pagination.getMovieListItemList().forEach(tmdbService::getProductionCompaniesForMovie);
        pagination.getMovieListItemList().forEach(tmdbService::getImagesForMovie);

        return pagination;
    }
}
