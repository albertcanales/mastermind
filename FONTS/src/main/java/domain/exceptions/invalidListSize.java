package domain.exceptions;

public class invalidListSize extends DomainException {
    public invalidListSize(String list, String actualSize, String expectedSize) {
        super(String.format("List %s has size %s, expecting %s", list, actualSize, expectedSize));
    }
}
