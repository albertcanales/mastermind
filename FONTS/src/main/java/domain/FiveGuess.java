package domain;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker que empra l'algorisme Five Guess.
 *
 * @author Mar Gonzàlez Català
 */
class FiveGuess extends BotBreaker {
    //conjunt de codis possibles
    static HashSet<Integer> S;

    /**
     * @brief Inicialitza el conjunt S de 1296 codis possibles.
     *
     * @author Mar Gonzàlez Català
     */
    private static void initializeSetS(){
        for (Integer it1 = 1; it1 <= Bola.NUMCOLORS; it1++){
            for (Integer it2 = 1; it2 <= Bola.NUMCOLORS; it2++){
                for (Integer it3 = 1; it3 <= Bola.NUMCOLORS; it3++){
                    for (Integer it4 = 1; it4 <= Bola.NUMCOLORS; it4++){
                        S.add(it1*1000+it2*100+it3*10+it4);
                    }
                }
            }
        }
    }

    /**
     * @brief Donades dues llistes retorna el nombre de boles amb color i posició coincidents.
     *
     * @param list1, list2 a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private static Integer compareTwoSequencesBlack(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Integer count = 0;
        for (int it = 0; it < 4; ++it){
            if (list1.get(it) == list2.get(it)){
                count++;
            }
        }
        return count;
    }

    /**
     * @brief Donades dues llistes retorna el nombre de boles amb color però no posició coincidents.
     *
     * @param list1, list2 a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private static Integer compareTwoSequencesWhite(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Integer count = 0;
        for (int it1 = 0; it1 < 4; ++it1){
            for (int it2 = 0; it2 < it1; it2++) {
                if ((list1.get(it1) == list2.get(it2)) && (list1.get(it1) != list2.get(it2)) && (list1.get(it2) != list2.get(it2))) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @brief Donat un enter el converteix en un vector de dígits
     *
     * @return Vector de dígits de l'enter.
     */
    private static ArrayList<Integer> getSequence(Integer intToTranslate){
        ArrayList<Integer> digits = new ArrayList<Integer>();
        for (Integer it = 0; it < 4; it++){
            digits.add(0,intToTranslate%10);
            intToTranslate = intToTranslate/10;
        }
        return digits;
    }

    /**
     * @brief Donat un feedback i una seqüència elimina de S qualsevol dels codis que de ser solució no rebriem
     * aquest feedback si enviéssim com a intent la seqüència.
     *
     * @param black,white feedback (nombre de boles negres i nombre de boles blanques) i una seqüència.
     * @author Mar Gonzàlez Català
     */
    private static void eraseNotPossibleSolutionsfromSetS(int black, int white, ArrayList<Integer> sequence){
        Iterator<Integer> it = S.iterator();
        while (it.hasNext()){
            ArrayList<Integer> seqIteration = getSequence(it.next());
            if ((black != compareTwoSequencesBlack(seqIteration, sequence)) || white != compareTwoSequencesWhite(seqIteration, sequence)){
                it.remove();
            }
        }
    }

    /**
     * @brief Escull la següent seqüència mitjançant la tècnica minimax.
     *
     * @return Següent seqüència
     * @author Mar Gonzàlez Català
     */
    private static ArrayList<Integer> minimax(){
        Integer currentMinimax = 1297;
        ArrayList<Integer> currentSeqMinimax = new ArrayList<Integer>();
        for (Integer it1 = 1; it1 <= Bola.NUMCOLORS; it1++) {
            for (Integer it2 = 1; it2 <= Bola.NUMCOLORS; it2++) {
                for (Integer it3 = 1; it3 <= Bola.NUMCOLORS; it3++) {
                    for (Integer it4 = 1; it4 <= Bola.NUMCOLORS; it4++) {
                        Integer finalIt1 = it1;
                        Integer finalIt2 = it2;
                        Integer finalIt3 = it3;
                        Integer finalIt4 = it4;
                        ArrayList<Integer> sequence = new ArrayList<Integer>() {{
                            add(finalIt1);
                            add(finalIt1);
                            add(finalIt3);
                            add(finalIt4);
                        }};
                        ArrayList<Integer> counts = new ArrayList<Integer>(Collections.nCopies(17,0));
                        Iterator<Integer> it = S.iterator();
                        while (it.hasNext()) {
                            ArrayList<Integer> seqIteration = getSequence(it.next());
                            int black = compareTwoSequencesBlack(seqIteration, sequence);
                            int white = compareTwoSequencesWhite(seqIteration, sequence);
                            int index = black * 4 + white;
                            Integer value = counts.get(index);
                            value = value + 1;
                            counts.set(index, value);
                        }
                        Integer currentMax = 0;
                        for (int i = 0; i < 15; i++) {
                            if (counts.get(i) > currentMax) {
                                currentMax = counts.get(i);
                            }
                        }
                        if (currentMax < currentMinimax) {
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
     * @brief Donada una solució genera la llista d'intents fins arribar a ella si utilitzem l'algorisme Five Guess.
     *
     * @param solution
     * @return Llista d'intents.
     * @author Mar Gonzàlez Català
     */
    public ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> solution){
        ArrayList<ArrayList<Integer>> guesses = new ArrayList<ArrayList<Integer>>();
        boolean won = false;

        //create set S of 1296 possible codes
        S = new HashSet<Integer>();
        initializeSetS();

        //start with initial guess 1122
        ArrayList<Integer> currentGuess = new ArrayList<Integer>();
        currentGuess.add(1); currentGuess.add(1); currentGuess.add(2); currentGuess.add(2);

        guesses.add(currentGuess);

        //play the guess to get a response of colored and white pegs
        //TODO get Kamil's function
        int black = compareTwoSequencesBlack(currentGuess,solution);
        int white = compareTwoSequencesWhite(currentGuess,solution);

        //if the response is four colored pegs, the game is won
        //TODO get Kamil's function
        if (black == 4) {
            won = true;
        }
        //remove from S any code that would not give the same response
        else {
            eraseNotPossibleSolutionsfromSetS(black, white, currentGuess);
        }

        while(!won) {
            //choose next guess with the minimax technique
            currentGuess = minimax();
            guesses.add(currentGuess);

            //play the guess to get a response of colored and white pegs
            //TODO get Kamil's function
            black = compareTwoSequencesBlack(currentGuess,solution);
            white = compareTwoSequencesWhite(currentGuess,solution);

            //if the response is four colored pegs, the game is won
            //TODO get Kamil's function
            if (black == 4) {
                won = true;
            }
            //remove from S any code that would not give the same response
            else {
                eraseNotPossibleSolutionsfromSetS(black, white, currentGuess);
            }
        }
        return guesses;
    }
}