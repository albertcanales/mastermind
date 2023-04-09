package domain.exceptions;

public class DomainException extends Exception{
    public DomainException() {
        super();
    }

    public DomainException(String str) {
        super(str);
    }
}
