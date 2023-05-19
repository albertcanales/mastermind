package exceptions.domain;

/**
 * Excepció per si s'intenta crear un usuari que ja existeix
 * @author Albert Canales Ros
 */
public class UserAlreadyExistsException extends DomainException {

    /**
     * Constructor de la excepció
     * @param username Username que s'ha volgut registrar
     */
    public UserAlreadyExistsException(String username) {
        super(String.format("Username with username %s already exists", username));
    }

}
