package domain;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class PartidaTests {

    @Test
    public void newPartidaTest() {
        Partida partida = new Partida();
        assertEquals(Duration.ZERO, partida.getTemps());
    }

    @Test
    public void loadedPartidaTest() {
        Partida partida = new Partida(Duration.ofMillis(754), true);
        assertEquals(Duration.ofMillis(754), partida.getTemps());
        assertEquals(true, partida.isSolucioVista());
    }

    @Test
    public void addDuracioPartida() {
        Partida partida = new Partida(Duration.ofMillis(123), false);

        partida.addMillis(43L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());

        partida.addMillis(0L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());

        partida.addMillis(-10L);
        assertEquals(Duration.ofMillis(166), partida.getTemps());
    }

    @Test
    public void veureSolucioPartida() {
        Partida partida = new Partida(Duration.ofMillis(123), false);

        partida.veureSolucio();
        assertEquals(true, partida.isSolucioVista());
    }

    public static void main(String[] args) {
        TestRunner.runTestClass(PartidaTests.class);
    }
}
