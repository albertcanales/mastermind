package domain.exceptions;

public class InvalidNumBolesException extends DomainException {
    public InvalidNumBolesException(Integer actualSize, Integer expectedSize) {
        super(String.format("List has %d Boles, expecting %d", actualSize, expectedSize));
    }
}
