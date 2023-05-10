package exceptions.persistance;

public class LineNotFoundException extends PersistanceException {

    public LineNotFoundException(String key, String fileName) {
        super(String.format("Cannot find line with key %s in CSV file %s", key, fileName));
    }

}