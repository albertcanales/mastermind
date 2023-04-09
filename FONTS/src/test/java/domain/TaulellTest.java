package domain;

import domain.exceptions.invalidEnumValue;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaulellTest {
    //TODO assertThrows d'invalidListSize
    @Test
    public void newTaulellBuit() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));
        List<List <Integer>> expected = new ArrayList<>(Taulell.NUMINTENTS);

        for (int i = 0; i < Taulell.NUMINTENTS; ++i) { //init to empty
            expected.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));
        }

        Taulell taulell = new Taulell(solucio);
        assertEquals(solucio,taulell.getSolucio());
        assertEquals(expected,taulell.getIntents());
        assertEquals(expected,taulell.getFeedbacks());
    }

    @Test
    public void newTaulellInit() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<List <Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));

        List<List <Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));


        Taulell taulell = new Taulell(solucio,expectedint,expectedfeed);
        assertEquals(solucio,taulell.getSolucio());
        assertEquals(expectedint,taulell.getIntents());
        assertEquals(expectedfeed,taulell.getFeedbacks());
    }

    @Test
    public void getUltimIntent() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> ultimintent = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> nulfeedback = new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number()));
        //considerem que l'últim intent és el que té com a feedback una Seqüència buida

        List<List <Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(ultimintent); //intent 5 és l'últim
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));

        List<List <Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(nulfeedback); //intent 5 és l'últim
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));

        Taulell taulell = new Taulell(solucio,expectedint,expectedfeed);
        assertEquals(ultimintent,taulell.getUltimIntent());

    }

    @Test
    public void getUltimFeedback() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> ultimintent = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> ultimfeedback = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NUL.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.NUL.number()));

        List<Integer> nulfeedback = new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number()));
        //considerem que l'últim intent és el que té com a feedback una Seqüència buida

        List<List <Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(ultimintent); //intent 5 és l'últim
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));

        List<List <Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(ultimfeedback); //l'ultim feedback serà l'anterior
        expectedfeed.add(nulfeedback); //intent 5 és l'últim
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));

        Taulell taulell = new Taulell(solucio,expectedint,expectedfeed);
        assertEquals(ultimfeedback,taulell.getUltimFeedback());
    }

    @Test
    public void getIntents() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<List <Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));


        Taulell taulell = new Taulell(solucio,expectedint,expectedint);
        assertEquals(expectedint,taulell.getIntents());
    }

    @Test
    public void getFeedbacks() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<List <Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));

        Taulell taulell = new Taulell(solucio,expectedfeed,expectedfeed);
        assertEquals(expectedfeed,taulell.getFeedbacks());
    }

    @Test
    public void getNumeroIntent() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> ultimintent = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));

        List<Integer> nulfeedback = new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number()));
        //considerem que l'últim intent és el que té com a feedback una Seqüència buida

        List<List <Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(ultimintent); //intent 5 és l'últim
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.NEGRE.number(),Bola.VERMELL.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.ROSA.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number())));
        expectedint.add(new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number())));

        List<List <Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(nulfeedback); //intent 5 és l'últim
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number())));
        expectedfeed.add(new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number())));

        Taulell taulell = new Taulell(solucio,expectedint,expectedfeed);
        assertEquals(5,taulell.getNumeroIntent());
    }

    @Test
    public void addFeedback() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));
        Taulell taulell = new Taulell(solucio);

        List<Integer> feedback = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLANC.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLANC.number()));
        taulell.addFeedback(feedback);
        taulell.addFeedback(feedback);
        taulell.addFeedback(feedback);
        assertEquals(feedback,taulell.getUltimFeedback());

    }

    @Test
    public void getSolucio() {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));
        Taulell taulell = new Taulell(solucio);
        assertEquals(solucio, taulell.getSolucio());
    }

    @Test
    public void setBola() throws invalidEnumValue {
        List<Integer> solucio = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number(),Bola.TARONJA.number()));
        Taulell taulell = new Taulell(solucio);

        List<Integer> intent = new ArrayList<>(List.of(Bola.NEGRE.number(), Bola.BLAU.number(), Bola.BLAU.number(), Bola.ROSA.number(), Bola.VERMELL.number()));
        taulell.setBola(0, Bola.NEGRE.number());
        taulell.setBola(1, Bola.BLAU.number());
        taulell.setBola(2, Bola.BLAU.number());
        taulell.setBola(3, Bola.ROSA.number());
        taulell.setBola(4, Bola.VERMELL.number());

        assertEquals(intent, taulell.getUltimIntent());
    }
}