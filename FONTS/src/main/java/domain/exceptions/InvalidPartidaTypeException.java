package domain.exceptions;

public class InvalidPartidaTypeException extends DomainException {

    public InvalidPartidaTypeException(String type) {
        super(String.format("Invalid operation, as the current partida is of type %s", type));
    }

}
