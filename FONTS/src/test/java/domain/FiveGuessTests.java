package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import exceptions.domain.InvalidNumBolesException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

@SuppressWarnings("MissingJavadoc")
public class FiveGuessTests {
    private static ArrayList<ArrayList<Integer>> sequencies;

    @SuppressWarnings("MissingJavadoc")
    @BeforeClass
    public static void beforeClass() {
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

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void solve() throws DomainException {
        BotBreaker fiveguess = new FiveGuess();
        for (ArrayList<Integer> sequencia : sequencies){
            List<List<Integer>> guesses = fiveguess.solve(sequencia);
            int numberOfGuesses = guesses.size();
            assertEquals("Solució",sequencia,guesses.get(numberOfGuesses-1));
            assertTrue("Nombre d'intents",numberOfGuesses <= 5);
        }
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void solveFirstTry() throws DomainException {
        BotBreaker fiveguess = new FiveGuess();
        List<Integer> firstTry = List.of(1,1,2,2);
            List<List<Integer>> guesses = fiveguess.solve(firstTry);
            int numberOfGuesses = guesses.size();
            assertEquals("Solució",firstTry,guesses.get(numberOfGuesses-1));
            assertTrue("Nombre d'intents",numberOfGuesses <= 5);

    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void solveWrongSizeOfSolution() {
        BotBreaker fiveguess = new FiveGuess();
        Integer[] sol = {1,1,2,3,4};
        List<Integer> solution = new ArrayList<>(Arrays.asList(sol));
        assertThrows(InvalidNumBolesException.class, () -> fiveguess.solve(solution));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void solveWrongSolution() {
        BotBreaker fiveguess = new FiveGuess();
        Integer[] sol1 = {-1,1,2,3};
        List<Integer> solution1 = new ArrayList<>(Arrays.asList(sol1));
        assertThrows(InvalidEnumValueException.class, () -> fiveguess.solve(solution1));
        Integer[] sol2 = {1,1,7,3};
        List<Integer> solution2 = new ArrayList<>(Arrays.asList(sol2));
        assertThrows(InvalidEnumValueException.class, () -> fiveguess.solve(solution2));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void getTipusAlgorisme() {
        BotBreaker fiveguess = new FiveGuess();
        assertEquals(TipusAlgorisme.FIVEGUESS,fiveguess.getTipusAlgorisme());
    }

    @SuppressWarnings("MissingJavadoc")
    public static void main(String[] args) {
        TestRunner.runTestClass(FiveGuessTests.class);
    }
}
