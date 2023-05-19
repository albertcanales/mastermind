package domain;

/**
 * Representació de les "Boles" del joc en enters.
 * El NUL (0) indica que no hi ha bola, i els colors són enters positius, però
 * només farem servir de l'1 al 6.
 * 
 * @author Arnau Valls Fusté
 */
enum Bola {
    NUL(0),
    BLANC(1),
    NEGRE(2),
    VERMELL(3),
    BLAU(4),
    TARONJA(5),
    ROSA(6);

    /**
     * Nombre que representa la bola
     */
    private final int number;

    /**
     * Constructor a partir de l'enter que representa el color
     * @param number és el valor que representa la bola
     */
    Bola(int number) {
        this.number = number;
    }

    /**
     * Getter del nombre que representa el valor
     * @return l'enter que representa el color
     */
    int number() {
        return number;
    }

    /**
     * Mètode per obtenir el nombre de colors
     * @return enter que representa el número de colors
     */
    public static int numColors() {
        return (values().length - 1); // no considerem NUL com un color
    }

    /**
     * Mètode per saber si un número representa un color vàlid no nul
     *
     * @param num un enter representa un color
     * @return un booleà cert si el color és vàlid i no nul
     */
    public static boolean isColor(int num) {
        return (num > NUL.number()) && (num <= numColors());
    }

    /**
     * Mètode per saber si un número representa una Bola vàlida (inclou NUL!)
     * @param num un enter representa una bola
     * @return un booleà cert si la bola és vàlida
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValid(int num) {
        return (num >= NUL.number()) && (num <= numColors());
    }

}