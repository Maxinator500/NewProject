package exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "exceptions.InvalidInputException{" + getMessage() + "}";
    }
}
