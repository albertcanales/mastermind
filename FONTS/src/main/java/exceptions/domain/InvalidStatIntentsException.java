package exceptions.domain;

/**
 * Excepció per indicar que una estadística intents té un valor incorrecte, és a dir, negatiu o 0 a una partida guanyada
 */
public class InvalidStatIntentsException extends DomainException {
    /**
     * Constructor de l'excepció
     * @param value valor incorrecte d'intents
     */
    public InvalidStatIntentsException(String value) {
        super(String.format("An intents stat has invalid value %s", value));
    }
}
