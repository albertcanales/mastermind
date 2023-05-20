package exceptions.persistance;

/**
 * Excepció per indicar que un fitxer CSV té un header erroni, els separadors incorrectes o altres tipus d'error
 * de format
 * @author Arnau Valls Fusté
 */
public class InvalidCSVException extends PersistanceException {

    /**
     * Constructor de l'excepció
     */
    public InvalidCSVException(String fileName) {
        super(String.format("CSV file %s is in an invalid format", fileName));
    }

}
