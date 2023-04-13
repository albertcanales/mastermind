package domain.exceptions;

public class InvalidStatSizeException extends DomainException {

    public InvalidStatSizeException(String stat, Integer value) {
        super(String.format("The stat %s has invalid size %d", stat, value));
    }
}
