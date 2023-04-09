package domain.exceptions;

public class invalidEnumValue extends DomainException {
    public invalidEnumValue(String enumName, String value) {
        super(String.format("Enum %s has invalid value %s", enumName, value));
    }
}
