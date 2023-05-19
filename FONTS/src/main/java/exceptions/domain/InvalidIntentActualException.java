package exceptions.domain;

/**
 * Mètode per quan el nombre d'intents i de feedbacks d'un taulell no corresponen entre si
 * @author Arnau Valls Fusté
 */
public class InvalidIntentActualException extends DomainException {
    /**
     * Constructor de l'excepció
     * @param intents Nombre d'intents
     * @param feebacks Nombre de feedbacks
     */
    public InvalidIntentActualException(Integer intents, Integer feebacks) {
        super(String.format("Intents has size %s and Feedbacks has size %s", intents, feebacks));
    }
}