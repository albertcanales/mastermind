package domain;

import domain.exceptions.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor que representa l'estat de les Boles del nostre joc
 *
 * @author Arnau Valls Fusté
 */
class Taulell {

    static final int NUMINTENTS = 12, NUMBOLES = 4;
    private int intentActual;

    private List<List<Integer>> intents;

    private List<List<Integer>> feedbacks;

    private List<Integer> solucio;

    /**
     * Retorna cert si la llista és plena o fals si no ho és
     * @param list una llista d'enters
     * @return un booleà cert o fals depenent de si està plena o no
     */
    private boolean isPlena(List<Integer> list) {
        for (Integer integer : list) {
            if (integer == Bola.NUL.number()) return false;
        }
        return true;
    }

    /**
     * Retorna cert si la llista és buida o fals si no ho és
     * @param list una llista d'enters
     * @return un booleà cert o fals depenent de si està buida o no
     */
    private boolean isBuida(List<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (Bola.isColor(list.get(i))) return false;
        }
        return true;
    }

    private List<Integer> getNulList() {
        List<Integer> list = new ArrayList<>(Taulell.NUMBOLES);
        for (int i = 0; i < Taulell.NUMBOLES; ++i) {
            list.add(Bola.NUL.number());
        }
        return list;
    }

    private List<List<Integer>> getNulListList() {
        List<List<Integer>> list = new ArrayList<>(Taulell.NUMINTENTS);
        for (int i = 0; i < Taulell.NUMINTENTS; ++i) {
            list.add(getNulList());
        }
        return list;
    }

    private void fillList(List<List<Integer>> list) {
        for (int i = list.size(); i < NUMINTENTS; ++i) {
            list.add(getNulList());
        }
    }

    private boolean isListListValid(List<List<Integer>> list) {
        int firstnull = -1;
        int lastfull = -1;

        for (int i = 0; i < list.size(); ++i) {
            if (firstnull == -1 && isBuida(list.get(i))) firstnull = i;
            if (!isBuida(list.get(i))) lastfull = i;
        }
        return lastfull > firstnull;
    }

    private Integer firstNull(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (isBuida(list.get(i))) return i;
        }
        return NUMINTENTS;
    }

    /**
     * Crea un Taulell buit
     * @param sol una llista d'enters representant la solució del Taulell
     * @throws InvalidNumBolesException si el tamany de la solució no és correcte
     * @throws InvalidSolutionException si la solució no és vàlida (té alguna Bola NUL)
     */
    Taulell(List <Integer> sol) throws DomainException {
        if (!isPlena(sol)) {
            throw new InvalidSolutionException();
        }

        if (sol.size() != NUMBOLES) {
            throw new InvalidNumBolesException(sol.size(), NUMBOLES);
        }

        intentActual = 0;

        intents = getNulListList();
        feedbacks = getNulListList();

        solucio = sol;
    }

    /**
     * Crea un Taulell inicialitzat amb els paràmetres donats
     * @param sol una llista d'enters representant la solució del Taulell
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     * @param feed una llista d'una llista d'enters representant els feedbacks rebuts
     * @throws InvalidNumBolesException si el tamany de boles d'alguna list no és correcte
     * @throws InvalidNumIntentsException si el tamany d'intents d'alguna list no és correcte
     * @throws InvalidSolutionException si la solució no és vàlida (té alguna Bola NUL)
     * @throws InvalidTaulellState si els intens i o feebacks són invàlids
     */
    Taulell(List <Integer> sol, List<List<Integer>> inten, List<List<Integer>> feed) throws DomainException{
        if (inten.size() > NUMINTENTS) {
            throw new InvalidNumIntentsException(inten.size(),NUMINTENTS);
        }
        else if (inten.size() < NUMINTENTS) {
            fillList(inten);
        }
        if (feed.size() > NUMINTENTS) {
            throw new InvalidNumIntentsException(feed.size(),NUMINTENTS);
        }
        if (feed.size() < NUMINTENTS) {
            fillList(feed);
        }

        if (isValidIntentsFeedback(inten, feed)) {
            throw new InvalidTaulellState();
        }

        if (!isPlena(sol)) {
            throw new InvalidSolutionException();
        }

        boolean foundEmpty = false;
        for (int i = 0; i < NUMINTENTS; ++i) {
            if (inten.get(i).size() != NUMBOLES) {
                throw new InvalidNumBolesException(inten.get(i).size(),NUMBOLES);
            }
            if (feed.get(i).size() != NUMBOLES) {
                throw new InvalidNumBolesException(feed.get(i).size(),NUMBOLES);
            }

            if (!foundEmpty && isBuida(feed.get(i))) {
                intentActual = i;
                foundEmpty = true;
            }
        }
        if (!foundEmpty) intentActual = NUMINTENTS - 1;

        intents = inten;
        feedbacks = feed;

        solucio = sol;
    }

    /**
     * Mètode que retorna si l'intent actual està ple (no hi ha boles nul·les)
     *
     * @return booleà
     */
    Boolean isUltimIntentPle() {
        return isPlena(intents.get(intentActual));
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" de l'intent actual
     * (últim)
     * 
     * @return una llista d'enters (Boles)
     */
    List<Integer> getUltimIntent() {
        return intents.get(intentActual);
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" del feedback actual
     * (últim)
     *
     * @return una llista d'enters (Boles)
     */
    List<Integer> getUltimFeedback() {
        // intentActual ha de ser > 0
        return feedbacks.get(intentActual - 1);
    }

    /**
     * Mètode que retorna una llista que conté tots els intents del taulell
     *
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getIntents() {
        return intents;
    }

    /**
     * Mètode que retorna una llista que conté tots els feedbacks del taulell
     *
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getFeedbacks() {
        return feedbacks;
    }

    /**
     * Mètode que retorna l'intent actual del taulell
     *
     * @return un enter amb l'intent actual
     */
    int getNumeroIntent() {
        return intentActual;
    }

    /**
     * Mètode que permet afegir el feedback corresponent per l'intent actual
     * 
     * @param feedback llista d'enters que conté boles de color blanc, negre o NUL
     * @throws InvalidNumBolesException si el tamany de feedback no és correcte
     */
    void addFeedback(List<Integer> feedback) throws InvalidNumBolesException {
        if (feedback.size() != NUMBOLES) {
            throw new InvalidNumBolesException(feedback.size(),NUMBOLES);
        }
        feedbacks.set(intentActual, feedback);
        ++intentActual;
    }

    /**
     * Mètode que retorna la solució de l'estat actual
     * @return una llista d'enters que representen Boles
     */
    List<Integer> getSolucio() {
        return solucio;
    }

    /**
     * Mètode que col·loca una Bola a la posició desitjada de l'intent actual
     * @param index enter que representa la posició a establir
     * @param bola enter que representa la bola a establir
     * @throws InvalidEnumValueException si bola no és una Bola vàlida
     */
    void setBola(Integer index, Integer bola) throws InvalidEnumValueException {
        if (!Bola.isValid(bola)) {
            throw new InvalidEnumValueException("Bola", bola.toString());
        }
        intents.get(intentActual).set(index, bola);
    }

    /**
     * Mètode que comprova si una unió de intents i feedbacks és correcta
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     * @param feed una llista d'una llista d'enters representant els feedbacks rebuts
     */
    boolean isValidIntentsFeedback(List<List<Integer>> inten, List<List<Integer>> feed) {
        return isListListValid(inten) && isListListValid(feed) && firstNull(inten) >= firstNull(feed) && firstNull(inten) - firstNull(feed) <= 1;
    }

}
