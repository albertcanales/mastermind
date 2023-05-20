package exceptions.persistance;

public class InvalidFileAccessException extends PersistanceException {
    public InvalidFileAccessException(String fileName) {
        super(String.format("Wrong Permissions to open, close, write file or folder doesn't exist %s", fileName));
    }
}
