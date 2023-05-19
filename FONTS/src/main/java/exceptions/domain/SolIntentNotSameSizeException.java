package exceptions.domain;

/**
 * Excepció per quan la solució i els intents tenen diferent nombre de boles
 * @author Kamil Przybyszewski
 */
public class SolIntentNotSameSizeException extends DomainException{

    /**
     * Constructor de l'excepció
     * @param solutionSize Mida de la solució
     * @param intentSize Mida de l'intent
     */
    public SolIntentNotSameSizeException(Integer solutionSize, Integer intentSize){
        super(String.format("Solution has %d boles, intent has %d", solutionSize, intentSize));
    }
}
