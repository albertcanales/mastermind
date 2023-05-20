package exceptions.domain;

/**
 * Excepció per indicar que el número de ranquings no és l'esperat
 */
public class InvalidNumRanquingsException extends DomainException {
    /**
     * Constructor de l'excepció
     * @param value número incorrecte de ranquings
     */
    public InvalidNumRanquingsException(String value) {
        super(String.format("There is an invalid number of ranquings: %s", value));
    }
}
