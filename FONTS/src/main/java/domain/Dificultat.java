package domain;

import java.util.List;

/**
 * Classe que representa la dificultat de una partida
 * @author Albert Canales
 */
abstract class Dificultat {

    /**
     * Getter del nivell de dificultat
     * @author Albert Canales
     */
    abstract NivellDificultat getNivellDificultat();

    /**
     * Mètode per donar el feedback d'un intent
     * @param solucio és la sequència correcta de la partiad
     * @param intent és l'intent pel qual es vol rebre feedback
     * @return feedback corresponent a l'intent
     * @author Albert Canales
     */
    abstract List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent);
}

/**
 * Enum dels diferents nivells de dificultat d'una partida
 * @author Albert Canales
 */
enum NivellDificultat {
    FACIL(1),
    MITJA(2),
    DIFICIL(3);

    private final int number;

    /**
     * Constructor a partir de l'enter que representa el valor
     * @param number és el valor que representa el nivell de dificultat
     * @author Albert Canales
     */
    NivellDificultat(int number) {
        this.number = number;
    }

    /**
     * Getter del nombre que representa el valor
     * @author Albert Canales
     */
    int number() { return number; }
}
