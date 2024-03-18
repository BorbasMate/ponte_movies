package bmg.hu.ponte_movies.validator;

import bmg.hu.ponte_movies.dto.RatingCreationCommand;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RatingCreationCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RatingCreationCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RatingCreationCommand command = (RatingCreationCommand) target;

        if (command.getMovieId() == null) {
            errors.rejectValue("movieId", "movie.id.not.given");
        }
        if (command.getRatingValue() == null) {
            errors.rejectValue("ratingValue", "rating.value.not.given");
        }
        if (command.getRatingValue() < 1) {
            errors.rejectValue("ratingValue", "rating.value.too.low");
        }
        if (command.getRatingValue() > 5) {
            errors.rejectValue("ratingValue", "rating.value.too.high");
        }
        if (command.getText().length() > 255) {
            errors.rejectValue("text", "rating.text.too.long");
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!command.getEmail().matches(emailRegex)) {
            errors.rejectValue("email", "email.format.invalid");
        }

    }
}
