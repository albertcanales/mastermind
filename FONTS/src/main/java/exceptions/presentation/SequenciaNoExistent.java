package exceptions.presentation;

/**
 * Excepció per quan s'intenta accedir a una seqüència inexistent
 * @author Albert Canales Ros
 */
public class SequenciaNoExistent extends PresentationException {

    /**
     * Constructor de la seqüència
     */
    public SequenciaNoExistent() {
        super("La seqüència no existeix");
    }

}
