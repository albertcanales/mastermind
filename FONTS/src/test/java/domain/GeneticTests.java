package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import exceptions.domain.InvalidNumBolesException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
public class GeneticTests {
    static ArrayList<ArrayList<Integer>> sequencies;

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

    @Test
    public void solve() throws DomainException {
        BotBreaker genetic = new Genetic();
        for (ArrayList<Integer> sequencia : sequencies){
            List<List<Integer>> guesses = genetic.solve(sequencia);
            int numberOfGuesses = guesses.size();
            assertEquals("Solució",sequencia,guesses.get(numberOfGuesses-1));
        }
    }

    @Test
    public void solveFirstTry() throws DomainException {
        BotBreaker fiveguess = new FiveGuess();
        List<Integer> firstTry = List.of(1,1,2,2);
        List<List<Integer>> guesses = fiveguess.solve(firstTry);
        int numberOfGuesses = guesses.size();
        assertEquals("Solució",firstTry,guesses.get(numberOfGuesses-1));
        assertTrue("Nombre d'intents",numberOfGuesses <= 5);

    }

    @Test
    public void solveWrongSizeOfSolution() {
        BotBreaker genetic = new Genetic();
        Integer[] sol = {1,1,2,3,4};
        ArrayList<Integer> solution = new ArrayList<>(Arrays.asList(sol));
        assertThrows(InvalidNumBolesException.class, () -> genetic.solve(solution));
    }

    @Test
    public void solveWrongSolution() {
        BotBreaker genetic = new Genetic();
        Integer[] sol1 = {-1,1,2,3};
        ArrayList<Integer> solution1 = new ArrayList<>(Arrays.asList(sol1));
        assertThrows(InvalidEnumValueException.class, () -> genetic.solve(solution1));
        Integer[] sol2 = {1,1,7,3};
        ArrayList<Integer> solution2 = new ArrayList<>(Arrays.asList(sol2));
        assertThrows(InvalidEnumValueException.class, () -> genetic.solve(solution2));
    }

    @Test
    public void getTipusAlgorisme() {
        BotBreaker genetic = new Genetic();
        assertEquals(TipusAlgorisme.GENETIC,genetic.getTipusAlgorisme());
    }

    public static void main(String[] args) {
        TestRunner.runTestClass(GeneticTests.class);
    }
}

