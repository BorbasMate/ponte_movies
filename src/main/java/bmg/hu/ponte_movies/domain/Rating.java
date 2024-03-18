package bmg.hu.ponte_movies.domain;

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
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "ratingValue")
    private Integer ratingValue;

    @Column(name = "text")
    private String text;

    @Column(name = "email")
    private String email;
}
