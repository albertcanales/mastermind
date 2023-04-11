package domain.exceptions;

public class NoGameSavedException extends DomainException {

    public NoGameSavedException() {
        super("There is no saved game for the logged user");
    }

}
