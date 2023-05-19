package exceptions.domain;

/**
 * Excepció per quan no hi ha cap partida éssent jugada
 * @author Albert Canales Ros
 */
public class NotPlayingPartidaException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public NotPlayingPartidaException() {
        super("There is no partida being played");
    }

}
