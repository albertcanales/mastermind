package exceptions.domain;

public class InvalidEnumValueException extends DomainException {
    public InvalidEnumValueException(String enumName, String value) {
        super(String.format("Enum %s has invalid value %s", enumName, value));
    }
}
