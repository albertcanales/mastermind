package domain;

import java.util.List;

/**
 * Controlador de la capa de domini
 * @author Albert Canales
 */
public class ControladorDomini {

    ControladorPartida controladorPartida;

    /**
     * Getter del rànquing de les millors partides d'una dificultat concreta
     * @param nivellDificultat nombre del nivell de dificultat
     * @param nombrePartides nombre de partides a mostrar
     * @return Una llista que conté tuples amb (username: String, intents: Integer, temps: Duration)
     * @author Albert Canales
     */
    public List<List<Object>> getRanquing(Integer nivellDificultat, Integer nombrePartides) {
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getPersonalRecord() {
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari actual
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getTimePlayed() {
        return null;
    }

    /**
     * Getter de victòries i derrotes
     * @return Una llista amb parelles (nombre de victòries, nombre de derrotes) per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<List<Integer>> getWinLost() {
        return null;
    }

    /**
     * Getter de ratxa de victòries
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     * @author Albert Canales
     */
    public List<Integer> getWinstreak() {
        return null;
    }

    /**
     * Getter de mitjana d'intents com a maker
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada dificultat
     * @author Albert Canales
     */
    public List<Double> getAverageAsMaker() {
        return null;
    }

    /**
     * Getter de la solució de la partida actual
     * @return La seqüència solució de la partida actual
     * @author Albert Canales
     */
    public List<Integer> getSolucio() {
        return controladorPartida.getSequenciaSolucio();
    }

    /**
     * Mètode per a col·locar una bola en l'intent actual de la partida actual
     * @param index La posició on es vol col·locar la bola
     * @param bola La bola que es vol col·locar
     * @author Albert Canales
     */
    public void setBola(Integer index, Integer bola) {
        controladorPartida.setBola(index, bola);
    }

    /**
     * Mètode per a validar l'intent actual en la partida actual
     * @return El feedback corresponent a l'intent donat
     * @author Albert Canales
     */
    public List<Integer> validarSequencia() {
        return controladorPartida.validarSequencia();
    }
}
