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

    private final List<List<Integer>> intents;

    private final List<List<Integer>> feedbacks;

    private final List<Integer> solucio;

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
        for (Integer integer : list) {
            if (Bola.isColor(integer)) return false;
        }
        return true;
    }

    public List<Integer> getNulList() {
        List<Integer> list = new ArrayList<>(Taulell.NUMBOLES);
        for (int i = 0; i < Taulell.NUMBOLES; ++i) {
            list.add(Bola.NUL.number());
        }
        return list;
    }

    private boolean isListListValid(List<List<Integer>> list) {
        for (int i = 0; i < list.size() - 1; ++i) { //no mirem l'ultim intent, pot tenir nuls, només mirem si als anteriors hi ha algun nul
            if (!isPlena(list.get(i))) return false;
        }
        return true;
    }

    private boolean isValidFeedback(List<Integer> list) {
        List<Integer> valid = List.of(Bola.NUL.number(),Bola.BLANC.number(),Bola.NEGRE.number());

        for (Integer bola : list) {
            if (!valid.contains(bola)) return false;
        }

        return true;
    }

    private boolean isValidIntent(List<Integer> list) {
        for (Integer bola : list) {
            if (!Bola.isValid(bola)) return false;
        }

        return true;
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

        intents = new ArrayList<>();
        intents.add(getNulList());
        feedbacks = new ArrayList<>();

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
     * @throws InvalidIntentsStateException si la combinació d'intents i feedback és invàlida
     * @throws InvalidFeedbackException si els feebacks són invàlids
     */
    Taulell(List <Integer> sol, List<List<Integer>> inten, List<List<Integer>> feed) throws DomainException{
        if (inten.size() > NUMINTENTS) {
            throw new InvalidNumIntentsException(inten.size(),NUMINTENTS);
        }
        if (feed.size() > NUMINTENTS) {
            throw new InvalidNumIntentsException(feed.size(),NUMINTENTS);
        }

        if (!isValidStateIntents(inten)) {
            throw new InvalidIntentsStateException();
        }

        if (!isPlena(sol)) {
            throw new InvalidSolutionException();
        }
        if (inten.size() - 1 != feed.size()) {
            throw new InvalidIntentActualException(inten.size(), feed.size());
        }


        for (int i = 0; i < inten.size(); ++i) {
            if (inten.get(i).size() != NUMBOLES) {
                throw new InvalidNumBolesException(inten.get(i).size(),NUMBOLES);
            }
            if (!isValidIntent(inten.get(i))) {
                throw new InvalidIntentException(inten.get(i));
            }
        }
        for (int i = 0; i < feed.size(); ++i) {
            if (feed.get(i).size() != NUMBOLES) {
                throw new InvalidNumBolesException(feed.get(i).size(),NUMBOLES);
            }
            if (!isValidFeedback(feed.get(i))) {
                throw new InvalidFeedbackException(feed.get(i));
            }
        }

        intentActual = inten.size() - 1;

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
     * @throws InvalidFeedbackException si el feeback és invàlid
     */
    void addFeedback(List<Integer> feedback) throws DomainException {
        if (feedback.size() != NUMBOLES) {
            throw new InvalidNumBolesException(feedback.size(),NUMBOLES);
        }
        if (!isValidFeedback(feedback)) {
            throw new InvalidFeedbackException(feedback);
        }
        feedbacks.add(feedback);
        intents.add(getNulList());
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
        //if (intentActual == NUMINTENTS) excepcio taulell ple????
        intents.get(intentActual).set(index, bola);
    }

    /**
     * Mètode que comprova si una unió d'intents és correcta
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     */
    boolean isValidStateIntents(List<List<Integer>> inten) {
        return isListListValid(inten);
    }

}
