package exceptions.domain;

public class InvalidIntentActualException extends DomainException {
    public InvalidIntentActualException(Integer intents, Integer feebacks) {
        super(String.format("Intents has size %s and Feedbacks has size %s", intents, feebacks));
    }
}