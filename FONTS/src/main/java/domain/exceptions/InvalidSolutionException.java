package domain.exceptions;

public class InvalidSolutionException extends DomainException {
    public InvalidSolutionException() {
        super("Solution is not valid");
    }
}
