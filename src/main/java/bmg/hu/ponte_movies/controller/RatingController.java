package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.service.RatingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewRating(@Valid @RequestBody RatingCreationCommand command) {
        LOGGER.info("Rating creation requested");
        ratingService.createRating(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<RatingListItem>> getRatings(@PathVariable Long movieId) {
        LOGGER.info("Rating list requested");
        List<RatingListItem> ratings = ratingService.getAllRatingForProduct(movieId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }


}
