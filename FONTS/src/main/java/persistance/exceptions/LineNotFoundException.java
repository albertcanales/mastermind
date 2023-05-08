package persistance.exceptions;

public class LineNotFoundException extends PersistanceException {

    public LineNotFoundException(String element, String fileName) {
        super(String.format("Cannot find line with key %s in CSV file %s", element, fileName));
    }

}