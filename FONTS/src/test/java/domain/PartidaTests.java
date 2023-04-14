package domain;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class PartidaTests {

    @Test
    public void newPartidaTest() {
        Partida partida = new Partida();
        assertEquals(Duration.ZERO, partida.getTemps());
    }

    @Test
    public void loadedPartidaTest() {
        Partida partida = new Partida(Duration.ofMillis(754));
        assertEquals(Duration.ofMillis(754), partida.getTemps());
    }

    @Test
    public void addDuracioPartida() {
        Partida partida = new Partida(Duration.ofMillis(123));

        partida.addMillis(43L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());

        partida.addMillis(0L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());

        partida.addMillis(-10L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());
    }

    public static void main(String[] args) {
        TestRunner.runTestClass(PartidaTests.class);
    }
}
