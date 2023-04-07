package domain;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotMaker.
 *
 * @author Mar Gonzàlez Català
 */
class BotMaker {

    /**
     * Genera una solució.
     *
     * @return Solució.
     * @author Mar Gonzàlez Català
     */
    public ArrayList<Integer> generaSequenciaSolucio(){
        ArrayList<Integer> solution = new ArrayList<Integer>(Taulell.NUMBOLES);
        for (int i = 0; i < 4; i++){
            Random rand = new Random();
            solution.set(i, rand.nextInt(Bola.NUMCOLORS) + 1);
        }
        return solution;
    }
}