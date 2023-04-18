package domain;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotMaker.
 *
 * @author Mar Gonzàlez Català
 */
class BotMaker {

    private final Integer numboles;
    private final Integer numcolors;

    /**
     * Constructor de BotMaker.
     * @param numboles llargada de la seqüència solució.
     * @param numcolors nombre de colors.
     */
    public BotMaker(Integer numboles, Integer numcolors) {
        this.numboles = numboles;
        this.numcolors = numcolors;
    }

    /**
     * Genera una solució.
     * @return Solució.
     */
    public ArrayList<Integer> generaSequenciaSolucio(){
        Random rand = new Random();
        ArrayList<Integer> solution = new ArrayList<>(numboles);
        for (int i = 0; i < numboles; i++)
            solution.add(rand.nextInt(numcolors) + 1);
        return solution;
    }
}