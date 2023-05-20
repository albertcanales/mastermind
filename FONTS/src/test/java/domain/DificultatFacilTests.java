package domain;

import exceptions.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("MissingJavadoc")
public class DificultatFacilTests {

    private static final Integer NUL = 0;
    private static final Integer BLANC = 1;
    private static final Integer NEGRE = 2;
    private static final Integer FACIL = 1;
    static final Integer MITJA = 2;
    static final Integer DIFICIL = 3;

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void FeedbackDificultatFacilWithoutCoincidence() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NUL,NUL,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void FeedbackDificultatFacil() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(1,1,3,3));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NUL,NEGRE,BLANC,NUL));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void FeedbackDificultatFacilForOneColor3Intent2Sol() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,4,4));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,4));
        List<Integer> FeedbackExpected = new ArrayList<>(List.of(NEGRE,NEGRE,NUL,NEGRE));
        assertEquals(FeedbackExpected, dificultat.validarSequencia(solucio,intent));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void SolutionIntentDifferentSizes() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,3,3,3));
        assertThrows(SolIntentNotSameSizeException.class, () -> dificultat.validarSequencia(solucio,intent));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void IntentNotComplet() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(1,1,2,2));
        List<Integer> intent = new ArrayList<>(List.of(3,3,0,3));
        assertThrows(IntentNoCompletException.class, () -> dificultat.validarSequencia(solucio,intent));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void SolucioNotComplete() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,0,3));
        List<Integer> intent = new ArrayList<>(List.of(1,1,2,2));
        assertThrows(InvalidSolutionException.class, () -> dificultat.validarSequencia(solucio,intent));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void InvalidColorsInSolucio() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,500,1,3));
        List<Integer> intent = new ArrayList<>(List.of(1,1,2,2));
        assertThrows(InvalidEnumValueException.class, () -> dificultat.validarSequencia(solucio,intent));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void InvalidColorsInIntent() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> solucio = new ArrayList<>(List.of(3,3,1,3));
        List<Integer> intent = new ArrayList<>(List.of(1,1,500,2));
        assertThrows(InvalidEnumValueException.class, () -> dificultat.validarSequencia(solucio,intent));
    }


    @SuppressWarnings("MissingJavadoc")
    public static void main(String[] args) {
        TestRunner.runTestClass(DificultatFacilTests.class);
    }
}
