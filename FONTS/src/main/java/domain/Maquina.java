package domain;

import java.util.List;

/**
 * Interfície d'una màquina que juga al mastermind com a breaker
 * @author Teoria
 */
public interface Maquina {


    /**
     * Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the
     * genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find
     * the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes.
     * The operation will throw an exception in case the secret code solution is not consistent with the parameters
     * of the current game.
     * @param solution Solució de la partida
     * @return Llista d'intents que ha efectuat la màquina
     * @throws Exception Si hi ha hagut cap error en la resolució.
     */
    List<List<Integer>>  solve (List<Integer> solution) throws Exception;
}