package exceptions.domain;

/**
 * Excepció que indica si s'està realitzant una operació incorrecta pel tipus
 * de partida actual
 * @author Albert Canales Ros
 */
public class InvalidPartidaTypeException extends DomainException {

    /**
     * Constructor de l'excepció
     * @param type tipus de la partida
     */
    public InvalidPartidaTypeException(String type) {
        super(String.format("Invalid operation, as the current partida is of type %s", type));
    }

}
