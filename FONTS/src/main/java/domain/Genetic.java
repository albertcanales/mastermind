package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import exceptions.domain.InvalidNumBolesException;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker que empra un algorisme genetic.
 *
 * @author Mar Gonzàlez Català
 */
class Genetic extends BotBreaker {

    /**
     * Població actual
     */
    private HashSet<Integer> population;

    /**
     * Conjunt de seqüències que ja s'han intentat
     */
    private HashSet<Integer> tried;

    /**
     * Combina dues seqüències per obtenir-ne una tercera que és combinació de les dues anteriors.
     *
     * @param seq1 Primera combinació a combinar
     * @param seq2 Segona combinació a combinar
     * @return seqüència combinació
     */
    private Integer combineTwoSequences(Integer seq1, Integer seq2) {
        return (seq1 / 100) * 100 + seq2 % 100;
    }

    /**
     * Aplica un cop l'operador genetic a la població actual per obtenir la següent generació.
     */
    private void getNextGeneration() {
        HashSet<Integer> aux = new HashSet<>();
        Iterator<Integer> iterator = population.iterator();
        while (iterator.hasNext()) {
            int seq1 = iterator.next();
            if (iterator.hasNext()) {
                int seq2 = iterator.next();
                int seq = combineTwoSequences(seq1, seq2);
                if (tried.contains(seq)) {
                    aux.add(seq1);
                } else {
                    aux.add(seq);
                }
            }
        }
        population = aux;
    }

    /**
     * Després d'aplicar uns quants cops l'operador genetic obté un candidat a solució.
     *
     * @return Candidat a solució.
     */
    private List<Integer> geneticCandidate() {
        population = possibleSolutions;
        while (population.size() > 1) {
            getNextGeneration();
        }
        Integer chosen = population.iterator().next();
        tried.add(chosen);
        return getSequence(chosen);
    }

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella si utilitzem un algorisme genetic.
     *
     * @param solution Solució per la generació
     * @return Llista d'intents.
     */
    public List<List<Integer>> solve(List<Integer> solution) throws DomainException {
        if (solution.size() != 4)
            throw new InvalidNumBolesException(solution.size(), 4);

        for (Integer bola : solution)
            if (bola < 1 || bola > Bola.numColors())
                throw new InvalidEnumValueException("Bola", bola.toString());

        numboles = solution.size();
        
        tried = new HashSet<>();
        initializePossibleSolutions();

        List<List<Integer>> guesses = new ArrayList<>();
        List<Integer> currentGuess = new ArrayList<>(List.of(1, 1, 2, 2));
        guesses.add(currentGuess);
        tried.add(getInt(currentGuess));

        while (!checkGuess(currentGuess, solution)) {
            currentGuess = geneticCandidate();
            guesses.add(currentGuess);
        }
        return guesses;
    }

    /**
     * Getter del tipus d'algorisme de Genetic
     */
    @Override
    TipusAlgorisme getTipusAlgorisme() {
        return TipusAlgorisme.GENETIC;
    }

}