package domain;

import domain.exceptions.DomainException;
import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.InvalidNumBolesException;
import domain.exceptions.InvalidNumIntentsException;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor que representa l'estat de les Boles del nostre joc
 */
class Taulell {

    static final int NUMINTENTS = 12, NUMBOLES = Sequencia.NUMBOLES;
    private int intentActual;

    private List<Sequencia> intents;

    private List<Sequencia> feedbacks;

    private Sequencia solucio;

    /**
     * Crea un Taulell buit
     * @param sol una llista d'enters representant la solució del Taulell
     * @throws DomainException si el tamany de list no és correcte
     */
    Taulell(List <Integer> sol) throws DomainException {
        intentActual = 0;

        intents = new ArrayList<>(NUMINTENTS);
        feedbacks = new ArrayList<>(NUMINTENTS);

        for (int i = 0; i < NUMINTENTS; ++i) {
            intents.add(new Sequencia());
            feedbacks.add(new Sequencia());
        }

        solucio = new Sequencia(sol);
    }

    /**
     * Crea un Taulell inicialitzat amb els paràmetres donats
     * @param sol una llista d'enters representant la solució del Taulell
     * @param inten una llista d'una llista d'enters representant els intents realitzats
     * @param feed una llista d'una llista d'enters representant els feedbacks rebuts
     * @throws DomainException si el tamany d'alguna list no és correcte
     */
    Taulell(List <Integer> sol, List<List<Integer>> inten, List<List<Integer>> feed) throws DomainException {
        if (inten.size() != NUMINTENTS) {
            throw new InvalidNumIntentsException(inten.size(),NUMINTENTS);
        }
        if (feed.size() != NUMINTENTS) {
            throw new InvalidNumIntentsException(feed.size(),NUMINTENTS);
        }

        intents = new ArrayList<>(NUMINTENTS);
        feedbacks = new ArrayList<>(NUMINTENTS);

        boolean foundEmpty = false;
        for (int i = 0; i < NUMINTENTS; ++i) {
            if (!foundEmpty && new Sequencia(feed.get(i)).isBuida())
            {
                intentActual = i;
                foundEmpty = true;
            }
            intents.add(new Sequencia(inten.get(i)));
            feedbacks.add(new Sequencia(feed.get(i)));
        }

        if (!foundEmpty) intentActual = NUMINTENTS - 1;

        solucio = new Sequencia(sol);
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" de l'intent actual
     * (últim)
     * 
     * @return una llista d'enters (Boles)
     */
    List<Integer> getUltimIntent() {
        return intents.get(intentActual).flatten();
    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" del feedback actual
     * (últim)
     *
     * @return una llista d'enters (Boles)
     */
    List<Integer> getUltimFeedback() {
        // intentActual ha de ser > 0
        return feedbacks.get(intentActual - 1).flatten();
    }

    /**
     * Mètode que retorna una llista que conté tots els intents del taulell
     *
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getIntents() {
        List<List<Integer>> inten = new ArrayList<>(NUMINTENTS);

        for (int i = 0; i < NUMINTENTS; ++i) {
            inten.add(intents.get(i).flatten());
        }

        return inten;
    }

    /**
     * Mètode que retorna una llista que conté tots els feedbacks del taulell
     *
     * @return una llista d'una llista d'enters
     */
    List<List<Integer>> getFeedbacks() {
        List<List<Integer>> feeds = new ArrayList<>(NUMINTENTS);

        for (int i = 0; i < NUMINTENTS; ++i) {
            feeds.add(feedbacks.get(i).flatten());
        }

        return feeds;
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
     * @throws DomainException si el tamany de feedback no és correcte
     */
    void addFeedback(List<Integer> feedback) throws DomainException {
        //per exceptions -> if (feedback.size() != NUMBOLES)
        feedbacks.set(intentActual, new Sequencia(feedback));
        ++intentActual;
    }

    /**
     * Mètode que retorna la solució de l'estat actual
     * @return una llista d'enters que representen Boles
     */
    List<Integer> getSolucio() {
        return solucio.flatten();
    }

    /**
     * Mètode que col·loca una Bola a la posició desitjada de l'intent actual
     * @param index enter que representa la posició a establir
     * @param bola enter que representa la bola a establir
     * @throws DomainException si bola no és una Bola vàlida
     */
    void setBola(Integer index, Integer bola) throws DomainException {
        intents.get(intentActual).setBola(index, bola);
    }

}
