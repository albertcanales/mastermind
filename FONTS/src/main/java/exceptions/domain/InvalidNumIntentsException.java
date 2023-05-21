package exceptions.domain;

/**
 * Excepció que salta si el nombre d'intents d'una llista és incorrecte
 * @author Arnau Valls Fusté
 */
public class InvalidNumIntentsException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param actualSize el tamany
     * @param expectedSize el tamany esperat
     */
    public InvalidNumIntentsException(Integer actualSize, Integer expectedSize) {
        super(String.format("List has %d Intents, expecting %d", actualSize, expectedSize));
    }
}
