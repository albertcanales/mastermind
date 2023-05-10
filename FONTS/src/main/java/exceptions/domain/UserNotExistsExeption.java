package exceptions.domain;

public class UserNotExistsExeption extends DomainException {

    public UserNotExistsExeption(String username) {
        super(String.format("Username with username %s does not exists", username));
    }

}
