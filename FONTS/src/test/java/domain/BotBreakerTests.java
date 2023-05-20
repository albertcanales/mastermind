package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("MissingJavadoc")
public class BotBreakerTests {
    private static final Integer FIVEGUESS = 1;
    private static final Integer GENETIC = 2;
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createBotBreakerFiveGuess() throws DomainException {
        BotBreaker botBreaker = BotBreaker.create(FIVEGUESS);
        Integer tipusAlgorisme = botBreaker.getTipusAlgorisme().number();
        assertEquals(FIVEGUESS, tipusAlgorisme);
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createBotBreakerGenetic() throws DomainException {
        BotBreaker botBreaker = BotBreaker.create(GENETIC);
        Integer tipusAlgorisme = botBreaker.getTipusAlgorisme().number();
        assertEquals(GENETIC, tipusAlgorisme);
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void createBotBreakerInvalid() {
        assertThrows(InvalidEnumValueException.class, () -> BotBreaker.create(500));
    }

    @SuppressWarnings("MissingJavadoc")
    @Test
    public void isValidCorrectAlgorisme() {
        assertTrue(TipusAlgorisme.isValid(1));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void isValidIncorrectAlgorisme() {
        assertFalse(TipusAlgorisme.isValid(500));
    }
    @SuppressWarnings("MissingJavadoc")
    @Test
    public void isValidIncorrectNegativeAlgorisme() {
        assertFalse(TipusAlgorisme.isValid(-5));
    }
}
