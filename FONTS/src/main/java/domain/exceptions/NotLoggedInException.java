package domain.exceptions;

public class NotLoggedInException extends DomainException {

    public NotLoggedInException() {
        super("You are not logged in");
    }

}
