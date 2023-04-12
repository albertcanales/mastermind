package domain.exceptions;

public class SolIntentNotSameSizeException extends DomainException{
    public SolIntentNotSameSizeException(Integer solutionSize, Integer intentSize){
        super(String.format("Solution has %d boles, intent has %d", solutionSize, intentSize));
    }
}
