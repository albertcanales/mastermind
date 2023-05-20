package exceptions.persistance;

/**
 * Excepció per indicar que la línia a insertar al fitxer CSV ja existeix
 * @author Arnau Valls Fusté
 */
public class LineAlreadyExistsException extends PersistanceException {

    /**
     * Constructor de l'excepció
     */
    public LineAlreadyExistsException(String key, String fileName) {
        super(String.format("Tried to insert a line %s that already exists in CSV file %s", key, fileName));
    }

}