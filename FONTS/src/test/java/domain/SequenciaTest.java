package domain;

import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.InvalidNumBolesException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SequenciaTest {
    public static List<Integer> getNulList() {
        List<Integer> list = new ArrayList<>(Sequencia.NUMBOLES);
        for (int i = 0; i < Sequencia.NUMBOLES ; ++i) {
            list.add(Bola.NUL.number());
        }
        return list;
    }

    @Test
    public void newSequenciaBuida() {
        Sequencia sequencia = new Sequencia();
        assertEquals(getNulList(),sequencia.flatten());
    }

    @Test
    public void newSequenciaInit() throws InvalidNumBolesException {
        Sequencia sequencia = new Sequencia(List.of(1,0,5,0));
        assertEquals((List.of(1,0,5,0)),sequencia.flatten());
    }

    @Test
    public void newSequenciaInitWrong() {
        assertThrows(InvalidNumBolesException.class, () -> {
            new Sequencia(List.of(1,0,5,0,0));
        });
    }

    @Test
    public void isPlenaTrue() throws InvalidNumBolesException {
        List<Integer> initBoles = new ArrayList<>(List.of(Bola.NEGRE.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number()));
        Sequencia sequencia = new Sequencia(initBoles);
        assertEquals(true,sequencia.isPlena());
    }

    @Test
    public void isPlenaFalse() throws InvalidNumBolesException {
        List<Integer> initBoles = new ArrayList<>(List.of(Bola.NUL.number(),Bola.BLAU.number(),Bola.BLANC.number(),Bola.BLANC.number()));
        Sequencia sequencia = new Sequencia(initBoles);
        assertEquals(false,sequencia.isPlena());
    }

    @Test
    public void isBuidaTrue() throws InvalidNumBolesException {
        Sequencia sequencia = new Sequencia(getNulList());
        assertEquals(true,sequencia.isBuida());
    }

    @Test
    public void isBuidaFalse() throws InvalidNumBolesException {
        List<Integer> initBoles = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number()));
        Sequencia sequencia = new Sequencia(initBoles);
        assertEquals(false,sequencia.isBuida());
    }

    @Test
    public void setBola() throws InvalidEnumValueException {
        Sequencia sequencia = new Sequencia();
        sequencia.setBola(0, Bola.ROSA.number());
        sequencia.setBola(3, Bola.BLAU.number());

        List<Integer> expectedBoles = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLAU.number()));
        assertEquals(expectedBoles,sequencia.flatten());
    }

    @Test
    public void setBolaDecreaseMida() throws InvalidEnumValueException, InvalidNumBolesException {
        List<Integer> inputBoles = new ArrayList<>(List.of(Bola.ROSA.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLAU.number()));
        Sequencia sequencia = new Sequencia(inputBoles);
        sequencia.setBola(0, Bola.NUL.number());

        List<Integer> expectedBoles = new ArrayList<>(List.of(Bola.NUL.number(),Bola.NUL.number(),Bola.NUL.number(),Bola.BLAU.number()));
        assertEquals(expectedBoles,sequencia.flatten());
    }

    @Test
    public void setWrongIndexBola() {
        Sequencia sequencia = new Sequencia();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            sequencia.setBola(-1, Bola.ROSA.number());
        });
    }

    @Test
    public void setWrongBola() {
        Sequencia sequencia = new Sequencia();
        assertThrows(InvalidEnumValueException.class, () -> {
            sequencia.setBola(0, -1);
        });
    }

    @Test
    public void flatten() throws InvalidNumBolesException {
        List<Integer> expectedBoles = new ArrayList<>(List.of(Bola.NUL.number(),Bola.ROSA.number(),Bola.NEGRE.number(),Bola.BLANC.number()));
        Sequencia sequencia = new Sequencia(expectedBoles);
        assertEquals(expectedBoles, sequencia.flatten());
    }
}