package bmg.hu.ponte_movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingCreationCommand {

    private Long movieId;
    private Integer ratingValue;
    private String text;
    private String email;
}
