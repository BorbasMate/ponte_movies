package bmg.hu.ponte_movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingCreationCommand {

    @Schema(description = "The id of the movie from TMDB database", example = "278")
    private Long movieId;

    @Schema(description = "The numeric value of the rating", example = "4")
    private Integer ratingValue;

    @Schema(description = "The text/comment send with rating", example = "Very good movie")
    private String text;

    @Schema(description = "The email of the api user", example = "user@user.com")
    private String email;
}
