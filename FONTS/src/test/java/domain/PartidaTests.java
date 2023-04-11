package domain;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PartidaTests {

    @Test
    public void newPartidaTest() {
        Partida partida = new Partida("Joan");
        assertEquals("Joan", partida.getUser());
        assertEquals(Duration.ZERO, partida.getTemps());
        assertNull(partida.getDataFi());
    }

    @Test
    public void loadedPartidaTest() {
        Partida partida = new Partida("Pere", Duration.ofMillis(754));
        assertEquals("Pere", partida.getUser());
        assertEquals(Duration.ofMillis(754), partida.getTemps());
        assertNull(partida.getDataFi());
    }

    @Test
    public void finishedPartidaTest() {
        LocalDateTime date = LocalDateTime.of(2023, 4, 8, 1, 2, 0);
        Partida partida = new Partida("Mercè", Duration.ofMillis(832), date);
        assertEquals("Mercè", partida.getUser());
        assertEquals(Duration.ofMillis(832), partida.getTemps());
        assertEquals(date, partida.getDataFi());
    }

    @Test
    public void setTempsTest() {
        Partida partida = new Partida("Anna", Duration.ofMillis(832));
        partida.setTemps(Duration.ofMillis(453));
        assertEquals(Duration.ofMillis(453), partida.getTemps());
    }

    @Test
    public void setDataFiTest() {
        LocalDateTime dateInicial = LocalDateTime.of(2023, 4, 8, 1, 2, 0);
        Partida partida = new Partida("Albert", Duration.ofMillis(832), dateInicial);
        LocalDateTime dateNova = LocalDateTime.of(2022, 1, 1, 0, 0, 0, 0);
        partida.setDataFi(dateNova);
        assertEquals(dateNova, partida.getDataFi());
    }
}
