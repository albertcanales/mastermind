package domain;

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
        for (int it1 = 1; it1 <= Bola.numColors(); it1++){
            for (int it2 = 1; it2 <= Bola.numColors(); it2++){
                for (int it3 = 1; it3 <= Bola.numColors(); it3++){
                    for (int it4 = 1; it4 <= Bola.numColors(); it4++){
                        Integer[] finalit = {it1, it2, it3, it4};
                        ArrayList<Integer> sequence = new ArrayList<>(Arrays.asList(finalit));
                        sequencies.add(sequence);
                    }
                }
            }
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

}
