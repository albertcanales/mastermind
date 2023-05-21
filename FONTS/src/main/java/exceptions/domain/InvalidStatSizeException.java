package exceptions.domain;

/**
 * Excepció per indicar que una llista d'estadístiques no té la mida esperada
 * @author Kamil Przybyszewski
 */
public class InvalidStatSizeException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param stat nom de l'estadística
     * @param value mida incorrecta de la llista d'estadístiques
     */
    public InvalidStatSizeException(String stat, Integer value) {
        super(String.format("The stat %s has invalid size %d", stat, value));
    }
}
