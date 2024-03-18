package bmg.hu.ponte_movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingListItem {

    private Integer ratingValue;
    private String text;
    private String email;

}
