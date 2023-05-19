package exceptions.domain;

/**
 * Excepció per quan els paràmetres d'un usuari (nom d'usuari, nom complet o contrasenya) no són correctes
 * @author Albert Canales Ros
 */
public class InvalidUserException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public InvalidUserException() {
        super("The parameters for the user are not valid");
    }

}
