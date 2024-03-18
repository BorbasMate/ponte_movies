package bmg.hu.ponte_movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieListItem {

    private Long id;
    private String title;
    private String releaseDate;
    private String overView;
    private String originalLanguage;
    private List<String> cast = new ArrayList<>();
    private List<String> productionCompanies = new ArrayList<>();

}
