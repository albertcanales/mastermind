package presentation;

/**
 * Observer del patró de disseny Subjecte-Observer
 * @author Teoria
 */
interface Observer {

    /**
     * Mètode perquè els observadors puguin rebre una actualització del subjecte
     * @param s Subjecte del qual prové la notificació
     */
    void Update(Subject s);

}