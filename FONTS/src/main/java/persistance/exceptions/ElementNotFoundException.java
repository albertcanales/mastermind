package persistance.exceptions;

public class ElementNotFoundException extends PersistanceException {

    public ElementNotFoundException(String element, String fileName) {
        super(String.format("Cannot find element %s in CSV file %s", element, fileName));
    }

}