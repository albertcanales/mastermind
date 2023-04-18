package persistance;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersistencia {

    public Boolean existsUser(String username) {
        return username.equals("albert");
    }

    public void registerUser(String username, String name, String password) {
        if(!username.equals("albert"))
            System.out.println("Sóc persistència, afegiria l'usuari però sóc només un Mock!");
    }

    public String getPasswordHash(String username) {
        if(username.equals("albert"))
            return "35cc37601c71c54e90af0a3892f70c3a177daee21df74c5fe24786f02bbe3502";
        return null;
    }

    public String getUserName(String username) {
        if(username.equals("albert"))
            return "Albert Canales";
        return null;
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
        return null;
    }

    public List<Long> getUserTimePlayed(String username) {
        if(username.equals("albert"))
            return List.of(400000L, 500000L, 600000L);
        return null;
    }

    public List<Integer> getUserWonGames(String username) {
        if(username.equals("albert"))
            return List.of(20, 15, 10);
        return null;
    }

    public List<Integer> getUserLostGames(String username) {
        if(username.equals("albert"))
            return List.of(0, 5, 10);
        return null;
    }

    public List<Integer> getUserCurrentWinstreak(String username) {
        if(username.equals("albert"))
            return List.of(4, 10, 0);
        return null;
    }

    public List<Integer> getUserWinstreak(String username) {
        if(username.equals("albert"))
            return List.of(20, 10, 5);
        return null;
    }

    public List<Double> getUserAvgAsMaker(String username) {
        if(username.equals("albert"))
            return List.of(4.0, 0.0);
        return null;
    }

    public List<Double> getUserAvgAsBreaker(String username) {
        if(username.equals("albert"))
            return List.of(4.0, 6.0, 8.0);
        return null;
    }

    public List<Integer> getUserNumGamesAsMaker(String username) {
        if(username.equals("albert"))
            return List.of(10, 0);
        return null;
    }

    public Integer getNivellDificultatPartidaGuardada(String username) {
        if(username.equals("albert"))
            return 2;
        return null;
    }

    public Integer getAlgorismePartidaGuardada(String username) {
        return null;
    }

    public List<List<Integer>> getIntentsPartidaGuardada(String username) {
        List<List<Integer>> intents =  new ArrayList<>();
        intents.add(List.of(3, 2, 3, 6));
        intents.add(List.of(0, 1, 0, 0));
        return intents;
    }

    public List<List<Integer>> getFeedbackPartidaGuardada(String username) {
        List<List<Integer>> feedbacks =  new ArrayList<>();
        feedbacks.add(List.of(2, 2, 1, 0));
        return feedbacks;
    }

    public List<Integer> getSolucioPartidaGuardada(String username) {
        if(username.equals("albert"))
            return List.of(1, 2, 3, 4);
        return null;
    }

    public Boolean isBreakerPartidaGuardada(String username) {
        if(username.equals("albert"))
            return true;
        return null;
    }

    public Boolean existsPartidaGuardada(String username) {
        if(username.equals("albert"))
            return true;
        return null;
    }

    public Long getTempsPartidaGuardada(String username) {
        if(username.equals("albert"))
            return 1000L;
        return null;
    }

    public void acabarPartidaGuardada(String username) {
        System.out.println("Sóc persistència, entraria la partida guardada als registres però sóc un Mock!");
    }

    public void esborrarUsuari(String username) {
        System.out.println("Sóc persistència, esborraria l'usuari però sóc un Mock!");
    }

    public void novaPartidaMaker(List<Integer> solucio, Integer algorisme) {
        System.out.println("Sóc persistència, substituiria la partida guardada però sóc un Mock!");
    }
    public void novaPartidaBreaker(Integer nivellDificultat) {
        System.out.println("Sóc persistència, substituiria la partida guardada però sóc un Mock!");
    }
}
