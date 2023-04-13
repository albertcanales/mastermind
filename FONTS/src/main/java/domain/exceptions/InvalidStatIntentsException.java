package domain.exceptions;

public class InvalidStatIntentsException extends DomainException {
    public InvalidStatIntentsException(String value) {
        super(String.format("An intents stat has invalid value %s", value));
    }
}
