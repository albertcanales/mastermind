package exceptions.domain;

/**
 * Excepció per quan es vol iniciar sessió amb un usuari no existeix
 * @author Albert Canales Ros
 */
public class UserNotExistsException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param username Username de l'usuari amb el que s'ha intentat inciar sessió
     */
    public UserNotExistsException(String username) {
        super(String.format("Username with username %s does not exists", username));
    }

}
