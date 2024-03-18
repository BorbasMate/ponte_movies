package bmg.hu.ponte_movies.controller;

import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import bmg.hu.ponte_movies.dto.RatingListItem;
import bmg.hu.ponte_movies.service.RatingService;
import bmg.hu.ponte_movies.validator.RatingCreationCommandValidator;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final RatingCreationCommandValidator ratingCreationCommandValidator;
    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    public RatingController(RatingService ratingService, RatingCreationCommandValidator ratingCreationCommandValidator) {
        this.ratingService = ratingService;
        this.ratingCreationCommandValidator = ratingCreationCommandValidator;
    }

    @InitBinder("ratingCreationCommand")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(ratingCreationCommandValidator);
    }

    @PostMapping
    public ResponseEntity<Void> createNewRating(@Valid @RequestBody RatingCreationCommand command) {
        LOGGER.info("Rating creation requested");
        ratingService.createRating(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<RatingListItem>> getAllRatingsForMovie(@PathVariable Long movieId) {
        LOGGER.info("Rating list requested");
        List<RatingListItem> ratings = ratingService.getAllRatingsForMovie(movieId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }


}
