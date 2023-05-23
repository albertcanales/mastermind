package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import exceptions.domain.InvalidNumBolesException;

import java.util.*;

/**
 * Classe que representa un BotBreaker, és a dir, la màquina que desxifra codis
 * @author Mar Gonzàlez Català
 */
abstract class BotBreaker implements Maquina {

    /**
     * Nombre de boles de la seqüència solució
     */
    Integer numboles;

    /**
     * Conjunt de codis possibles
     */
    HashSet<Integer> possibleSolutions;

    /**
     * Constructor de BotMaker.
     * @param tipusAlgorisme tipus d'Algorisme.
     */
    static BotBreaker create(Integer tipusAlgorisme) throws DomainException {
        switch (TipusAlgorisme.findByNumber(tipusAlgorisme)) {
            case FIVEGUESS:
                return new FiveGuess();
            case GENETIC:
                return new Genetic();
        }
        return null;
    }

    /**
     * Getter del tipus d'algorisme
     */
    abstract TipusAlgorisme getTipusAlgorisme();

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella.
     * @param solution seqüència oculta que intentem endevinar.
     * @return Llista d'intents.
     */
    public abstract List<List<Integer>> solve(List<Integer> solution) throws DomainException;

    /**
     * Inicialitza el conjunt de 1296 codis possibles.
     */
    void initializeSetS() {
        possibleSolutions = new HashSet<>();
        for (int it1 = 1; it1 <= Bola.numColors(); it1++)
            for (int it2 = 1; it2 <= Bola.numColors(); it2++)
                for (int it3 = 1; it3 <= Bola.numColors(); it3++)
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++)
                        possibleSolutions.add(it1 * 1000 + it2 * 100 + it3 * 10 + it4);
    }

    /**
     * Donat un enter el converteix en un vector de dígits
     *
     * @return Vector de dígits de l'enter.
     */
    List<Integer> getSequence(Integer intToTranslate) {
        List<Integer> digits = new ArrayList<>();
        for (int it = 0; it < this.numboles; it++) {
            digits.add(0, intToTranslate % 10);
            intToTranslate = intToTranslate / 10;
        }
        return digits;
    }

    /**
     * Donades dues llistes retorna el nombre de boles amb color i posició coincidents.
     *
     * @param list1 primera llista  a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    Integer compareTwoSequencesBlack(List<Integer> list1, List<Integer> list2) {
        Integer count = 0;
        for (int it = 0; it < this.numboles; ++it) {
            if (list1.get(it).equals(list2.get(it))) {
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
     * @return Nombre de boles amb color però no posició coincidents.
     */
    Integer compareTwoSequencesWhite(List<Integer> list1, List<Integer> list2) {
        Integer count = 0;
        boolean counted;
        List<Integer> added = new ArrayList<>(this.numboles);
        for (int it = 0; it < this.numboles; it++) {
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
     * Donat un feedback i una seqüència elimina de S qualsevol dels codis que de ser solució no rebríem
     * aquest feedback si enviéssim com a intent la seqüència.
     *
     * @param black    nombre de boles negres en el feedback.
     * @param white    nombre de boles blanques en el feedback.
     * @param sequence seqüència donada.
     */
    private void eraseNotPossibleSolution(int black, int white, List<Integer> sequence) {
        Iterator<Integer> it = possibleSolutions.iterator();
        while (it.hasNext()) {
            List<Integer> seqIteration = getSequence(it.next());
            if ((black != compareTwoSequencesBlack(seqIteration, sequence)) || white != compareTwoSequencesWhite(seqIteration, sequence)) {
                it.remove();
            }
        }
    }

    /**
     * Comprova si la guess donada és la solució. Si no ho és, l'esborra del conjunt de possibles solucions
     *
     * @param currentGuess Guess a comprovar
     * @param solution Solució a comprovar
     * @return Si la solució és correcta
     */
    Boolean checkGuess(List<Integer> currentGuess, List<Integer> solution) {
        int black = compareTwoSequencesBlack(currentGuess, solution);
        int white = compareTwoSequencesWhite(currentGuess, solution);

        if (black == this.numboles)
            return true;
        eraseNotPossibleSolution(black, white, currentGuess);
        return false;
    }
}

/**
 * Enum dels diferents algorismes que pot utilitzar el BotBreaker
 * @author Mar Gonzàlez Català
 */
enum TipusAlgorisme {
    FIVEGUESS(1),
    GENETIC(2);

    /**
     * Nombre que representa el tipus d'algorisme
     */
    private final int number;

    /**
     * onstructor a partir de l'enter que representa el valor
     * @param number és el valor que representa el tipus d'algorisme
     */
    TipusAlgorisme(int number) {
        this.number = number;
    }

    /**
     * Mètode per obtenir el TipusAlgorisme corresponent a un nombre
     * @param number és el valor que representa el tipus d'algorisme
     * @throws DomainException si el nombre no correspon a un algorisme
     * @return el valor de TipusAlgorisme corresponent al nombre donat
     */
    static TipusAlgorisme findByNumber(Integer number) throws DomainException {
        for(TipusAlgorisme ta : values()){
            if( ta.number() == number){
                return ta;
            }
        }
        throw new InvalidEnumValueException("Algorisme", number.toString());
    }

    /**
     * Getter del nombre que representa el valor
     */
    int number() { return number; }

    /**
     * Mètode per obtenir el nombre d'algorismes
     * @return enter que representa el número d'algorismes
     */
    static int numAlgorismes() {
        return (values().length);
    }

    /**
     * Mètode per saber si un número representa un algorisme vàlid
     * @param num enter que representa un algorisme
     * @return un booleà cert si el número correspon a un algorisme, fals si no
     */
    static boolean isValid(int num) {
        return (num > 0) && (num <= numAlgorismes());
    }
}