package domain;

import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.InvalidNumBolesException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
public class BotMakerTests {
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
}