package exceptions.domain;

/**
 * Excepció per indicar que l'últim intent de la partida que s'està jugant no està complet, és a dir,
 * té alguna bola nul·la
 * @author Albert Canales Ros
 */
public class IntentNoCompletException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public IntentNoCompletException() {
        super("L'últim intent no està complet");
    }

}
