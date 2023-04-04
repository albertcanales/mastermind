package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Contenidor que representa l'estat de les Boles del nostre joc
 */
public class Taulell {

    public static final int NUMINTENTS = 12, NUMBOLES = 5;
    private int intentActual;

    private List<Sequencia> intents;

    private List<Sequencia> feedbacks;

    private Sequencia solucio;

    /**
     * Crea un Taulell inicialitzat
     */
    public Taulell() {
        intentActual = 0;

        intents = new ArrayList<>(NUMINTENTS);
        feedbacks = new ArrayList<>(NUMINTENTS);

        for (int i = 0; i < NUMINTENTS; ++i) {
            List<Integer> intents_seqbuida = new ArrayList<>(NUMBOLES);
            List<Integer> feedbacks_seqbuida = new ArrayList<>(NUMBOLES);

            for (int j = 0; j < NUMBOLES; ++j) {
                intents_seqbuida.add(Bola.NUL);
                feedbacks_seqbuida.add(Bola.NUL);
            }

            intents.add(new Sequencia(intents_seqbuida));
            feedbacks.add(new Sequencia(feedbacks_seqbuida));
        }

        List<Integer> solucio_seqbuida = new ArrayList<>(NUMBOLES);
        for (int i = 0; i < NUMBOLES; ++i) {
            solucio_seqbuida.add(Bola.NUL);
        }
        solucio = new Sequencia(solucio_seqbuida);

    }

    /**
     * Mètode que retorna una llista que conté totes les "Boles" de l'intent actual
     * (últim)
     * 
     * @return una llista d'enters (Boles)
     */
    public List<Integer> getUltimIntent() {
        return intents.get(intentActual).flatten();
    }

    /**
     * Mètode que permet afegir el feedback corresponent per l'intent actual
     * 
     * @param feedback llista d'enters que conté boles de color blanc, negre o NUL
     */
    public void addFeedback(List<Integer> feedback) {
        feedbacks.set(intentActual, new Sequencia(feedback));
        ++intentActual;
    }

    /**
     * Mètode que retorna la solució de l'estat actual
     * @return una llista d'enters que representen Boles
     */
    public List<Integer> getSolucio() {
        return solucio.flatten();
    }

    /**
     * Mètode que permet establir la solució 
     * @param sol una llista d'enters que representen Boles
     */
    public void setSolucio(List<Integer> sol) {
        solucio = new Sequencia(sol);
    }

    /**
     * Mètode que col·loca una Bola a la posició desitjada de l'intent actual
     * @param index enter que representa la posició a establir
     * @param bola enter que representa la bola a establir
     */
    public void setBola(Integer index, Integer bola) {
        intents.get(intentActual).setBola(index, bola);
    }

}
