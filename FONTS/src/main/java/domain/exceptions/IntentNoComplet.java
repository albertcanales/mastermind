package domain.exceptions;

public class IntentNoComplet extends DomainException {

    public IntentNoComplet() {
        super("L'últim intent no està complet");
    }

}
