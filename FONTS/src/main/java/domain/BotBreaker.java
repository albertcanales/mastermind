package domain;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker.
 *
 * @author Mar Gonzàlez Català
 */
abstract class BotBreaker {

    /**
     * Donada una solució genera la llista d'intents fins arribar a ella.
     *
     * @param solution
     * @return Llista d'intents.
     * @author Mar Gonzàlez Català
     */
    abstract ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> solution);
}