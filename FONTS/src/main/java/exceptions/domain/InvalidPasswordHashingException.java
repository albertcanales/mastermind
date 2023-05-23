package exceptions.domain;

/**
 * Excepció per indicar que no s'ha pogut obtenir el hash de la contrasenya (en el cas de l'aplicació, utilitzant SHA-256).
 * @author Albert Canales Ros
 */
public class InvalidPasswordHashingException extends DomainException {

    /**
     * Constructor de la excepció
     */
    public InvalidPasswordHashingException() {
        super("No s'ha pogut obtenir el hash de la contrasenya");
    }
}
