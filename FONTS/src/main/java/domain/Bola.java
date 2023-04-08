package domain;

/**
 * Representació de les "Boles" del joc en enters.
 * El NUL (0) indica que no hi ha bola, i els colors són enters positius, però
 * només farem servir de l'1 al 8.
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
    ROSA(6),
    VERD(7),
    GROC(8);


    private final int number;

    /**
     * Constructor a partir de l'enter que representa el color
     *
     * @param number és el valor que representa el nivell de dificultat
     */
    Bola(int number) {
        this.number = number;
    }

    /**
     * Mètode per obtenir la Bola corresponent a un nombre
     *
     * @param number és el valor que representa el color
     * @return el valor de Bola corresponent al nombre donat
     */
    public static Bola findByNumber(Integer number) {
        for (Bola nd : values()) {
            if (nd.number() == number) {
                return nd;
            }
        }
        return null;
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

}