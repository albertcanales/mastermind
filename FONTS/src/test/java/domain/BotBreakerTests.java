package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;
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
    public void createBotBreakerInvalid() throws DomainException {
        assertThrows(InvalidEnumValueException.class, () -> {
            BotBreaker.create(500);
        });
    }

    @Test
    public void isValidCorrectAlgorisme() throws DomainException {
        assertTrue(TipusAlgorisme.isValid(1));
    }
    @Test
    public void isValidIncorrectAlgorisme() throws DomainException {
        assertFalse(TipusAlgorisme.isValid(500));
    }
    @Test
    public void isValidIncorrectNegativeAlgorisme() throws DomainException {
        assertFalse(TipusAlgorisme.isValid(-5));
    }
}
