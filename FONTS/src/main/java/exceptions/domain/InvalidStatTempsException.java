package exceptions.domain;

public class InvalidStatTempsException extends DomainException {

    public InvalidStatTempsException(String value) {
        super(String.format("A temps stat has invalid value %s", value));
    }
}
