package persistance;

import exceptions.GeneralException;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersistencia {

    private final GestorUsuaris gestorUsuaris;
    private final GestorPartidesActualsBreaker gestorPartidesActualsBreaker;
    private final GestorPartidesActualsMaker gestorPartidesActualsMaker;
    private static final String basePath = "./db/"; //TODO: directoris dinàmics
    public ControladorPersistencia() throws GeneralException {
        gestorUsuaris = new GestorUsuaris(basePath);
        gestorPartidesActualsBreaker = new GestorPartidesActualsBreaker(basePath);
        gestorPartidesActualsMaker = new GestorPartidesActualsMaker(basePath);
    }
    public Boolean existsUser(String username) throws GeneralException {
        return gestorUsuaris.existsUser(username);

    }

    public void registerUser(String username, String name, String password) throws GeneralException {
        gestorUsuaris.registerUser(username, name, password);
    }

    public String getPasswordHash(String username) throws GeneralException {
        return gestorUsuaris.getPasswordHash(username);
    }

    public String getUserName(String username) throws GeneralException {
        return gestorUsuaris.getUserName(username);
    }

    public List<List<List<String>>> getRanquing(){ //TODO: Recuperar de persistencia
        return null;
    }

    public List<List<Object>> getRanquing(Integer nivellDificultat) { //TODO: Recuperar de persistencia
        List<List<Object>> ranquing = new ArrayList<>();
        if(nivellDificultat == 1) {
            ranquing.add(List.of("albert", 2, 100L));
            ranquing.add(List.of("mar", 4, 200L));
        }
        else if (nivellDificultat == 2) {
            ranquing.add(List.of("arnau", 5, 400L));
            ranquing.add(List.of("kamil", 7, 300L));
            ranquing.add(List.of("mar", 10, 700L));
        }
        else if (nivellDificultat == 3) {
            // No n'hi ha cap
        }
        return ranquing;
    }

    public List<Integer> getUserPersonalRecord(String username) throws GeneralException {
        return gestorUsuaris.getPersonalRecord(username);
    }

    public List<Long> getUserTimePlayed(String username) throws GeneralException {
        return gestorUsuaris.getTimePlayed(username);
    }

    public List<Integer> getUserWonGames(String username) throws GeneralException {
        return gestorUsuaris.getWonGames(username);
    }

    public List<Integer> getUserLostGames(String username) throws GeneralException {
        return gestorUsuaris.getLostGames(username);
    }

    public List<Integer> getUserCurrentWinstreak(String username) throws GeneralException {
        return gestorUsuaris.getCurrentWinstreak(username);
    }

    public List<Integer> getUserWinstreak(String username) throws GeneralException {
        return gestorUsuaris.getWinstreak(username);
    }

    public List<Double> getUserAvgAsMaker(String username) throws GeneralException {
        return gestorUsuaris.getAvgAsMaker(username);
    }

    public List<Double> getUserAvgAsBreaker(String username) throws GeneralException {
        return gestorUsuaris.getAvgAsBreaker(username);
    }

    public List<Integer> getUserNumGamesAsMaker(String username) throws GeneralException {
        return gestorUsuaris.getNumGamesAsMaker(username);
    }

    public void setUserStats(String username, List<Integer> pr, List<Long> time, List<Integer> won, List<Integer> lost,
                             List<Integer> currentWs, List<Integer> ws, List<Double> avgMaker, List<Double> avgBreaker,
                             List<Integer> gamesMaker) throws GeneralException {
        gestorUsuaris.setStats(username, pr, time, won, lost, currentWs, ws, avgMaker, avgBreaker, gamesMaker);
    }

    public Integer getNivellDificultatPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActualsBreaker.getDificultat(username);
    }

    public Integer getAlgorismePartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActualsMaker.getAlgorisme(username);
    }

    public List<List<Integer>> getIntentsPartidaGuardada(String username) throws GeneralException {
        if (gestorPartidesActualsBreaker.existsPartidaActual(username)) {
            return gestorPartidesActualsBreaker.getIntents(username);
        }
        return gestorPartidesActualsMaker.getIntents(username);
    }

    public List<List<Integer>> setIntentsPartidaGuardada(String username) {
        List<List<Integer>> intents =  new ArrayList<>();
        intents.add(List.of(3, 2, 3, 6));
        intents.add(List.of(0, 1, 0, 0));
        return intents;
    }

    public List<List<Integer>> getFeedbackPartidaGuardada(String username) throws GeneralException {
        if (gestorPartidesActualsBreaker.existsPartidaActual(username)) {
            return gestorPartidesActualsBreaker.getFeedbacks(username);
        }
        return gestorPartidesActualsMaker.getFeedbacks(username);
    }

    public List<List<Integer>> setFeedbackPartidaGuardada(String username) {
        List<List<Integer>> feedbacks =  new ArrayList<>();
        feedbacks.add(List.of(2, 2, 1, 0));
        return feedbacks;
    }

    public List<Integer> getSolucioPartidaGuardada(String username) throws GeneralException {
        if (gestorPartidesActualsBreaker.existsPartidaActual(username)) {
            return gestorPartidesActualsBreaker.getSolucio(username);
        }
        return gestorPartidesActualsMaker.getSolucio(username); //TODO: treure logica cap a domini
    }

    public Boolean isBreakerPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActualsBreaker.existsPartidaActual(username);
    }

    public Boolean existsPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActualsBreaker.existsPartidaActual(username) || gestorPartidesActualsMaker.existsPartidaActual(username);
    }

    public Long getTempsPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActualsBreaker.getTemps(username);
    }

    public void acabarPartidaGuardada(String username) {
        System.out.println("Sóc persistència, entraria la partida guardada als registres però sóc un Mock!"); //TODO: algo s'ha de fer
    }

    public void esborrarUsuari(String username) throws GeneralException {
        gestorUsuaris.esborrarUsuari(username);
        if (gestorPartidesActualsBreaker.existsPartidaActual(username)) //TODO: treure logica cap a domini
            gestorPartidesActualsBreaker.esborrarPartidaActual(username);
        if (gestorPartidesActualsMaker.existsPartidaActual(username))
            gestorPartidesActualsMaker.esborrarPartidaActual(username);

    }

    public void esborrarPartidaMaker(String username) throws GeneralException {
        gestorPartidesActualsMaker.esborrarPartidaActual(username);
    }

    public void esborrarPartidaBreaker(String username) throws GeneralException {
        gestorPartidesActualsBreaker.esborrarPartidaActual(username);
    }

    //TODO: esborrar del RanquingGestor

    public void novaPartidaMaker(String username, List<Integer> solucio, Integer algorisme, List<List<Integer>> intents,
                                 List<List<Integer>> feedback) throws GeneralException {
        gestorPartidesActualsMaker.novaPartidaMaker(username, algorisme, solucio, intents, feedback);
    }
    public void novaPartidaBreaker(String username, Integer nivellDificultat, Long temps, List<Integer> solucio, List<List<Integer>> intents,
                                   List<List<Integer>> feedback) throws GeneralException {
        gestorPartidesActualsBreaker.novaPartidaBreaker(username, temps, nivellDificultat, solucio, intents, feedback);
    }
}
