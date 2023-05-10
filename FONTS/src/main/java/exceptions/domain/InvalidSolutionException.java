package exceptions.domain;

public class InvalidSolutionException extends DomainException {
    public InvalidSolutionException() {
        super("Solution is not valid");
    }
}
