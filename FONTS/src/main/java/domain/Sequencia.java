package domain;

import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.InvalidNumBolesException;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor de les "Boles" del joc que representa un conjunt d'aquestes. Els
 * colors de les "Boles" es derivaran a partir d'enters.
 * 
 * @author Arnau Valls Fusté
 */
class Sequencia {

    static final int NUMBOLES = 4;
    private int mida;

    private List<Integer> sequencia;

    /**
     * Crea una seqüència buida
     *
     */
    Sequencia() {
        sequencia = new ArrayList<>(NUMBOLES);
        mida = 0;
        for (int i = 0; i < NUMBOLES ; ++i) {
            sequencia.add(Bola.NUL.number());
        }
    }

    /**
     * Crea una seqüència donada una llista d'enters (Boles)
     * 
     * @param list una llista d'enters (Boles) a utilitzar per inicialitzar la seqüència
     * @throws InvalidNumBolesException si el tamany de list no és correcte
     */
    Sequencia(List<Integer> list) throws InvalidNumBolesException {
        if (list.size() != NUMBOLES) {
            throw new InvalidNumBolesException(list.size(),NUMBOLES);
        }
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
     * Estableix una "Bola" determinada en una posició de la seqüència
     * 
     * @param index enter que representa la posició a establir
     * @param bola  enter més gran o igual a 0 que representa la "Bola"
     * @throws InvalidEnumValueException si bola no és una Bola vàlida
     */
    void setBola(Integer index, Integer bola) throws InvalidEnumValueException {
        if (!Bola.isValid(bola)) {
            throw new InvalidEnumValueException("Bola", bola.toString());
        }
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
