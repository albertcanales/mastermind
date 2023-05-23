package exceptions.presentation;

/**
 * Excepció per quan s'intenta accedir a una seqüència inexistent
 * @author Albert Canales Ros
 */
public class SequenciaNoExistentException extends PresentationException {

    /**
     * Constructor de la seqüència
     */
    public SequenciaNoExistentException() {
        super("La seqüència no existeix");
    }

}
