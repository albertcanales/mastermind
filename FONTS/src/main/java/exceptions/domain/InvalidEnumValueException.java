package exceptions.domain;

/**
 * Excepció per quan es vol obtenir un enum de l'aplicació a partir d'un valor incorrecte
 * @author Arnau Valls Fusté
 */
public class InvalidEnumValueException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param enumName String que representa l'enum
     * @param value Valor que no correspon a cap enum
     */
    public InvalidEnumValueException(String enumName, String value) {
        super(String.format("Enum %s has invalid value %s", enumName, value));
    }
}
