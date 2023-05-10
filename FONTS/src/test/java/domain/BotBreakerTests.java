package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotBreakerTests {
    static final Integer FIVEGUESS = 1, GENETIC = 2;
    @Test
    public void createBotBreakerFiveGuess() throws DomainException {
        BotBreaker botBreaker = BotBreaker.create(FIVEGUESS);
        Integer tipusAlgorisme = botBreaker.getTipusAlgorisme().number();
        assertEquals(FIVEGUESS, tipusAlgorisme);
    }
    @Test
    public void createBotBreakerGenetic() throws DomainException {
        BotBreaker botBreaker = BotBreaker.create(GENETIC);
        Integer tipusAlgorisme = botBreaker.getTipusAlgorisme().number();
        assertEquals(GENETIC, tipusAlgorisme);
    }
    @Test
    public void createBotBreakerInvalid() {
        assertThrows(InvalidEnumValueException.class, () -> BotBreaker.create(500));
    }

    @Test
    public void isValidCorrectAlgorisme() {
        assertTrue(TipusAlgorisme.isValid(1));
    }
    @Test
    public void isValidIncorrectAlgorisme() {
        assertFalse(TipusAlgorisme.isValid(500));
    }
    @Test
    public void isValidIncorrectNegativeAlgorisme() {
        assertFalse(TipusAlgorisme.isValid(-5));
    }
}
