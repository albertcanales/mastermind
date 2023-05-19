package exceptions.domain;

import java.util.List;

/**
 * Excepció per quan una seqüència de feedback és invàlida (té boles diferents de nul·la, blanca i negra)
 * @author Arnau Valls Fusté
 */
public class InvalidFeedbackException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param feedback Seqüència que s'ha intentat assignar a un feedback
     */
    public InvalidFeedbackException(List<Integer> feedback) {
        super(String.format("The feedback %s is invalid", feedback));
    }

}