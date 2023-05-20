package exceptions.persistance;

/**
 * Excepció per indicar que un fitxer no es pot obrir, tancar o escriure
 * @author Arnau Valls Fusté
 */
public class InvalidFileAccessException extends PersistanceException {

    /**
     * Constructor de l'excepció
     */
    public InvalidFileAccessException(String fileName) {
        super(String.format("Wrong Permissions to open, close, write file or folder doesn't exist %s", fileName));
    }
}
