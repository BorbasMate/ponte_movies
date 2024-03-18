package bmg.hu.ponte_movies.repository;

import bmg.hu.ponte_movies.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select r from Rating r where r.movieId = :movieId order by r.id desc")
    List<Rating> findAllByMovieId(@Param("movieId") Long movieId);

}
