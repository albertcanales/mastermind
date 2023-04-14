package domain;

import domain.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TaulellTest {

    public List<Integer> getNulList() {
        List<Integer> list = new ArrayList<>(Taulell.NUMBOLES);
        for (int i = 0; i < Taulell.NUMBOLES; ++i) {
            list.add(Bola.NUL.number());
        }
        return list;
    }

    public List<List<Integer>> getNulListList() {
        List<List<Integer>> list = new ArrayList<>(Taulell.NUMINTENTS);
        for (int i = 0; i < Taulell.NUMINTENTS; ++i) {
            list.add(getNulList());
        }
        return list;
    }

    public List<Integer> getList(long seed, boolean allowNul, boolean isFeedback) {
        List<Integer> list = new ArrayList<>();
        Random rand = new Random(seed);
        for (int i = 0; i < Taulell.NUMBOLES; i++) {
            if (isFeedback) list.add(rand.nextInt(Bola.NEGRE.number() + 1)); // de 0 (Nul) a 2 (Negre)
            else {
                if (!allowNul) list.add(rand.nextInt(Bola.numColors()) + 1);
                else list.add(rand.nextInt(Bola.numColors()) + 2);
            }
        }
        return list;
    }

    public List<List<Integer>> getListList(long seed, boolean allowNul, boolean isFeedback, int size) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Integer> sequence = getList(seed+i, allowNul, isFeedback);
            list.add(sequence);
        }
        return list;
    }
    //TODO genera sequencies random valides
    @Test
    public void newTaulellBuit() throws DomainException {

        List<Integer> solucio = getList(50,false, false);

        Taulell taulell = new Taulell(solucio);
        assertEquals(solucio, taulell.getSolucio());
        assertEquals(getNulListList(), taulell.getIntents());
        assertEquals(getNulListList(), taulell.getFeedbacks());
    }

    @Test
    public void newTaulellBuitWrongSol() {
        assertThrows(InvalidSolutionException.class, () -> {
            new Taulell(List.of(Bola.BLANC.number(),Bola.TARONJA.number(),Bola.TARONJA.number(),Bola.NUL.number())); //té un NUL
        });
    }

    @Test
    public void newTaulellBuitWrongSizeSol() {
        assertThrows(InvalidNumBolesException.class, () -> {
            new Taulell(List.of(Bola.BLANC.number(),Bola.TARONJA.number(),Bola.TARONJA.number(),Bola.TARONJA.number(),Bola.TARONJA.number())); //5 boles en comptes de 4
        });
    }

    @Test
    public void newTaulellInit() throws DomainException {
        List<Integer> solucio = getList(420,false, false);

        List<List<Integer>> expectedint = getListList(425, false, false, Taulell.NUMINTENTS);

        List<List<Integer>> expectedfeed = getListList(421329, false, true, Taulell.NUMINTENTS);

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(solucio, taulell.getSolucio());
        assertEquals(expectedint, taulell.getIntents());
        assertEquals(expectedfeed, taulell.getFeedbacks());
    }

    @Test
    public void newTaulellInitWrongSizeIntents() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, 13);
        //13 intents

        List<List<Integer>> expectedfeed = getListList(421324, true, false, Taulell.NUMINTENTS);

        assertThrows(InvalidNumIntentsException.class, () -> {
            new Taulell(solucio, expectedint, expectedfeed);
        });
    }

    @Test
    public void newTaulellInitWrongSizeBolesIntents() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);
        expectedint.get(0).add(Bola.NUL.number()); //5 boles a l'intent 0

        List<List<Integer>> expectedfeed = getListList(421324, true, false, Taulell.NUMINTENTS);

        assertThrows(InvalidNumBolesException.class, () -> {
            new Taulell(solucio, expectedint, expectedfeed);
        });
    }

    @Test
    public void newTaulellInitWrongSizeFeebacks() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);

        List<List<Integer>> expectedfeed = getListList(421324, true, false, Taulell.NUMINTENTS);
        expectedfeed.add(getList(21738, false, true)); //13 intents

        assertThrows(InvalidNumIntentsException.class, () -> {
            new Taulell(solucio, expectedint, expectedfeed);
        });
    }

    @Test
    public void newTaulellInitWrongSizeBolesFeebacks() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);

        List<List<Integer>> expectedfeed = getListList(421324, false, true, Taulell.NUMINTENTS);
        expectedfeed.get(0).add(Bola.NUL.number()); //5 boles a l'intent 0

        assertThrows(InvalidNumBolesException.class, () -> {
            new Taulell(solucio, expectedint, expectedfeed);
        });
    }
    @Test
    public void newTaulellInitWrongSol() {
        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);
        List<List<Integer>> expectedfeed = getListList(421324, false, true, Taulell.NUMINTENTS);

        assertThrows(InvalidSolutionException.class, () -> {
            new Taulell(List.of(3,3,3,0),expectedint,expectedfeed);
        });
    }

    @Test
    public void newTaulellInitWrongFeeback() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);
        List<List<Integer>> expectedfeed = getListList(421324, true, false, Taulell.NUMINTENTS); //Li posem colors al feedback

        assertThrows(InvalidFeedbackException.class, () -> {
            new Taulell(solucio,expectedint,expectedfeed);
        });
    }

    @Test
    public void newTaulellInitWrongIntent() {
        List<Integer> solucio = getList(50,true, false);

        List<List<Integer>> expectedint = getListList(424, false, false, Taulell.NUMINTENTS);
        expectedint.set(0, List.of(Bola.NUL.number(), Bola.BLAU.number(), Bola.ROSA.number(), 10)); //Li posem un color que no existeix
        List<List<Integer>> expectedfeed = getListList(23121, false, true, Taulell.NUMINTENTS);

        assertThrows(InvalidIntentException.class, () -> {
            new Taulell(solucio,expectedint,expectedfeed);
        });
    }


    @Test
    public void getUltimIntent() throws DomainException {
        List<Integer> solucio = getList(777,false, false);

        //considerem que l'últim intent és el que té com a feedback una Seqüència buida

        List<List<Integer>> expectedint = getListList(29945, false, false, 6);

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(231,false, true));
        expectedfeed.add(getList(22331,false, true));
        expectedfeed.add(getList(232231,false, true));
        expectedfeed.add(getList(25431,false, true));
        expectedfeed.add(getList(2631,false, true));
        expectedfeed.add(getNulList()); //intent 5 és l'últim

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(expectedint.get(5), taulell.getUltimIntent());

    }

    @Test
    public void getUltimFeedback() throws DomainException {
        List<Integer> solucio = getList(4798378,false, false);

        List<List<Integer>> expectedint = getListList(29945, false, false, 6);

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(7,false, true));
        expectedfeed.add(getList(234,false, true));
        expectedfeed.add(getList(13,false, true));
        expectedfeed.add(getList(987,false, true));
        expectedfeed.add(getList(234879,false,true)); // l'últim feedback serà l'anterior
        expectedfeed.add(getNulList()); //intent 5 és "l'últim" perquè està buit

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(expectedfeed.get(4), taulell.getUltimFeedback());
    }

    @Test
    public void isUltimIntentPleFalse() throws DomainException {
        List<Integer> solucio = getList(4798378,false, false);

        List<List<Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        expectedint.add(getList(7,false, false));
        expectedint.add(getList(234,false, false));
        expectedint.add(getList(13,false, false));
        expectedint.add(getList(987,false, false));
        expectedint.add(getList(234879,false,false)); // l'últim feedback serà l'anterior
        expectedint.add(getNulList()); //intent 5 és "l'últim" perquè està buit

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(7,false, true));
        expectedfeed.add(getList(234,false, true));
        expectedfeed.add(getList(13,false, true));
        expectedfeed.add(getList(987,false, true));
        expectedfeed.add(getList(234879,false,true)); // l'últim feedback serà l'anterior
        expectedfeed.add(getNulList()); //intent 5 és "l'últim" perquè està buit

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(false, taulell.isUltimIntentPle());
    }

    @Test
    public void isUltimIntentPleTrue() throws DomainException {
        List<Integer> solucio = getList(4798378,false, false);

        List<List<Integer>> expectedint = new ArrayList<>(Taulell.NUMINTENTS);
        expectedint.add(getList(7,false, false));
        expectedint.add(getList(234,false, false));
        expectedint.add(getList(13,false, false));
        expectedint.add(getList(987,false, false));
        expectedint.add(getList(234879,false,false));
        expectedint.add(getList(21739,false, false)); //ultim intent ple

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(7,false, true));
        expectedfeed.add(getList(234,false, true));
        expectedfeed.add(getList(13,false, true));
        expectedfeed.add(getList(987,false, true));
        expectedfeed.add(getList(234879,false,true));
        expectedfeed.add(getNulList()); //intent 5 és "l'últim" perquè està buit

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(true, taulell.isUltimIntentPle());
    }

    @Test
    public void getIntents() throws DomainException {
        List<Integer> solucio = getList(312312312,false, false);

        List<List<Integer>> expectedint = getListList(575, false, false, 5);
        List<List<Integer>> expectedfeed = getListList(23121, false, true, 5);

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(expectedint, taulell.getIntents());
    }

    @Test
    public void getFeedbacks() throws DomainException {
        List<Integer> solucio = getList(312312312,false, false);

        List<List<Integer>> expectedint = getListList(575, false, false, 7);
        List<List<Integer>> expectedfeed = getListList(23121, false, true, 7);

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(expectedfeed, taulell.getFeedbacks());
    }

    @Test
    public void getNumeroIntent() throws DomainException {
        List<Integer> solucio = getList(51414,false, false);

        List<List<Integer>> expectedint = getListList(999317, false, false, 6);

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(213213,false, true));
        expectedfeed.add(getList(645646,false, true));
        expectedfeed.add(getList(287398747,false, true));
        expectedfeed.add(getList(987342983,false, true));
        expectedfeed.add(getList(435643,false, true));
        expectedfeed.add(getList(4363,false, true));
        expectedfeed.add(getNulList()); //intent 6 és l'últim

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(6, taulell.getNumeroIntent());
    }

    @Test
    public void getNumeroIntentLast() throws DomainException {
        List<Integer> solucio = getList(51414,false, false);

        List<List<Integer>> expectedint = getListList(999317, false, false, Taulell.NUMINTENTS);

        List<List<Integer>> expectedfeed = new ArrayList<>(Taulell.NUMINTENTS);
        //init NUMINTENTS (12) times
        expectedfeed.add(getList(213213,false, true));
        expectedfeed.add(getList(645646,false, true));
        expectedfeed.add(getList(287398747,false, true));
        expectedfeed.add(getList(987342983,false, true));
        expectedfeed.add(getList(435643,false, true));
        expectedfeed.add(getList(4363,false, true));
        expectedfeed.add(getList(65,false, true));
        expectedfeed.add(getList(98409,false, true));
        expectedfeed.add(getList(35234,false, true));
        expectedfeed.add(getList(65436,false, true));
        expectedfeed.add(getList(6364,false, true));
        expectedfeed.add(getNulList()); //intent 11 és l'últim

        Taulell taulell = new Taulell(solucio, expectedint, expectedfeed);
        assertEquals(11, taulell.getNumeroIntent());
    }

    @Test
    public void addFeedback() throws DomainException {
        List<Integer> solucio = getList(213312,false, false);
        Taulell taulell = new Taulell(solucio);

        List<Integer> feedback = new ArrayList<>(List.of(Bola.NEGRE.number(), Bola.BLANC.number(), Bola.NUL.number(), Bola.NUL.number()));
        taulell.addFeedback(feedback);
        taulell.addFeedback(feedback);
        taulell.addFeedback(feedback);
        assertEquals(feedback, taulell.getUltimFeedback());

    }

    @Test
    public void addFeedbackWrong() throws DomainException {
        List<Integer> solucio = getList(213312,false, false);
        Taulell taulell = new Taulell(solucio);

        List<Integer> feedback = new ArrayList<>(List.of(Bola.NEGRE.number(), Bola.BLANC.number(), Bola.NUL.number(), Bola.BLAU.number()));
        //posem un color no permès (BLAU)

        assertThrows(InvalidFeedbackException.class, () -> {
            taulell.addFeedback(feedback);
        });

    }

    @Test
    public void addFeedbackWrongSize() throws DomainException {
        List<Integer> solucio = getList(213312,false, false);
        Taulell taulell = new Taulell(solucio);

        List<Integer> feedback = new ArrayList<>(List.of(Bola.NEGRE.number(), Bola.BLANC.number(), Bola.NUL.number(), Bola.NUL.number(), Bola.NUL.number()));

        assertThrows(InvalidNumBolesException.class, () -> {
            taulell.addFeedback(feedback);
        });

    }

    @Test
    public void getSolucio() throws DomainException {
        List<Integer> solucio = getList(123,false, false);
        Taulell taulell = new Taulell(solucio);
        assertEquals(solucio, taulell.getSolucio());
    }

    @Test
    public void setBola() throws DomainException {
        List<Integer> solucio = getList(213123466,false, false);
        Taulell taulell = new Taulell(solucio);

        List<Integer> intent = new ArrayList<>(List.of(Bola.NEGRE.number(), Bola.BLAU.number(), Bola.BLAU.number(), Bola.ROSA.number()));
        taulell.setBola(0, Bola.NEGRE.number());
        taulell.setBola(1, Bola.BLAU.number());
        taulell.setBola(2, Bola.BLAU.number());
        taulell.setBola(3, Bola.ROSA.number());

        assertEquals(intent, taulell.getIntents().get(0));
    }

    @Test
    public void setBolaWrong() throws DomainException {
        List<Integer> solucio = getList(213123466,false, false);

        Taulell taulell = new Taulell(solucio);

        assertThrows(InvalidEnumValueException.class, () -> {
            taulell.setBola(0, -1);
        });
    }

    public static void main(String[] args) {
        TestRunner.runTestClass(TaulellTest.class);
    }
}