package exceptions.domain;

public class InvalidNumIntentsException extends DomainException {
    public InvalidNumIntentsException(Integer actualSize, Integer expectedSize) {
        super(String.format("List has %d Intents, expecting %d", actualSize, expectedSize));
    }
}
