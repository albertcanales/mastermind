package persistance.exceptions;

public class InvalidPermissionsException extends PersistanceException {
    public InvalidPermissionsException(String fileName) {
        super(String.format("Wrong Permissions to open, close or write file %s", fileName));
    }
}
