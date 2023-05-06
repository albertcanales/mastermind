package persistance.exceptions;

public class InvalidPermissionsException extends PersistanceException {
    public InvalidPermissionsException(String fileName) {
        super(String.format("Wrong Permissions to open, close, write file or folder doesn't exist %s", fileName));
    }
}
