package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;

import java.util.ArrayList;
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
     */
    static Dificultat create(Integer nivellDificultat) throws DomainException {

        switch (NivellDificultat.findByNumber(nivellDificultat)) {
            case FACIL:
                return new DificultatFacil();
            case MITJA:
                return new DificultatMitja();
            case DIFICIL:
                return new DificultatDificil();
        }
        throw new InvalidEnumValueException("NivellDificultat", nivellDificultat.toString());
    }

    /**
     * Getter del nivell de dificultat
     */
    abstract NivellDificultat getNivellDificultat();

    /**
     * Mètode per donar el feedback d'un intent
     * @param solucio és la seqüència correcta de la partida
     * @param intent és l'intent pel qual es vol rebre feedback
     * @return feedback corresponent a l'intent
     */
    abstract List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent) throws DomainException;

    /**
     * Mètode que retorna el nombre d'aparicions de cada bola en la seqüència
     * @param sequencia és la seqüència de la qual se'n volen extreure els colors
     * @return una llista on la posició del valor numèric de la bola representa el seu nombre d'aparicions
     */
    List<Integer> countColorsBoles(List<Integer> sequencia) throws DomainException {
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i <= Bola.numColors(); ++i) colorList.add(0);

        for (Integer color_sol : sequencia){
            if (!Bola.isValid(color_sol)) {
                throw new InvalidEnumValueException("Bola", color_sol.toString());
            }
            Integer count = colorList.get(color_sol); count++;
            colorList.set(color_sol, count);
        }
        return colorList;
    }

    /**
     * Mètode per saber si una seqüència donada no conté nul·ls
     * @param sequencia és la seqüència de la qual se'n vol saber si està plena
     * @return si està plena o no
     */
    Boolean isPlena(List<Integer> sequencia) {
        for (Integer bola : sequencia) {
            if(bola.equals(Bola.NUL.number()))
                return false;
        }
        return true;
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
     */
    NivellDificultat(int number) {
        this.number = number;
    }

    /**
     * Mètode per obtenir en NivellDificultat corresponent a un nombre
     * @param number és el valor que representa el nivell de dificultat
     * @return el valor de NivellDificultat corresponent al nombre donat
     */
    static NivellDificultat findByNumber(Integer number) throws DomainException {
        for(NivellDificultat nd : values()){
            if( nd.number() == number){
                return nd;
            }
        }
        throw new InvalidEnumValueException("NivellDificultat", number.toString());
    }

    /**
     * Getter del nombre que representa el valor
     */
    int number() { return number; }

    /**
     * Mètode per obtenir el nombre de dificultats
     * @return enter que representa el número de dificultats
     */
    static int numDificultats() {
        return (values().length);
    }

    /**
     * Mètode per saber si un número representa una dificultat vàlida
     *
     * @param num enter que representa una dificultat
     * @return un booleà cert si el número correspon a una dificultat
     */
    static boolean isValid(int num) {
        return (num > 0) && (num <= numDificultats());
    }
}
