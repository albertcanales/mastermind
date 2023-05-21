package exceptions.domain;

/**
 * Excepció que indica si una solució és invàlida
 */
public class InvalidSolutionException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public InvalidSolutionException() {
        super("Solution is not valid");
    }
}
