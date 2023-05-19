package exceptions.presentation;

/**
 * Excepció per indicar que es vol accedir a una bola inexistent
 * @author Albert Canales Ros
 */
public class BolaNoExistent extends PresentationException {

    /**
     * Constructor de l'excepció
     */
    public BolaNoExistent() {
        super("La bola no existeix");
    }

}