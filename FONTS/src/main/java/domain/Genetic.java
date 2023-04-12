package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor de les funcionalitats d'un BotBreaker que empra un algorisme genetic.
 *
 * @author Mar Gonzàlez Català
 */
class Genetic extends BotBreaker {

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella si utilitzem un algorisme genetic.
     *
     * @param solution
     * @return Llista d'intents.
     * @author Mar Gonzàlez Català
     */
    ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> solution) {
        return null;
    }

    /**
     * @brief Getter del tipus d'algorisme de Genetic
     * @author Mar Gonzàlez Català
     * */
    @Override
    TipusAlgorisme getTipusAlgorisme() {
        return TipusAlgorisme.GENETIC;
    }
}