package exceptions.persistance;

/**
 * Excepció per indicar que la línia no s'ha pogut trobar al fitxer CSV
 * @author Arnau Valls Fusté
 */
public class LineNotFoundException extends PersistanceException {

    /**
     * Constructor de l'excepció
     * @param key Línia que no s'ha trobat en el fitxer
     * @param fileName Nom del fitxer en què s'ha buscat la línea
     */
    public LineNotFoundException(String key, String fileName) {
        super(String.format("Cannot find line with key %s in CSV file %s", key, fileName));
    }

}