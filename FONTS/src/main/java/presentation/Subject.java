package presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Subjecte del patró de disseny Subjecte-Observer
 * @author Albert Canales (Teoria)
 */
abstract class Subject {

    /**
     * Llista dels observadors adjunts a la instància del subjecte
     */
    private final List<Observer> observersList = new ArrayList<>();

    /**
     * Mètode perquè els observadors es puguin adjuntar al subjecte
     * @param o Observador a adjuntar
     */
    void Attach(Observer o) {
        observersList.add(o);
    }

    /**
     * Mètode perquè els observadors es puguin desadjuntar al subjecte
     * @param o Observador a desadjuntar
     */
    void Detach(Observer o) {
        observersList.remove(o);
    }

    /**
     * Mètode per poder notificar als observadors
     */
    void Notify() {
        for (Observer obs : observersList) {
            obs.Update(this);
        }
    }
}