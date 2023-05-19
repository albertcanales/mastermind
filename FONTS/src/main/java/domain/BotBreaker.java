package domain;

import exceptions.domain.DomainException;
import exceptions.domain.InvalidEnumValueException;

import java.util.*;

/**
 * Classe que representa un BotBreaker, és a dir, la màquina que desxifra codis
 * @author Mar Gonzàlez Català
 */
abstract class BotBreaker implements Maquina {

    /**
     * Constructor de BotMaker.
     * @param tipusAlgorisme tipus d'Algorisme.
     */
    static BotBreaker create(Integer tipusAlgorisme) throws DomainException {
        switch (TipusAlgorisme.findByNumber(tipusAlgorisme)) {
            case FIVEGUESS:
                return new FiveGuess();
            case GENETIC:
                return new Genetic();
        }
        return null;
    }

    /**
     * Getter del tipus d'algorisme
     */
    abstract TipusAlgorisme getTipusAlgorisme();

    /**
     * Donada una solució genera la llista d'intents fins a arribar a ella.
     * @param solution seqüència oculta que intentem endevinar.
     * @return Llista d'intents.
     */
    public abstract List<List<Integer>> solve(List<Integer> solution) throws DomainException;
}

/**
 * Enum dels diferents algorismes que pot utilitzar el BotBreaker
 * @author Mar Gonzàlez Català
 */
enum TipusAlgorisme {
    FIVEGUESS(1),
    GENETIC(2);

    /**
     * Nombre que representa el tipus d'algorisme
     */
    private final int number;

    /**
     * onstructor a partir de l'enter que representa el valor
     * @param number és el valor que representa el tipus d'algorisme
     */
    TipusAlgorisme(int number) {
        this.number = number;
    }

    /**
     * Mètode per obtenir el TipusAlgorisme corresponent a un nombre
     * @param number és el valor que representa el tipus d'algorisme
     * @throws DomainException si el nombre no correspon a un algorisme
     * @return el valor de TipusAlgorisme corresponent al nombre donat
     */
    public static TipusAlgorisme findByNumber(Integer number) throws DomainException {
        for(TipusAlgorisme ta : values()){
            if( ta.number() == number){
                return ta;
            }
        }
        throw new InvalidEnumValueException("Algorisme", number.toString());
    }

    /**
     * Getter del nombre que representa el valor
     */
    int number() { return number; }

    /**
     * Mètode per obtenir el nombre d'algorismes
     * @return enter que representa el número d'algorismes
     */
    public static int numAlgorismes() {
        return (values().length);
    }

    /**
     * Mètode per saber si un número representa un algorisme vàlid
     * @param num enter que representa un algorisme
     * @return un booleà cert si el número correspon a un algorisme, fals si no
     */
    public static boolean isValid(int num) {
        return (num > 0) && (num <= numAlgorismes());
    }
}