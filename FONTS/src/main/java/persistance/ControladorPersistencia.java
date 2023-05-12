package persistance;

import exceptions.GeneralException;
import exceptions.persistance.PersistanceException;

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

    public List<List<Object>> getRanquing(Integer nivellDificultat) {
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

    public List<Integer> getUserPersonalRecord(String username) {
        if(username.equals("albert"))
            return List.of(2, 4, 7);
        return List.of(0,0,0);
    }

    public List<Long> getUserTimePlayed(String username) {
        if(username.equals("albert"))
            return List.of(400000L, 500000L, 600000L);
        return List.of(0L,0L,0L);
    }

    public List<Integer> getUserWonGames(String username) {
        if(username.equals("albert"))
            return List.of(20, 15, 10);
        return List.of(0,0,0);
    }

    public List<Integer> getUserLostGames(String username) {
        if(username.equals("albert"))
            return List.of(0, 5, 10);
        return List.of(0,0,0);
    }

    public List<Integer> getUserCurrentWinstreak(String username) {
        if(username.equals("albert"))
            return List.of(4, 10, 0);
        return List.of(0,0,0);
    }

    public List<Integer> getUserWinstreak(String username) {
        if(username.equals("albert"))
            return List.of(20, 10, 5);
        return List.of(0,0,0);
    }

    public List<Double> getUserAvgAsMaker(String username) {
        if(username.equals("albert"))
            return List.of(4.0, 0.0);
        return List.of(0.0,0.0);
    }

    public List<Double> getUserAvgAsBreaker(String username) {
        if(username.equals("albert"))
            return List.of(4.0, 6.0, 8.0);
        return List.of(0.0,0.0,0.0);
    }

    public List<Integer> getUserNumGamesAsMaker(String username) {
        if(username.equals("albert"))
            return List.of(10, 0);
        return List.of(0,0);
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
        return gestorPartidesActualsMaker.getSolucio(username);
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
        if (gestorPartidesActualsBreaker.existsPartidaActual(username))
            gestorPartidesActualsBreaker.esborrarPartidaActual(username);
        if (gestorPartidesActualsMaker.existsPartidaActual(username))
            gestorPartidesActualsMaker.esborrarPartidaActual(username);
        //TODO: esborrar del RanquingGestor
    }

    public void novaPartidaMaker(String username, List<Integer> solucio, Integer algorisme) throws GeneralException {
        gestorPartidesActualsMaker.novaPartidaMaker(username, algorisme, solucio);
    }
    public void novaPartidaBreaker(String username, Integer nivellDificultat, List<Integer> solucio) throws GeneralException {
        gestorPartidesActualsBreaker.novaPartidaBreaker(username, 0L, nivellDificultat, solucio);
    }
}
