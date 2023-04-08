package domain;

import java.util.List;

/**
 * Contenidor de les "Boles" del joc que representa un conjunt d'aquestes. Els
 * colors de les "Boles" es derivaran a partir d'enters.
 *
 * 
 * @author Arnau Valls Fusté
 */
class Sequencia {

    private int mida;

    private List<Integer> sequencia;

    /**
     * Crea una sequència donada una llista d'enters (Boles)
     * 
     * @param list
     */
    Sequencia(List<Integer> list) {
        sequencia = list;
        mida = 0;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > Bola.NUL.number())
                ++mida;
        }

    }

    /**
     * Retorna cert si la Seqüència és plena o fals si no ho és
     * 
     * @return un booleà cert o fals depenent de si està plena o no
     */
    Boolean isPlena() {
        if (mida == sequencia.size())
            return true;
        else
            return false;

    }

    /**
     * Retorna cert si la Seqüència és buida o fals si no ho és
     * 
     * @return un booleà cert o fals depenent de si està buida o no
     */
    Boolean isBuida() {
        if (mida == 0)
            return true;
        else
            return false;
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
        if ((sequencia.get(index) == Bola.NUL.number()) && (bola > Bola.NUL.number()))
            ++mida;
        else if ((sequencia.get(index) > Bola.NUL.number()) && (bola == Bola.NUL.number()))
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
