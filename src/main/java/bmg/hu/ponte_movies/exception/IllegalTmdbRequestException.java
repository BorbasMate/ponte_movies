package bmg.hu.ponte_movies.exception;

public class IllegalTmdbRequestException extends RuntimeException {

    public IllegalTmdbRequestException(String message) {
        super(message);
    }
}
