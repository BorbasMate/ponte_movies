package bmg.hu.ponte_movies.service;

import bmg.hu.ponte_movies.domain.Rating;
import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.repository.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(RatingCreationCommand command) {

        Rating rating = new Rating();
        rating.setMovieId(command.getMovieId());
        rating.setRatingValue(command.getRatingValue());
        rating.setText(command.getText());
        rating.setEmail(command.getEmail());
        ratingRepository.save(rating);

        return rating;
    }

    public List<RatingListItem> getAllRatingsForMovie(Long movieId) {
        List<Rating> ratingList = ratingRepository.findAllByMovieId(movieId);
        return ratingList.stream().map(rating -> {
            RatingListItem ratingListItem = new RatingListItem();
            ratingListItem.setRatingValue(rating.getRatingValue());
            ratingListItem.setText(rating.getText());
            ratingListItem.setEmail(rating.getEmail());
            return ratingListItem;

        }).toList();
    }



}
