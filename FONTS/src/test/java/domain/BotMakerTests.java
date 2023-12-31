package domain;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
@SuppressWarnings("MissingJavadoc")
public class BotMakerTests {
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void generaSequenciaSolucio() {
        BotMaker botmaker = new BotMaker(4,6);
        for (int numTest = 0; numTest < 10; numTest++){
            ArrayList<Integer> solucio = botmaker.generaSequenciaSolucio();
            assertEquals("Mida solució",solucio.size(),4);
            for (Integer bola : solucio){
                assertTrue("Bola incorrecta",bola >= 1 && bola <= 6);
            }
        }
    }

    @SuppressWarnings("MissingJavadoc")
    public static void main(String[] args) {
        TestRunner.runTestClass(BotMakerTests.class);
    }
}
