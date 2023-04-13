package domain;

import org.junit.Test;

import domain.exceptions.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DificultatTests {

    static final Integer NUL = 0, BLANC = 1, NEGRE = 2;
    static final Integer FACIL = 1, MITJA = 2, DIFICIL = 3;
    @Test
    public void countColorsOfSequencia() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> Sequencia = new ArrayList<>(List.of(1,1,3,6));
        List<Integer> ColorCountExpected = new ArrayList<>(List.of(0,2,0,1,0,0,1));
        assertEquals(ColorCountExpected, dificultat.countColorsBoles(Sequencia));
    }

    @Test
    public void FeedbackDificultatFacilWithoutCoincidence() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NUL,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }
    @Test
    public void FeedbackDificultatFacil() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(1,1,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NEGRE,BLANC,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void FeedbackDificultatFacilForOneColor3Intent2Sol() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,4,4));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,4));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NEGRE,NEGRE,NUL,NEGRE));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void FeedbackDificultatMitjaWithoutCoincidence() throws DomainException {
        Dificultat dificultat = Dificultat.create(MITJA);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NUL,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }
    @Test
    public void FeedbackDificultatMitja() throws DomainException {
        Dificultat dificultat = Dificultat.create(MITJA);

        List<Integer> solucio = new ArrayList<>(List.of(3,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(1,1,3,6));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NEGRE,BLANC,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void FeedbackDificultatMitjaForOneColor3Intent2Sol() throws DomainException {
        Dificultat dificultat = Dificultat.create(MITJA);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,4,4));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,4));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NEGRE,NEGRE,NEGRE,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void FeedbackDificultatDificilWithoutCoincidence() throws DomainException {
        Dificultat dificultat = Dificultat.create(DIFICIL);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NUL,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }
    @Test
    public void FeedbackDificultatDificil() throws DomainException {
        Dificultat dificultat = Dificultat.create(DIFICIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(1,1,3,6));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(BLANC,BLANC,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void FeedbackDificultatDificilForOneColor3Intent2Sol() throws DomainException {
        Dificultat dificultat = Dificultat.create(DIFICIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,4,4));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,4));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(BLANC,BLANC,BLANC,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @Test
    public void SolutionIntentDifferentSizes() throws DomainException{
        Dificultat dificultat = Dificultat.create(MITJA);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3,3));
        assertThrows(SolIntentNotSameSizeException.class, () -> {
            dificultat.validarSequencia(solucio,intent);
        });
    }
    @Test
    public void IntentNotComplet() throws DomainException{
        Dificultat dificultat = Dificultat.create(MITJA);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,0,3));
        assertThrows(IntentNoComplet.class, () -> {
            dificultat.validarSequencia(solucio,intent);
        });
    }

}
