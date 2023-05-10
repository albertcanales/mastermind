package exceptions.persistance;

public class LineAlreadyExistsException extends PersistanceException {

    public LineAlreadyExistsException(String key, String fileName) {
        super(String.format("Tried to insert a line %s that already exists in CSV file %s", key, fileName));
    }

}