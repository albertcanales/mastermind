package exceptions.domain;

/**
 * Excepció que salta si el nombre de Boles d'una llista no és l'esperat
 * @author Arnau Valls Fusté
 */
public class InvalidNumBolesException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param actualSize el tamany
     * @param expectedSize el tamany esperat
     */
    public InvalidNumBolesException(Integer actualSize, Integer expectedSize) {
        super(String.format("List has %d Boles, expecting %d", actualSize, expectedSize));
    }
}
