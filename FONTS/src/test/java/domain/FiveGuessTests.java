package domain;

import domain.exceptions.InvalidNumBolesException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
public class FiveGuessTests {
    static ArrayList<ArrayList<Integer>> sequencies;

    @BeforeClass
    public static void beforeClass() {
        System.err.println("Carregant les dades");
        sequencies = new ArrayList<>();
        Random rand = new Random();
        for (int it = 0; it < 50; it++){
            int bola1 = rand.nextInt(Bola.numColors()) + 1;
            int bola2 = rand.nextInt(Bola.numColors()) + 1;
            int bola3 = rand.nextInt(Bola.numColors()) + 1;
            int bola4 = rand.nextInt(Bola.numColors()) + 1;
            Integer[] seq = {bola1, bola2, bola3, bola4};
            ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(seq));
            sequencies.add(sequence);
        }
    }

    @Test
    public void solve() throws Exception {
        System.err.println("Inici test solve");
        BotBreaker fiveguess = new FiveGuess();
        for (ArrayList<Integer> sequencia : sequencies){
            ArrayList<ArrayList<Integer>> guesses = fiveguess.solve(sequencia);
            int numberOfGuesses = guesses.size();
            assertEquals("Solució",sequencia,guesses.get(numberOfGuesses-1));
            assertTrue("Nombre d'intents",numberOfGuesses <= 5);
        }
        System.err.println("Fi del test solve");
    }

    @Test
    public void solveWrongSizeOfSolution() {
        BotBreaker fiveguess = new FiveGuess();
        Integer[] sol = {1,1,2,3,4};
        ArrayList<Integer> solution = new ArrayList<>(Arrays.asList(sol));
        assertThrows(InvalidNumBolesException.class, () -> {
            fiveguess.solve(solution);
        });
    }

}
