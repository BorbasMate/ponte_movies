package bmg.hu.ponte_movies.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingListItem {

    @Schema(description = "The numeric value of the rating", example = "4")
    private Integer ratingValue;

    @Schema(description = "The text/comment send with rating", example = "Very good movie")
    private String text;

    @Schema(description = "The email of the api user", example = "user@user.com")
    private String email;

}
