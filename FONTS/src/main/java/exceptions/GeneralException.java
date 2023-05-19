package exceptions;

/**
 * Excepció de la qual hereten tota la resta d'excepcions pròpies de l'aplicació
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
