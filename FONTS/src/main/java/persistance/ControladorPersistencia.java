package persistance;

import exceptions.GeneralException;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersistencia {

    private final GestorUsuaris gestorUsuaris;
    private final GestorPartidesActuals gestorPartidesActuals;
    private final GestorRanquing gestorRanquing;
    private static final String basePath = "./db/"; //TODO: directoris dinàmics
    public ControladorPersistencia() throws GeneralException {
        gestorUsuaris = new GestorUsuaris(basePath);
        gestorPartidesActuals = new GestorPartidesActuals(basePath);
        gestorRanquing = new GestorRanquing(basePath);
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

    public List<List<List<String>>> getRanquing(){
        List<List<List<String>>> ranquing = new ArrayList<>();
        for (int i = 0; i < 3; ++i){
            ranquing.add(new ArrayList<>());
        }

        return ranquing;
    }
    public List<List<Object>> getRanquing(Integer nivellDificultat) throws GeneralException {
        return gestorRanquing.getRanquing(nivellDificultat - 1);
    }

    public void setRanquing(Integer nivellDificultat, List<List<Object>> ranquing) throws GeneralException {
        gestorRanquing.setRanquing(nivellDificultat - 1, ranquing);
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
        return gestorPartidesActuals.getDificultat(username);
    }

    public Integer getAlgorismePartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.getAlgorisme(username);
    }

    public List<List<Integer>> getIntentsPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.getIntents(username);
    }

    public List<List<Integer>> setIntentsPartidaGuardada(String username) {
        List<List<Integer>> intents =  new ArrayList<>();
        intents.add(List.of(3, 2, 3, 6));
        intents.add(List.of(0, 1, 0, 0));
        return intents;
    }

    public List<List<Integer>> getFeedbackPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.getFeedbacks(username);
    }

    public List<List<Integer>> setFeedbackPartidaGuardada(String username) {
        List<List<Integer>> feedbacks =  new ArrayList<>();
        feedbacks.add(List.of(2, 2, 1, 0));
        return feedbacks;
    }

    public List<Integer> getSolucioPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.getSolucio(username);
    }

    public Boolean existsPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.existsPartidaActual(username);
    }

    public Boolean partidaGuardadaisBreaker(String username) throws GeneralException {
        return gestorPartidesActuals.isBreaker(username);
    }


    public Long getTempsPartidaGuardada(String username) throws GeneralException {
        return gestorPartidesActuals.getTemps(username);
    }

    public void acabarPartidaGuardada(String username) {
        System.out.println("Sóc persistència, entraria la partida guardada als registres però sóc un Mock!"); //TODO: algo s'ha de fer
    }

    public void esborrarUsuari(String username) throws GeneralException {
        gestorUsuaris.esborrarUsuari(username);
    }

    public void esborrarPartida(String username) throws GeneralException {
        gestorPartidesActuals.esborrarPartidaActual(username);
    }

    public void novaPartidaMaker(String username, List<Integer> solucio, Integer algorisme, List<List<Integer>> intents,
                                 List<List<Integer>> feedback) throws GeneralException {
        gestorPartidesActuals.novaPartida(username, false, null, null, algorisme, solucio, intents, feedback);
    }
    public void novaPartidaBreaker(String username, Integer nivellDificultat, Long temps, List<Integer> solucio, List<List<Integer>> intents,
                                   List<List<Integer>> feedback) throws GeneralException {
        gestorPartidesActuals.novaPartida(username, true, temps, nivellDificultat, null, solucio, intents, feedback);
    }
}
