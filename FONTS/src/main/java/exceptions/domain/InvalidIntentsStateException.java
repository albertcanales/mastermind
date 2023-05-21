package exceptions.domain;

/**
 * Excepció que salta si una unió d'intents i feedbacks és incorrecta (el seu size no és correcte)
 * @author Arnau Valls Fusté
 */
public class InvalidIntentsStateException extends DomainException {

    /**
     * Constructor de l'excepció
     */
    public InvalidIntentsStateException() {
        super("The Intents are invalid");
    }
}
