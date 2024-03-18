package bmg.hu.ponte_movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieListItem {

    private Long movieId;
    private String title;
    private String releaseDate;
    private String overView;
    private String originalLanguage;
    private String posterPath;
    private List<String> cast = new ArrayList<>();
    private List<String> productionCompanies = new ArrayList<>();
    private List<String> images = new ArrayList<>();

}
