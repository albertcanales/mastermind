package domain.exceptions;

public class NotPlayingPartidaException extends DomainException {

    public NotPlayingPartidaException() {
        super("There is no partida being played");
    }

}
