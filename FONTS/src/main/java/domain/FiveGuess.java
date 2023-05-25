package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import exceptions.domain.InvalidNumBolesException;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker que empra l'algorisme Five Guess.
 * @author Mar Gonzàlez Català
 */
class FiveGuess extends BotBreaker {

    /**
     * Getter pel pitjor cas per la seqüència donada i solucions possibles
     * @return Pitjor cas
     */
    private Integer getCurrentMax(List<Integer> sequence) {
        List<Integer> counts = new ArrayList<>(45);
        for (int it = 0; it < 45; it++){
            counts.add(0);
        }
        for (Integer integer : possibleSolutions) {
            List<Integer> seqIteration = getSequence(integer);
            int black = compareTwoSequencesBlack(seqIteration, sequence);
            int white = compareTwoSequencesWhite(seqIteration, sequence);
            int index = black * 10 + white;
            counts.set(index, counts.get(index) + 1);
        }
        return Collections.max(counts);
    }

    /**
     * Escull la següent seqüència mitjançant la tècnica minimax.
     * @return Següent seqüència
     */
    private List<Integer> minimax(){
        int currentMinimax = 1297;
        List<Integer> currentSeqMinimax = new ArrayList<>();
        for (int it1 = 1; it1 <= Bola.numColors(); it1++) {
            for (int it2 = 1; it2 <= Bola.numColors(); it2++) {
                for (int it3 = 1; it3 <= Bola.numColors(); it3++) {
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++) {
                        List<Integer> sequence = new ArrayList<>(List.of(it1, it2, it3, it4));
                        Integer currentMax = getCurrentMax(sequence);
                        if (currentMax < currentMinimax || currentMax == currentMinimax && possibleSolutions.contains(getInt(sequence))) {
                            currentMinimax = currentMax;
                            currentSeqMinimax = sequence;
                        }
                    }
                }
            }
        }
        return currentSeqMinimax;
    }

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella si utilitzem l'algorisme Five Guess
     * @param solution seqüència oculta que volem endevinar
     * @return Llista d'intents
     */
    @Override
    public List<List<Integer>> solve(List<Integer> solution) throws DomainException {
        if (solution.size() != 4)
            throw new InvalidNumBolesException(solution.size(), 4);

        for (Integer bola : solution)
            if (bola < 1 || bola > Bola.numColors())
                throw new InvalidEnumValueException("Bola", bola.toString());

        numboles = solution.size();

        List<List<Integer>> guesses = new ArrayList<>();
        initializePossibleSolutions();

        List<Integer> currentGuess = new ArrayList<>(List.of(1, 1, 2, 2));
        guesses.add(currentGuess);

        while(!checkGuess(currentGuess, solution)) {
            currentGuess = minimax();
            guesses.add(currentGuess);
        }

        return guesses;
    }

    /**
     * Getter del tipus d'algorisme de FiveGuess
     * */
    @Override
    TipusAlgorisme getTipusAlgorisme() {
        return TipusAlgorisme.FIVEGUESS;
    }
}
