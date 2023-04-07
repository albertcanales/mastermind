package domain;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotMaker.
 *
 * @author Mar Gonzàlez Català
 */
class BotMaker {

    private final Integer numboles;

    public BotMaker(Integer numboles) {
        this.numboles = numboles;
    }

    /**
     * Genera una solució.
     *
     * @return Solució.
     * @author Mar Gonzàlez Català
     */
    public ArrayList<Integer> generaSequenciaSolucio(){
        Random rand = new Random();
        ArrayList<Integer> solution = new ArrayList<>(numboles);
        for (int i = 0; i < 4; i++)
            solution.set(i, rand.nextInt(Bola.NUMCOLORS) + 1);
        return solution;
    }
}