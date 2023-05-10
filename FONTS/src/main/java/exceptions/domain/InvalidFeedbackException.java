package exceptions.domain;

import java.util.List;

public class InvalidFeedbackException extends DomainException {

    public InvalidFeedbackException(List<Integer> feedback) {
        super(String.format("The feedback %s is invalid", feedback));
    }

}