package exceptions.domain;

/**
 * Excepció per si s'intenta seguir jugant en una partida que ja està acabada
 * @author Albert Canales Ros
 */
public class PartidaAlreadyFinishedException extends DomainException {

    /**
     * Constructor de la excepció
     */
    public PartidaAlreadyFinishedException() {
        super("The partida being played has already finished");
    }

}
