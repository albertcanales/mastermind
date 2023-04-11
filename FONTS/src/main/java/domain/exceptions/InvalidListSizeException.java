package domain.exceptions;

public class InvalidListSizeException extends DomainException {
    public InvalidListSizeException(String list, String actualSize, String expectedSize) {
        super(String.format("List %s has size %s, expecting %s", list, actualSize, expectedSize));
    }
}
