package persistance;

import java.time.Duration;
import java.util.List;

public class ControladorPersistencia {

    public Boolean existsUser(String username) {
        return null;
    }

    public void registerUser(String username, String name, String password) { }

    public String getPasswordHash(String username) {
        return null;
    }

    public String getUserName(String username) {
        return null;
    }

    public List<List<Object>> getRanquing(Integer nivellDificultat, Integer nombrePartides) {
        return null;
    }

    public List<Integer> getUserPersonalRecord(String username) {
        return null;
    }

    public List<Long> getUserTimePlayed(String username) {
        return null;
    }

    public List<Integer> getUserWonGames(String username) {
        return null;
    }

    public List<Integer> getUserLostGames(String username) {
        return null;
    }

    public List<Integer> getUserWinstreak(String username) {
        return null;
    }

    public List<Double> getUserAvgAsMaker(String username) {
        return null;
    }

    public List<Double> getUserAvgAsBreaker(String username) {
        return null;
    }

    public List<Integer> getUserNumGamesAsMaker(String username) {
        return null;
    }

    public String getUserPartidaGuardada() {
        return null;
    }

    public Integer getNivellDificultatPartidaGuardada() {
        return null;
    }

    public List<List<Integer>> getIntentsPartidaGuardada() {
        return null;
    }

    public List<List<Integer>> getFeedbackPartidaGuardada() {
        return null;
    }

    public List<Integer> getSolucioPartidaGuardada() {
        return null;
    }

    public Boolean isBreakerPartidaGuardada(String username) {
        return null;
    }

    public Boolean existsPartidaGuardada(String username) {
        return null;
    }

    public Duration getTempsPartidaGuardada() {
        return null;
    }

    public void acabarPartidaGuardada() { }
}
