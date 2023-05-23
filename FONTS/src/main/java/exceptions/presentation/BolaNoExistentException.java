package exceptions.presentation;

/**
 * Excepció per indicar que es vol accedir a una bola inexistent
 * @author Albert Canales Ros
 */
public class BolaNoExistentException extends PresentationException {

    /**
     * Constructor de l'excepció
     */
    public BolaNoExistentException() {
        super("La bola no existeix");
    }

}