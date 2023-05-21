package exceptions.domain;

/**
 * Excepció per indicar que una estadística temps té un valor incorrecte, és a dir, no positiu
 * @author Kamil Przybyszewski
 */
public class InvalidStatTempsException extends DomainException {
    /**
     * Constructor de l'excepció
     * @param value valor incorrecte de temps
     */
    public InvalidStatTempsException(String value) {
        super(String.format("A temps stat has invalid value %s", value));
    }
}
