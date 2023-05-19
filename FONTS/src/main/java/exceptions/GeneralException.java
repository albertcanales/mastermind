package exceptions;

/**
 * Excepció que engloba a totes les excepcions de l'aplicació
 * @author Albert Canales Ros
 */
public class GeneralException extends Exception {

    /**
     * Constructor de l'excepció
     * @param str Missatge que es mostrarà en l'excepció
     */
    public GeneralException(String str) {
        super(str);
    }

}
