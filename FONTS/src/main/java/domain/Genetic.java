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
     * Nombre de boles de la seqüència solució
     */
    private Integer numboles;

    private HashSet<Integer> possibleSolutions;
    private HashSet<Integer> population;
    private HashSet<Integer> tried;

    /**
     * Inicialitza el conjunt S de 1296 codis possibles.
     */
    private void initializeSetS() {
        for (int it1 = 1; it1 <= Bola.numColors(); it1++) {
            for (int it2 = 1; it2 <= Bola.numColors(); it2++) {
                for (int it3 = 1; it3 <= Bola.numColors(); it3++) {
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++) {
                        possibleSolutions.add(it1 * 1000 + it2 * 100 + it3 * 10 + it4);
                    }
                }
            }
        }
    }

    /**
     * Donades dues llistes retorna el nombre de boles amb color i posició coincidents.
     *
     * @param list1 primera llista  a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private Integer compareTwoSequencesBlack(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        Integer count = 0;
        for (int it = 0; it < this.numboles; ++it) {
            if (Objects.equals(list1.get(it), list2.get(it))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Donades dues llistes, retorna el nombre de boles amb color però no posició coincidents.
     *
     * @param list1 primera llista a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private Integer compareTwoSequencesWhite(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        Integer count = 0;
        boolean counted;
        ArrayList<Integer> added = new ArrayList<>(4);
        for (int it = 0; it < 4; it++) {
            added.add(0);
        }
        for (int it1 = 0; it1 < this.numboles; it1++) {
            counted = false;
            for (int it2 = 0; it2 < this.numboles && !counted; it2++) {
                if ((list1.get(it1).equals(list2.get(it2))) && (it1 != it2) && (added.get(it2).equals(0))) {
                    if (!(list1.get(it1).equals(list2.get(it1))) && !(list1.get(it2).equals(list2.get(it2)))) {
                        counted = true;
                        count++;
                        added.set(it2, 1);
                    }
                }
            }
        }
        return count;
    }

    /**
     * Donat un enter el converteix en un vector de dígits
     *
     * @return Vector de dígits de l'enter.
     */
    private ArrayList<Integer> getSequence(Integer intToTranslate) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int it = 0; it < this.numboles; it++) {
            digits.add(0, intToTranslate % 10);
            intToTranslate = intToTranslate / 10;
        }
        return digits;
    }

    /**
     * Donat un feedback i una seqüència elimina de S qualsevol dels codis que de ser solució no rebríem
     * aquest feedback si enviéssim com a intent la seqüència.
     *
     * @param black    nombre de boles negres en el feedback.
     * @param white    nombre de boles blanques en el feedback.
     * @param sequence seqüència donada.
     */
    private void eraseNotPossibleSolutionsfromSetS(int black, int white, ArrayList<Integer> sequence) {
        Iterator<Integer> it = possibleSolutions.iterator();
        while (it.hasNext()) {
            ArrayList<Integer> seqIteration = getSequence(it.next());
            if ((black != compareTwoSequencesBlack(seqIteration, sequence)) || white != compareTwoSequencesWhite(seqIteration, sequence)) {
                it.remove();
            }
        }
    }

    /**
     * Genera una població inicial
     */
    private void getInitialPopulation() {
        population = new HashSet<>();
        population.addAll(possibleSolutions);
    }


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
        population.clear();
        population.addAll(aux);
    }

    /**
     * Després d'aplicar uns quants cops l'operador genetic obté un candidat a solució.
     *
     * @return Candidat a solució.
     */
    private ArrayList<Integer> geneticCandidate() {
        getInitialPopulation();
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
     * @param sol Solució per la generació
     * @return Llista d'intents.
     */
    public List<List<Integer>> solve(List<Integer> sol) throws DomainException {
        ArrayList<Integer> solution = new ArrayList<>(sol);

        if (solution.size() != 4) {
            throw new InvalidNumBolesException(solution.size(), 4);
        }

        for (Integer bola : solution) {
            if (bola < 1 || bola > Bola.numColors()) {
                throw new InvalidEnumValueException("Bola", bola.toString());
            }
        }

        numboles = solution.size();
        tried = new HashSet<>();
        possibleSolutions = new HashSet<>();
        initializeSetS();

        ArrayList<ArrayList<Integer>> guesses = new ArrayList<>();
        ArrayList<Integer> currentGuess = new ArrayList<>();
        boolean won = false;

        currentGuess.add(1);
        currentGuess.add(1);
        currentGuess.add(2);
        currentGuess.add(2);
        guesses.add(currentGuess);
        tried.add(1122);

        //play the guess to get a response of colored and white pegs
        int black = compareTwoSequencesBlack(currentGuess, solution);
        int white = compareTwoSequencesWhite(currentGuess, solution);

        //if the response is four colored pegs, the game is won
        if (black == 4) {
            won = true;
        }
        //remove from S any code that would not give the same response
        else {
            eraseNotPossibleSolutionsfromSetS(black, white, currentGuess);
        }

        while (!won) {
            currentGuess = geneticCandidate();
            guesses.add(currentGuess);

            black = compareTwoSequencesBlack(currentGuess, solution);
            white = compareTwoSequencesWhite(currentGuess, solution);
            if (black == 4) {
                won = true;
            } else {
                eraseNotPossibleSolutionsfromSetS(black, white, currentGuess);
            }
        }
        return new ArrayList<>(guesses);
    }

    /**
     * Getter del tipus d'algorisme de Genetic
     */
    @Override
    TipusAlgorisme getTipusAlgorisme() {
        return TipusAlgorisme.GENETIC;
    }

}