package persistance.exceptions;

import domain.exceptions.DomainException;

public class PersistanceException extends DomainException {
    public PersistanceException(String str) {
        super(str);
    }
}
