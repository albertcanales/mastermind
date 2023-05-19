package exceptions.domain;

/**
 * Excepció per quan no hi ha cap usuari que ha iniciat sessió
 * @author Albert Canales Ros
 */
public class NotLoggedInException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public NotLoggedInException() {
        super("You are not logged in");
    }

}
