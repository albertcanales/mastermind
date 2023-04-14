package domain.exceptions;

public class InvalidIntentsStateException extends DomainException {
    public InvalidIntentsStateException() {
        super("The Intents are invalid");
    }
}
