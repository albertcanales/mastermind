package domain;

/**
 * Representació de les "Boles" del joc en enters.
 * El NUL (0) indica que no hi ha bola, i els colors són enters positius, però
 * només farem servir del 1-8.
 * 
 * @author Arnau Valls Fusté
 */
class Bola {
    static final int NUL = 0;
    static final int BLANC = 1;
    static final int NEGRE = 2;
    static final int VERMELL = 3;
    static final int BLAU = 4;
    static final int TARONJA = 5;
    static final int ROSA = 6;
    static final int VERD = 7;
    static final int GROC = 8;
    static final int NUMCOLORS = 6;
}