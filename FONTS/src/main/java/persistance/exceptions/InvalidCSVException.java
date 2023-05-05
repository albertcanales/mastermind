package persistance.exceptions;

public class InvalidCSVException extends PersistanceException {

    public InvalidCSVException(String fileName) {
        super(String.format("CSV file %s is in an invalid format", fileName));
    }

}
