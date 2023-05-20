package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("MissingJavadoc")
public class DificultatTests {
    private static final Integer FACIL = 1;
    private static final Integer MITJA = 2;
    private static final Integer DIFICIL = 3;

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createDificultatFacil() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);
        Integer nivellDificultat = dificultat.getNivellDificultat().number();
        assertEquals(FACIL, nivellDificultat);
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createDificultatMitja() throws DomainException {
        Dificultat dificultat = Dificultat.create(MITJA);
        Integer nivellDificultat = dificultat.getNivellDificultat().number();
        assertEquals(MITJA, nivellDificultat);
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createDificultatDificil() throws DomainException {
        Dificultat dificultat = Dificultat.create(DIFICIL);
        Integer nivellDificultat = dificultat.getNivellDificultat().number();
        assertEquals(DIFICIL, nivellDificultat);
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createDificultatInvalid() {
        assertThrows(InvalidEnumValueException.class, () -> Dificultat.create(500));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void countColorsOfSequencia() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> Sequencia = new ArrayList<>(List.of(1, 1, 3, 6));
        List<Integer> ColorCountExpected = new ArrayList<>(List.of(0, 2, 0, 1, 0, 0, 1));
        assertEquals(ColorCountExpected, dificultat.countColorsBoles(Sequencia));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void countColorsOfSequenciaWithInvalidColors() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> Sequencia = new ArrayList<>(List.of(1,1,500,6));
        assertThrows(InvalidEnumValueException.class, () -> dificultat.countColorsBoles(Sequencia));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void isPlenaSequenciaPlena() throws DomainException{
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> Sequencia = new ArrayList<>(List.of(1, 1, 3, 6));
        assertTrue(dificultat.isPlena(Sequencia));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void isPlenaSequenciaNotPlena() throws DomainException {
        Dificultat dificultat = Dificultat.create(FACIL);

        List<Integer> Sequencia = new ArrayList<>(List.of(1, 1, 0, 6));
        assertFalse(dificultat.isPlena(Sequencia));
    }

    @SuppressWarnings("MissingJavadoc")
    public static void main(String[] args) {
        TestRunner.runTestClass(DificultatTests.class);
    }
}
