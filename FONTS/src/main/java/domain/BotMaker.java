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
     * @brief Constructor de BotMaker.
     * @param numboles llargada de la seqüència solució.
     * @param numcolors nombre de colors.
     * @author Mar Gonzàlez Català
     */
    public BotMaker(Integer numboles, Integer numcolors) {
        this.numboles = numboles;
        this.numcolors = numcolors;
    }

    /**
     * @brief Genera una solució.
     * @return Solució.
     * @author Mar Gonzàlez Català
     */
    public ArrayList<Integer> generaSequenciaSolucio(){
        Random rand = new Random();
        ArrayList<Integer> solution = new ArrayList<>(numboles);
        for (int i = 0; i < 4; i++)
            solution.set(i, rand.nextInt(numcolors) + 1);
        return solution;
    }
}