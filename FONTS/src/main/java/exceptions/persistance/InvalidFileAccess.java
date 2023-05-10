package exceptions.persistance;

public class InvalidFileAccess extends PersistanceException {
    public InvalidFileAccess(String fileName) {
        super(String.format("Wrong Permissions to open, close, write file or folder doesn't exist %s", fileName));
    }
}
