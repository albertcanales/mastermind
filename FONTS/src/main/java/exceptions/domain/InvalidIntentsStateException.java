package exceptions.domain;

public class InvalidIntentsStateException extends DomainException {
    public InvalidIntentsStateException() {
        super("The Intents are invalid");
    }
}
