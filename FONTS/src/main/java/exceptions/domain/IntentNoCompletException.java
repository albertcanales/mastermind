package exceptions.domain;

public class IntentNoCompletException extends DomainException {

    public IntentNoCompletException() {
        super("L'últim intent no està complet");
    }

}
