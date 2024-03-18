package bmg.hu.ponte_movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieListItem {

    @Schema(description = "The id of the movie from TMDB database", example = "278")
    private Long movieId;

    @Schema(description = "The title of the movie from TMDB database", example = "The Shawshank Redemption")
    private String title;

    @Schema(description = "The release date of the movie from TMDB database", example = "1994-09-23")
    private String releaseDate;

    @Schema(description = "The overview of the movie from TMDB database", example = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.")
    private String overView;

    @Schema(description = "The original language of the movie from TMDB database", example = "en")
    private String originalLanguage;

    @Schema(description = "The poster path the movie from TMDB database", example = "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg")
    private String posterPath;

    @Schema(description = "The 5 main cast of the movie from TMDB database", example = "Tim Robbins, Morgan Freeman, Bob Gunton, William Sandler, Clancy Brown")
    private List<String> cast = new ArrayList<>();

    @Schema(description = "The production company (companies if more) of the movie from TMDB database", example = "Castle Rock Entertainment")
    private List<String> productionCompanies = new ArrayList<>();

    @Schema(description = "Some path of images from the movie from TMDB database", example = "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg, /tXHpvlr5F7gV5DwgS7M5HBrUi2C.jpg, .... etc")
    private List<String> images = new ArrayList<>();

}
