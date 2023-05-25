package domain;

import exceptions.domain.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor que representa l'estat de les Boles del nostre joc
 *
 * @author Arnau Valls Fusté
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class Taulell {

    /**
     * Nombre d'intents del taulell
     */
    static final int NUMINTENTS = 12;

    /**
     * Nombre de boles de cada seqüència
     */
    static final int NUMBOLES = 4;

    /**
     * Índex de l'últim intent que s'està jugant
     */
    private int intentActual;

    /**
     * Llista de tots els intents del taulell
     */
    private final List<List<Integer>> intents;

    /**
     * Llista de tots els feedbacks del taulell
     */
    private final List<List<Integer>> feedbacks;

    /**
     * Seqüència solució del taulell
     */
    private final List<Integer> solucio;

    /**
     * Mètode que retorna cert si la llista és plena o fals si no ho és
     * @param list una llista d'enters
     * @return un booleà cert o fals depenent de si està plena o no
     */
    private static boolean isPlena(List<Integer> list) {
        for (Integer integer : list) {
            if (integer == Bola.NUL.number()) return false;
        }
        return true;
    }

    /**
     * Mètode que retorna una llista amb NUMBOLES Bola.NUL
     * @return una llista amb NUMBOLES Bola.NUL
     */
    private List<Integer> getNulList() {
        List<Integer> list = new ArrayList<>(Taulell.NUMBOLES);
        for (int i = 0; i < Taulell.NUMBOLES; ++i) {
            list.add(Bola.NUL.number());
        }
        return list;
    }

    /**
     * Mètode que comprova si un Feedback és vàlid (que només tingui Bola.NUL, Bola.BLANC i Bola.NEGRE)
     * @param list una llista d'enters
     * @return un booleà cert o fals depenent de si el Feedback és vàlid o no
     */
    private static boolean isValidFeedback(List<Integer> list) {
        List<Integer> valid = List.of(Bola.NUL.number(),Bola.BLANC.number(),Bola.NEGRE.number());

        for (Integer bola : list) {
            if (!valid.contains(bola)) return false;
        }
        return true;
    }

    /**
     * Mètode que comprova si un Intent és vàlid (que només tingui tots el colors definits a Bola)
     * @param list una llista d'enters
     * @return un booleà cert o fals depenent de si l'Intent és vàlid o no
     */
    private static boolean isValidIntent(List<Integer> list) {
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
     * @throws InvalidIntentsStateException si el conjunt d'intents és invàlid
     * @throws InvalidFeedbackException si algun feedback és invàlid
     * @throws InvalidIntentException si algun intent és invàlid
     * @throws InvalidIntentActualException si el size dels feedbacks no és el size dels intents - 1
     */
    Taulell(List <Integer> sol, List<List<Integer>> inten, List<List<Integer>> feed) throws DomainException{
        if (inten.size() > NUMINTENTS + 1) {
            throw new InvalidNumIntentsException(inten.size(),NUMINTENTS + 1);
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
        if (sol.size() != NUMBOLES) {
            throw new InvalidNumBolesException(sol.size(), NUMBOLES);
        }

        if (inten.size() - 1 != feed.size()) {
            throw new InvalidIntentActualException(inten.size(), feed.size());
        }


        for (List<Integer> integers : inten) {
            if (integers.size() != NUMBOLES) {
                throw new InvalidNumBolesException(integers.size(), NUMBOLES);
            }
            if (!isValidIntent(integers)) {
                throw new InvalidIntentException(integers);
            }
        }
        for (List<Integer> integers : feed) {
            if (integers.size() != NUMBOLES) {
                throw new InvalidNumBolesException(integers.size(), NUMBOLES);
            }
            if (!isValidFeedback(integers)) {
                throw new InvalidFeedbackException(integers);
            }
        }

        intentActual = inten.size() - 1;

        intents = new ArrayList<>(inten);
        feedbacks = new ArrayList<>(feed);

        solucio = sol;
    }

    /**
     * Mètode que retorna si l'intent actual està ple (no hi ha boles nul·les)
     * @return booleà
     */
    Boolean isUltimIntentPle() {
        return isPlena(intents.get(intentActual));
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" de l'intent actual (últim)
     * @return una llista d'enters (Boles)
     */
    List<Integer> getUltimIntent() {
        return intents.get(intentActual);
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" del feedback actual (últim)
     * @return una llista d'enters (Boles) o null si l'intentActual és 0
     */
    List<Integer> getUltimFeedback() {
        if(intentActual == 0) return null;
        return feedbacks.get(intentActual - 1);
    }

    /**
     * Mètode que retorna una llista que conté tots els intents del taulell
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getIntents() {
        return intents;
    }

    /**
     * Mètode que retorna una llista que conté tots els feedbacks del taulell
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getFeedbacks() {
        return feedbacks;
    }

    /**
     * Mètode que retorna l'intent actual del taulell
     * @return un enter amb l'intent actual
     */
    int getNumeroIntent() {
        return intentActual;
    }

    /**
     * Mètode que permet afegir el feedback corresponent per l'intent actual
     * @param feedback llista d'enters que conté boles de color blanc, negre o NUL
     * @throws InvalidNumBolesException si el tamany de feedback no és correcte
     * @throws InvalidFeedbackException si el feeback és invàlid
     * @throws InvalidNumIntentsException si el taulell està ple
     */
    void addFeedback(List<Integer> feedback) throws DomainException {
        if (feedback.size() != NUMBOLES) {
            throw new InvalidNumBolesException(feedback.size(),NUMBOLES);
        }
        if (!isValidFeedback(feedback)) {
            throw new InvalidFeedbackException(feedback);
        }
        if (intentActual == NUMINTENTS) { //si el taulell està ple
            throw new InvalidNumIntentsException(intentActual, NUMINTENTS - 1);
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
     * @throws InvalidNumIntentsException si el taulell està ple
     */
    void setBola(Integer index, Integer bola) throws DomainException {
        if (!Bola.isValid(bola)) {
            throw new InvalidEnumValueException("Bola", bola.toString());
        }
        if (intentActual == NUMINTENTS) {
            throw new InvalidNumIntentsException(intentActual, NUMINTENTS - 1);
        }
        List<Integer> ultimIntent = new ArrayList<>(intents.get(intentActual));
        ultimIntent.set(index, bola);
        intents.set(intentActual, ultimIntent);
    }

    /**
     * Mètode que comprova si una unió d'intents és correcta
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     */
    private static boolean isValidStateIntents(List<List<Integer>> inten) {
        for (int i = 0; i < inten.size() - 1; ++i) { //no mirem l'ultim intent, pot tenir nuls, només mirem si als anteriors hi ha algun nul
            if (!isPlena(inten.get(i))) return false;
        }
        return true;
    }

    /**
     * Mètode que comprova si una unió d'intents i feedbacks és correcta
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     * @param feed una llista d'una llista d'enters representant els feedbacks realitzats
     */
    static boolean isValidIntentsFeedbacks(List<List<Integer>> inten, List<List<Integer>> feed) {
        if (inten.size() > NUMINTENTS + 1 || feed.size() > NUMINTENTS || !isValidStateIntents(inten) || inten.size() - 1 != feed.size()) {
            return false;
        }

        for (List<Integer> integers : inten) {
            if (integers.size() != NUMBOLES) {
                return false;
            }
            if (!isValidIntent(integers)) {
                return false;
            }
        }
        for (List<Integer> integers : feed) {
            if (integers.size() != NUMBOLES) {
                return false;
            }
            if (!isValidFeedback(integers)) {
               return false;
            }
        }
        return true;
    }

}
