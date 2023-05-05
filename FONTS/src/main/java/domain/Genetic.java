package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.InvalidNumBolesException;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker que empra un algorisme genetic.
 *
 * @author Mar Gonzàlez Català
 */
class Genetic extends BotBreaker {

    private Integer numboles;
    private HashSet<Integer> possibleSolutions;
    private HashSet<Integer> population;

    /**
     * Inicialitza el conjunt S de 1296 codis possibles.
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
     * Donades dues llistes retorna el nombre de boles amb color i posició coincidents.
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
     * Donades dues llistes, retorna el nombre de boles amb color però no posició coincidents.
     * @param list1 primera llista a comparar.
     * @param list2 segona llista a comparar.
     * @return Nombre de boles amb color i posició coincidents.
     */
    private Integer compareTwoSequencesWhite(ArrayList<Integer> list1, ArrayList<Integer> list2){
        Integer count = 0;
        boolean counted;
        ArrayList<Integer> added = new ArrayList<>(4);
        for (int it = 0; it < 4; it++){
            added.add(0);
        }
        for (int it1 = 0; it1 < this.numboles; it1++){
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
     * @return Vector de dígits de l'enter.
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
     * Donat un feedback i una seqüència elimina de S qualsevol dels codis que de ser solució no rebríem
     * aquest feedback si enviéssim com a intent la seqüència.
     * @param black nombre de boles negres en el feedback.
     * @param white nombre de boles blanques en el feedback.
     * @param sequence seqüència donada.
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
     * Escull la següent seqüència mitjançant la tècnica minimax.
     * @return Següent seqüència
     */
    private ArrayList<Integer> minimax(){
        ArrayList<Integer> valors = new ArrayList<Integer>(1296);
        int currentMinimax = 1297;
        boolean isInS = false;
        ArrayList<Integer> currentSeqMinimax = new ArrayList<>();
        for(Integer seq : population){
            ArrayList<Integer> sequence = getSequence(seq);
            ArrayList<Integer> counts = new ArrayList<>(45);
            for (int it = 0; it < 45; it++){
                counts.add(0);
            }
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
            valors.add(currentMax * 10000 + sequence.get(0) * 1000 + sequence.get(1) * 100 + sequence.get(2) * 10 + sequence.get(3));
        }
        Collections.sort(valors);
        population.clear();
        int mida = valors.size()/2;
        for (int i = 0; i <= mida; i++){
            population.add(valors.get(i)%10000);
        }
        return currentSeqMinimax;
    }

    /**
     * Genera una població inicial de 128 seqüències.
     */
    private void getInitialPopulation() {
        population = new HashSet<>();
        Random random = new Random();
        while (population.size() < 128) {
            Integer sequencia = 0;
            for (int i = 0; i < 4; i++) {
                Integer bola = random.nextInt(6) + 1;
                sequencia = sequencia*10 + bola;
            }
            population.add(sequencia);
        }
    }



    /**
     * Combina dues seqüències per obtenir-ne una tercera que és combinació de les dues anteriors.
     * @param seq1
     * @param seq2
     * @return seqüència combinació
     */
    private Integer combineTwoSequences(Integer seq1, Integer seq2) {
        return (seq1/100)*100+seq2%100;
    }

    /**
     * Aplica un cop l'operador genetic a la població actual per obtenir la següent generació.
     */
    private void getNextGeneration() {
        HashSet<Integer> aux = new HashSet<>();
        Iterator<Integer> iterator = population.iterator();
        while (iterator.hasNext()){
            int seq1 = iterator.next();
            if (iterator.hasNext()) {
                int seq2 = iterator.next();
                int seq = combineTwoSequences(seq1,seq2);
                aux.add(seq);
            }
        }
        population.clear();
        for (Integer seq : aux){
            population.add(seq);
        }
    }

    /**
     * Després d'aplicar uns quants cops l'operador genetic obté un candidat a solució.
     *
     * @return Candidat a solució.
     */
    private ArrayList<Integer> geneticCandidate(){
        getInitialPopulation();
        while (population.size() > 1){
            minimax();
            getNextGeneration();
        }
        Integer chosen = population.iterator().next();
        ArrayList<Integer> candidate = getSequence(chosen);
        return candidate;
    }

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella si utilitzem un algorisme genetic.
     *
     * @param sol
     * @return Llista d'intents.
     */
    public List<List<Integer>> solve(List<Integer> sol) throws DomainException {
        ArrayList<Integer> solution = new ArrayList<>(sol);

        if (solution.size() != 4) {
            throw new InvalidNumBolesException(solution.size(),4);
        }

        for (Integer bola : solution){
            if (bola < 1 || bola > Bola.numColors()){
                throw new InvalidEnumValueException("Bola",bola.toString());
            }
        }

        numboles = solution.size();

        possibleSolutions = new HashSet<>();
        initializeSetS();

        ArrayList<ArrayList<Integer>> guesses = new ArrayList<>();
        ArrayList<Integer> currentGuess = new ArrayList<>();
        boolean won = false;

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

        while (!won){
            currentGuess = geneticCandidate();

            guesses.add(currentGuess);

            black = compareTwoSequencesBlack(currentGuess,solution);
            white = compareTwoSequencesWhite(currentGuess,solution);
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
     * */
    @Override
    TipusAlgorisme getTipusAlgorisme() {
        return TipusAlgorisme.GENETIC;
    }
    public static void main(String[] args) throws Exception {
        List<Integer> solution = new ArrayList<Integer>() {{
            add(6);
            add(5);
            add(4);
            add(6);
        }};
        List<List<Integer>> sortidaFiveGuess = (new Genetic()).solve(solution);

        for (int i = 0; i < sortidaFiveGuess.size(); i++){
            for (int j = 0; j < sortidaFiveGuess.get(i).size(); j++){
                System.out.println("Al torn " + i + " bola " + j + " és :" + sortidaFiveGuess.get(i).get(j));
            }
            System.out.println();
        }
    }
}