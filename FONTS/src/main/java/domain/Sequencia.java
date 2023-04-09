package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor de les "Boles" del joc que representa un conjunt d'aquestes. Els
 * colors de les "Boles" es derivaran a partir d'enters.
 * 
 * @author Arnau Valls Fusté
 */
class Sequencia {

    private int mida;

    private List<Integer> sequencia;

    /**
     * Crea una seqüència buida
     *
     * @param size enter que representa la mida de la seqüència
     */
    Sequencia(Integer size) {
        sequencia = new ArrayList<>(size);
        mida = 0;
        for (int i = 0; i < size ; ++i) {
            sequencia.add(Bola.NUL.number());
        }
    }

    /**
     * Crea una seqüència donada una llista d'enters (Boles)
     * 
     * @param list 
     */
    Sequencia(List<Integer> list) {
        sequencia = list;
        mida = 0;
        for (Integer bola : list) {
            if (Bola.isColor(bola))
                ++mida;
        }
    }

    /**
     * Retorna cert si la Seqüència és plena o fals si no ho és
     * 
     * @return un booleà cert o fals depenent de si està plena o no
     */
    Boolean isPlena() {
        return mida == sequencia.size();
    }

    /**
     * Retorna cert si la Seqüència és buida o fals si no ho és
     * 
     * @return un booleà cert o fals depenent de si està buida o no
     */
    Boolean isBuida() {
        return mida == 0;
    }

    /**
     * Retorna un enter més gran o igual a 0 que representa la "Bola" desitjada
     * 
     * @param index enter que representa la posició de la "Bola" desitjada
     * @return un Enter que representa una "Bola"
     */
    Integer getBola(Integer index) {
        return sequencia.get(index);
    }

    /**
     * Estableix una "Bola" determinada en una posició de la seqüència
     * 
     * @param index enter que representa la posició a establir
     * @param bola  enter més gran o igual a 0 que representa la "Bola"
     */
    void setBola(Integer index, Integer bola) {
        if ((sequencia.get(index) == Bola.NUL.number()) && (Bola.isColor(bola)))
            ++mida;
        else if ((Bola.isColor(sequencia.get(index))) && (bola == Bola.NUL.number()))
            --mida;
        sequencia.set(index, bola);
    }

    /**
     * Retorna la llista d'enters (Boles) que formen la Seqüència
     * 
     * @return una llista d'enters
     */
    List<Integer> flatten() {
        return sequencia;
    }

}
