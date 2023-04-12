package domain.exceptions;

public class PartidaAlreadyFinished extends DomainException {

    public PartidaAlreadyFinished() {
        super("The partida being played has already finished");
    }

}
