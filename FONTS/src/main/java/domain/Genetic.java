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
    private HashSet<Integer> population;

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

        ArrayList<ArrayList<Integer>> guesses = new ArrayList<>();
        ArrayList<Integer> currentGuess = new ArrayList<>();
        boolean won = false;

        while (!won){
            currentGuess = geneticCandidate();

            guesses.add(currentGuess);

            int black = compareTwoSequencesBlack(currentGuess,solution);
            if (black == 4) {
                won = true;
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