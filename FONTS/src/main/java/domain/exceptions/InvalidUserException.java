package domain.exceptions;

public class InvalidUserException extends DomainException {

    public InvalidUserException() {
        super("The parameters for the user are not valid");
    }

}
