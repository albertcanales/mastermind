package domain;

import java.util.*;

/**
 * @brief Contenidor de les funcionalitats d'un BotBreaker que empra l'algorisme Five Guess.
 * @author Mar Gonzàlez Català
 */
class FiveGuess extends BotBreaker {
    private Integer numboles;

    //conjunt de codis possibles (S a la viquipèdia)
    private HashSet<Integer> possibleSolutions;

    /**
     * @brief Inicialitza el conjunt S de 1296 codis possibles.
     * @author Mar Gonzàlez Català
     */
    private void initializeSetS(){
        for (int it1 = 1; it1 <= Bola.numColors(); it1++){
            for (int it2 = 1; it2 <= Bola.numColors(); it2++){
                for (int it3 = 1; it3 <= Bola.numColors(); it3++){
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++){
                        possibleSolutions.add(it1*1000+it2*100+it3*10+it4);
                    }
                }
            }
        }
    }

    /**
     * @brief Donades dues llistes retorna el nombre de boles amb color i posició coincidents.
     * @param list1 primera llista  a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private Integer compareTwoSequencesBlack(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Integer count = 0;
        for (int it = 0; it < this.numboles; ++it){
            if (Objects.equals(list1.get(it), list2.get(it))){
                count++;
            }
        }
        return count;
    }

    /**
     * @brief Donades dues llistes retorna el nombre de boles amb color però no posició coincidents.
     * @param list1 primera llista a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private Integer compareTwoSequencesWhite(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Integer count = 0;
        for (int it1 = 0; it1 < this.numboles; it1++){
            ArrayList<Integer> added = new ArrayList<>(Collections.nCopies(4, 0));
            for (int it2 = 0; it2 < this.numboles; it2++) {
                if ((list1.get(it1).equals(list2.get(it2))) && (it1 != it2) && (added.get(it2).equals(0))) {
                    if (!(list1.get(it1).equals(list2.get(it1))) && !(list1.get(it2).equals(list2.get(it2)))) {
                        count++;
                        added.set(it2, 1);
                    }
                }
            }
        }
        return count;
    }

    /**
     * @brief Donat un enter el converteix en un vector de dígits
     * @return Vector de dígits de l'enter.
     * @author Mar Gonzàlez Català
     */
    private ArrayList<Integer> getSequence(Integer intToTranslate){
        ArrayList<Integer> digits = new ArrayList<>();
        for (int it = 0; it < this.numboles; it++){
            digits.add(0,intToTranslate%10);
            intToTranslate = intToTranslate/10;
        }
        return digits;
    }

    /**
     * @brief Donat un feedback i una seqüència elimina de S qualsevol dels codis que de ser solució no rebríem
     * aquest feedback si enviéssim com a intent la seqüència.
     * @param black nombre de boles negres en el feedback.
     * @param white nombre de boles blanques en el feedback.
     * @param sequence seqüència donada.
     * @author Mar Gonzàlez Català
     */
    private void eraseNotPossibleSolutionsfromSetS(int black, int white, ArrayList<Integer> sequence){
        Iterator<Integer> it = possibleSolutions.iterator();
        while (it.hasNext()){
            ArrayList<Integer> seqIteration = getSequence(it.next());
            if ((black != compareTwoSequencesBlack(seqIteration, sequence)) || white != compareTwoSequencesWhite(seqIteration, sequence)){
                it.remove();
            }
        }
    }

    /**
     * @brief Escull la següent seqüència mitjançant la tècnica minimax.
     * @return Següent seqüència
     * @author Mar Gonzàlez Català
     */
    private ArrayList<Integer> minimax(){
        int currentMinimax = 1297;
        Boolean isInS = false;
        ArrayList<Integer> currentSeqMinimax = new ArrayList<>();
        for (int it1 = 1; it1 <= Bola.numColors(); it1++) {
            for (int it2 = 1; it2 <= Bola.numColors(); it2++) {
                for (int it3 = 1; it3 <= Bola.numColors(); it3++) {
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++) {
                        Integer[] finalit = {it1, it2, it3, it4};
                        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(finalit));
                        ArrayList<Integer> counts = new ArrayList<>(Collections.nCopies(45, 0));
                        for (Integer integer : possibleSolutions) {
                            ArrayList<Integer> seqIteration = getSequence(integer);
                            int black = compareTwoSequencesBlack(seqIteration, sequence);
                            int white = compareTwoSequencesWhite(seqIteration, sequence);
                            int index = black * 10 + white;
                            Integer value = counts.get(index);
                            value = value + 1;
                            counts.set(index, value);
                        }
                        Integer currentMax = 0;
                        for (Integer count : counts) {
                            if (count > currentMax) {
                                currentMax = count;
                            }
                        }
                        if (currentMax < currentMinimax) {
                            currentMinimax = currentMax;
                            currentSeqMinimax = sequence;
                            if (possibleSolutions.contains(sequence.get(0)*1000+sequence.get(1)*100+sequence.get(2)*10+sequence.get(3))){
                                isInS = true;
                            } else {
                                isInS = false;
                            }
                        } else if ((currentMax == currentMinimax) && !isInS){
                            if (possibleSolutions.contains(sequence.get(0)*1000+sequence.get(1)*100+sequence.get(2)*10+sequence.get(3))){
                                currentSeqMinimax = sequence;
                                isInS = true;
                            }
                        }
                    }
                }
            }
        }
        return currentSeqMinimax;
    }


    /**
     * @brief Donada una solució genera la llista d'intents fins a arribar a ella si utilitzem l'algorisme Five Guess.
     * @param solution seqüència oculta que volem endevinar.
     * @return Llista d'intents.
     * @author Mar Gonzàlez Català
     */
    @Override
    public ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> solution){
        numboles = solution.size();

        ArrayList<ArrayList<Integer>> guesses = new ArrayList<>();
        boolean won = false;

        //create set S of 1296 possible codes
        possibleSolutions = new HashSet<>();
        initializeSetS();

        //start with initial guess 1122
        ArrayList<Integer> currentGuess = new ArrayList<>();
        currentGuess.add(1); currentGuess.add(1); currentGuess.add(2); currentGuess.add(2);
        guesses.add(currentGuess);

        //play the guess to get a response of colored and white pegs
        int black = compareTwoSequencesBlack(currentGuess,solution);
        int white = compareTwoSequencesWhite(currentGuess,solution);

        //if the response is four colored pegs, the game is won
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
            black = compareTwoSequencesBlack(currentGuess,solution);
            white = compareTwoSequencesWhite(currentGuess,solution);

            //if the response is four colored pegs, the game is won
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