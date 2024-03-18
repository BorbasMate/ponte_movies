package bmg.hu.ponte_movies.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    @Schema(description = "The id of the Rating", example = "1")
    private Long id;

    @Column(name = "movie_id")
    @Schema(description = "The id of the movie from TMDB database", example = "278")
    private Long movieId;

    @Column(name = "ratingValue")
    @Schema(description = "The numeric value of the rating", example = "4")
    private Integer ratingValue;

    @Column(name = "text")
    @Schema(description = "The text/comment send with rating", example = "Very good movie")
    private String text;

    @Column(name = "email")
    @Schema(description = "The email of the api user", example = "user@user.com")
    private String email;
}
