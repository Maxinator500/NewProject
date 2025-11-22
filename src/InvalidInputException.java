public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidInputException{" + getMessage() + "}";
    }
}
