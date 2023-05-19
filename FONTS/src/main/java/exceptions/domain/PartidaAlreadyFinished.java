package exceptions.domain;

/**
 * Excepció per si s'intenta seguir jugant en una partida que ja està acabada
 */
public class PartidaAlreadyFinished extends DomainException {

    /**
     * Constructor de la excepció
     */
    public PartidaAlreadyFinished() {
        super("The partida being played has already finished");
    }

}
