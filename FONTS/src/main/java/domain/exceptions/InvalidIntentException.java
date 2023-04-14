package domain.exceptions;

import java.util.List;

public class InvalidIntentException extends DomainException {

    public InvalidIntentException(List<Integer> intent) {
        super(String.format("The \"intent\" %s is invalid", intent));
    }

}