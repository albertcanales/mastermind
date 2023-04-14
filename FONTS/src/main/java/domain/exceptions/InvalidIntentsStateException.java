package domain.exceptions;

public class InvalidIntentsStateException extends DomainException {
    public InvalidIntentsStateException() {
        super("The feedback and or tries are invalid");
    }
}
