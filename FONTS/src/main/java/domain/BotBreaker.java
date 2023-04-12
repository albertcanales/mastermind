package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;

import java.util.*;

/**
 * Contenidor de les funcionalitats d'un BotBreaker.
 *
 * @author Mar Gonzàlez Català
 */
abstract class BotBreaker {

    /**
     * @brief Constructor de BotMaker.
     * @param tipusAlgorisme tipus d'Algorisme.
     * @author Mar Gonzàlez Català
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
     * @brief Donada una solució genera la llista d'intents fins a arribar a ella.
     * @param solution seqüència oculta que intentem endevinar.
     * @return Llista d'intents.
     * @author Mar Gonzàlez Català
     */
    abstract ArrayList<ArrayList<Integer>> solve(ArrayList<Integer> solution);
}

/**
 * @brief Enum dels diferents algorismes que pot utilitzar el BotBreaker
 * @author Mar Gonzàlez Català
 */
enum TipusAlgorisme {
    FIVEGUESS(1),
    GENETIC(2);

    private final int number;

    /**
     * @brief Constructor a partir de l'enter que representa el valor
     * @param number és el valor que representa el tipus d'algorisme
     * @author Mar Gonzàlez Català
     */
    TipusAlgorisme(int number) {
        this.number = number;
    }

    /**
     * @brief Mètode per obtenir el TipusAlgorisme corresponent a un nombre
     * @param number és el valor que representa el tipus d'algorisme
     * @throws DomainException si el nombre no correspon a un algorisme
     * @return el valor de TipusAlgorisme corresponent al nombre donat
     * @author Mar Gonzàlez Català
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
     * @brief Getter del nombre que representa el valor
     * @author Mar Gonzàlez Català
     */
    int number() { return number; }

    /**
     * Mètode per obtenir el nombre d'algoritmes
     * @return enter que representa el número de algoritmes
     */
    public static int numAlgoritmes() {
        return (values().length);
    }

    /**
     * Mètode per saber si un número representa un algoritme vàlid
     *
     * @param num enter que representa un algoritme
     * @return un booleà cert si el número correspon a un algoritme
     */
    public static boolean isValid(int num) {
        return (num > 0) && (num <= numAlgoritmes());
    }
}