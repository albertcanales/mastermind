package exceptions.domain;

/**
 * Excepció per si s'intenta carregar una partida quan no n'hi havia cap de guardada
 * @author Albert Canales Ros
 */
public class NoGameSavedException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public NoGameSavedException() {
        super("There is no saved game for the logged user");
    }

}
