package domain.exceptions;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String username) {
        super(String.format("Username with username %s already exists", username));
    }

}
