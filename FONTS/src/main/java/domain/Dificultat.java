package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa la dificultat d'una partida
 * @author Albert Canales
 */
abstract class Dificultat {

    /**
     * Mètode per instanciar una subclasse a partir del nombre del nivell de dificultat
     * @param nivellDificultat nombre del nivell de dificultat desitjat
     * @return Nova instància de la dificultat desitjada
     * @author Albert Canales
     */
    static Dificultat create(Integer nivellDificultat) {

        switch (NivellDificultat.findByNumber(nivellDificultat)) {
            case FACIL:
                return new DificultatFacil();
            case MITJA:
                return new DificultatMitja();
            case DIFICIL:
                return new DificultatDificil();
        }
        return null;
    }

    /**
     * Getter del nivell de dificultat
     * @author Albert Canales
     */
    abstract NivellDificultat getNivellDificultat();

    /**
     * Mètode per donar el feedback d'un intent
     * @param solucio és la seqüència correcta de la partida
     * @param intent és l'intent pel qual es vol rebre feedback
     * @return feedback corresponent a l'intent
     * @author Albert Canales
     */
    abstract List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent);

    /**
     * Mètode que retorna el nombre d'aparicions de cada bola en la seqüència
     * @param sequencia és la seqüència de la qual se'n volen extreure els colors
     * @return una llista on la posició del valor numèric de la bola representa el seu nombre d'aparicions
     * @author Albert Canales
     */
    List<Integer> countColorsBoles(List<Integer> sequencia) {
        List<Integer> colorList = new ArrayList<>(Collections.nCopies(sequencia.size(), 0));
        for (Integer color_sol : sequencia){
            Integer count = colorList.get(color_sol); count++;
            colorList.set(color_sol, count);
        }
        return colorList;
    }
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
     * Mètode per obtenir en NivellDificultat corresponent a un nombre
     * @param number és el valor que representa el nivell de dificultat
     * @return el valor de NivellDificultat corresponent al nombre donat
     * @author Albert Canales
     */
    public static NivellDificultat findByNumber(Integer number){
        for(NivellDificultat nd : values()){
            if( nd.number() == number){
                return nd;
            }
        }
        return null;
    }

    /**
     * Getter del nombre que representa el valor
     * @author Albert Canales
     */
    int number() { return number; }

    /**
     * Mètode per obtenir el nombre de dificultats
     * @return enter que representa el número de dificultats
     */
    public static int numDificultats() {
        return (values().length);
    }
}
