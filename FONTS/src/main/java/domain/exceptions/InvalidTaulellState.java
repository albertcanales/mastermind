package domain.exceptions;

public class InvalidTaulellState extends DomainException {
    public InvalidTaulellState() {
        super("The feedback and or tries are invalid");
    }
}
