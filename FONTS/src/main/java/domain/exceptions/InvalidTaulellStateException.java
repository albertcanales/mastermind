package domain.exceptions;

public class InvalidTaulellStateException extends DomainException {
    public InvalidTaulellStateException() {
        super("The feedback and or tries are invalid");
    }
}
