package bmg.hu.ponte_movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Pagination {

    @Schema(description = "One page with list of movies from TMDB database")
    private List<MovieListItem> movieListItemList;

    @Schema(description = "The total number of pages in the TMDB database", example = "552")
    private Long total;

}
