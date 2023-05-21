package exceptions.domain;

import java.util.List;

/**
 * Excepció que salta si un Intent té nul·ls
 * @author Arnau Valls Fusté
 */
public class InvalidIntentException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param intent la llista d'intents
     */
    public InvalidIntentException(List<Integer> intent) {
        super(String.format("The \"intent\" %s is invalid", intent));
    }

}