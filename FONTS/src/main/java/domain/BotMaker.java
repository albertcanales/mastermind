package domain;

import java.util.*;

/**
 * Classe que representa un BotMaker, és a dir, la màquina creadora de codis
 * @author Mar Gonzàlez Català
 */
class BotMaker {

    /**
     * Nombre de boles de la seqüència solució
     */
    private final Integer numboles;

    /**
     * Nombre de colors de la seqüència solució
     */
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