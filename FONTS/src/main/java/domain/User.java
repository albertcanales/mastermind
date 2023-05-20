package domain;

import exceptions.domain.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa a un usuari del Mastermind i les seves estadístiques
 * @author Kamil Przybyszewski
 */
class User {

    /**
     * Nom complet de l'usuari
     */
    private final String name;

    /**
     * Nom identificador de l'usuari
     */
    private final String username;

    /**
     * Mínim nombre d'intents per guanyar una partida en cada dificultat
     */
    private List<Integer> personalRecord;

    /**
     * Temps total jugat com a breaker en cada dificultat
     */
    private List<Long> timePlayedFinishedGames;

    /**
     * Nombre de partides guanyades per cada dificultat
     */
    private List<Integer> wonGames;

    /**
     * Nombre de partides perdudes per cada dificultat
     */
    private List<Integer> lostGames;

    /**
     * Ratxa actual de victòries en cada dificultat
     */
    private List<Integer> currentWinStreak;

    /**
     * Millor ratxa de victòries en cada dificultat
     */
    private List<Integer> winStreak;

    /**
     * Mitjana del nombre d'intents de les partides guanyades com a breaker en cada dificultat
     */
    private List<Double> averageAsBreaker;

    /**
     * Mitjana d'intents de les partides com a maker en cada tipus d'algorisme
     */
    private List<Double> averageAsMaker;

    /**
     * Nombre de partides acabades com a maker
     */
    private List<Integer> numGamesAsMaker;

    /**
     * Constructor d'un usuari nou, amb les estadístiques a 0
     * @param name nom de l'usuari
     * @param username username de l'usuari
     */
    User(String name, String username) {
        this.name = name;
        this.username = username;

        initializeBreakerStats();
        initializeMakerStats();
    }

    /**
     * Constructor d'un usuari ja existent
     */
    User(String name, String username, List<Integer> personalRecord, List<Long> timePlayed, List<Integer> wonGames, List<Integer> lostGames, List<Integer> currentWinStreak, List<Integer> winStreak, List<Double> avgAsBreaker, List<Double> avgAsMaker, List<Integer> numGamesAsMaker) throws DomainException {
        comprovaSizeStats(personalRecord, timePlayed, wonGames, lostGames, currentWinStreak, winStreak, avgAsBreaker, avgAsMaker, numGamesAsMaker);

        this.name = name;
        this.username = username;

        this.personalRecord = new ArrayList<>(personalRecord);
        this.timePlayedFinishedGames = new ArrayList<>(timePlayed);
        this.wonGames = new ArrayList<>(wonGames);
        this.lostGames = new ArrayList<>(lostGames);
        this.currentWinStreak = new ArrayList<>(currentWinStreak);
        this.winStreak = new ArrayList<>(winStreak);
        this.averageAsBreaker = new ArrayList<>(avgAsBreaker);

        this.averageAsMaker = new ArrayList<>(avgAsMaker);
        this.numGamesAsMaker = new ArrayList<>(numGamesAsMaker);
    }

    /**
     * Mètode per assignar el valor inicial a les estadístiques com a CodeBreaker de l'usuari
     */
    private void initializeBreakerStats(){
        int numDificultats = NivellDificultat.numDificultats();

        List<Integer> tot_zero = new ArrayList<>();

        timePlayedFinishedGames = new ArrayList<>();
        averageAsBreaker = new ArrayList<>();
        for (int i = 0; i < numDificultats+1; ++i){
            tot_zero.add(0);
            timePlayedFinishedGames.add(0L);
            averageAsBreaker.add(0d);
        }

        personalRecord = new ArrayList<>(tot_zero);
        wonGames = new ArrayList<>(tot_zero);
        lostGames = new ArrayList<>(tot_zero);
        currentWinStreak = new ArrayList<>(tot_zero);
        winStreak = new ArrayList<>(tot_zero);

    }

    /**
     * Mètode per assignar el valor inicial a les estadístiques com a CodeMaker de l'usuari
     */
    private void initializeMakerStats(){
        int numAlgoritmes = TipusAlgorisme.numAlgorismes();

        averageAsMaker = new ArrayList<>();
        numGamesAsMaker = new ArrayList<>();
        for (int i = 0; i < numAlgoritmes+1; ++i){
            averageAsMaker.add(0d);
            numGamesAsMaker.add(0);
        }
    }

    /**
     * Mètode per comprovar les mides de les llistes d'estadístiques
     */
    private void comprovaSizeStats(List<Integer> personalRecord, List<Long> timePlayed, List<Integer> wonGames, List<Integer> lostGames, List<Integer> currentWinStreak, List<Integer> winStreak, List<Double> avgAsBreaker, List<Double> avgAsMaker, List<Integer> numGamesAsMaker) throws InvalidStatSizeException {
        Integer sizeDificultats = NivellDificultat.numDificultats()+1;
        if (!sizeDificultats.equals(personalRecord.size())) throw new InvalidStatSizeException("PersonalRecord", personalRecord.size());
        if (!sizeDificultats.equals(timePlayed.size())) throw new InvalidStatSizeException("timePlayed", timePlayed.size());
        if (!sizeDificultats.equals(wonGames.size())) throw new InvalidStatSizeException("wonGames", wonGames.size());
        if (!sizeDificultats.equals(lostGames.size())) throw new InvalidStatSizeException("lostGames", lostGames.size());
        if (!sizeDificultats.equals(currentWinStreak.size())) throw new InvalidStatSizeException("currentWinStreak", currentWinStreak.size());
        if (!sizeDificultats.equals(winStreak.size())) throw new InvalidStatSizeException("winStreak", winStreak.size());
        if (!sizeDificultats.equals(avgAsBreaker.size())) throw new InvalidStatSizeException("avgAsBreaker", avgAsBreaker.size());

        Integer numAlgoritmes = TipusAlgorisme.numAlgorismes()+1;
        if (!numAlgoritmes.equals(avgAsMaker.size())) throw new InvalidStatSizeException("avgAsMaker", avgAsMaker.size());
        if (!numAlgoritmes.equals(numGamesAsMaker.size())) throw new InvalidStatSizeException("numGamesAsMaker", numGamesAsMaker.size());
    }

    /**
     * Mètode per calcular el valor de les estadístiques totals com a CodeBreaker de l'usuari
     */
    private void calculaTotalStatsBreaker() {
        int indexTotal = NivellDificultat.numDificultats();

        Integer minPR = 0;
        for (int i = 0; i < indexTotal; ++i) {
            Integer iPR = personalRecord.get(i);
            if (minPR == 0 || (iPR != 0 && iPR < minPR)) minPR = iPR;
        }
        personalRecord.set(indexTotal, minPR);
        timePlayedFinishedGames.set(indexTotal, timePlayedFinishedGames.subList(0,indexTotal).stream().reduce(0L, Long::sum));
        wonGames.set(indexTotal, wonGames.subList(0,indexTotal).stream().reduce(0, Integer::sum));
        lostGames.set(indexTotal, lostGames.subList(0,indexTotal).stream().reduce(0, Integer::sum));
        currentWinStreak.set(indexTotal, Collections.max(currentWinStreak.subList(0,indexTotal)));
        winStreak.set(indexTotal, Collections.max(winStreak.subList(0,indexTotal)));

        double avgTotal = 0d;
        int playedDifficulties = 0;
        for (int i = 0; i < indexTotal; ++i) {
            Double iAvg = averageAsBreaker.get(i);
            if (iAvg != 0d) {
                avgTotal += iAvg;
                ++playedDifficulties;
            }
        }
        if (playedDifficulties != 0) avgTotal = avgTotal/playedDifficulties;
        averageAsBreaker.set(indexTotal, avgTotal);
    }

    /**
     * Mètode per calcular el valor de les estadístiques totals com a CodeMaker de l'usuari
     */
    private void calculaTotalStatsMaker() {
        int indexTotal = TipusAlgorisme.numAlgorismes();

        double avgTotal = 0d;
        int playedAlgorismes = 0;
        for (int i = 0; i < indexTotal; ++i) {
            Double iAvg = averageAsMaker.get(i);
            if (iAvg != 0d) {
                avgTotal += iAvg;
                ++playedAlgorismes;
            }
        }
        if (playedAlgorismes != 0) avgTotal = avgTotal/ playedAlgorismes;
        averageAsMaker.set(indexTotal, avgTotal);
        numGamesAsMaker.set(indexTotal, numGamesAsMaker.subList(0,indexTotal).stream().reduce(0, Integer::sum));
    }

    /**
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a CodeBreaker
     * @param dificultat nivell de dificultat de la partida acabada
     * @param intents intents realitzats a la partida
     * @param guanyada si l'usuari ha guanyat la partida
     * @param temps duració de la partida
     */
    void acabarPartidaBreaker(Integer dificultat, Integer intents, Boolean guanyada, Long temps) throws DomainException {
        if (!NivellDificultat.isValid(dificultat)) throw new InvalidEnumValueException("NivellDificultat", dificultat.toString());
        if (intents < 0 || (guanyada && intents==0)) throw new InvalidStatIntentsException(intents.toString());
        if (temps < 0L) throw new InvalidStatTempsException(temps.toString());

        dificultat--; //Els valors de NivellDificultat comencen a 1

        Integer PR = personalRecord.get(dificultat);
        if (PR == 0) personalRecord.set(dificultat, intents);
        else if (intents < PR) personalRecord.set(dificultat, intents);

        Long newTimePlayed = timePlayedFinishedGames.get(dificultat) + temps;
        timePlayedFinishedGames.set(dificultat,newTimePlayed);

        if (guanyada) {
            Integer numwon = wonGames.get(dificultat);
            int totalIntents = (int) (averageAsBreaker.get(dificultat)*numwon);
            totalIntents += intents;
            numwon++;
            Double newAvg = (double) totalIntents /numwon;
            averageAsBreaker.set(dificultat, newAvg);

            wonGames.set(dificultat, numwon);
            Integer streak = currentWinStreak.get(dificultat) + 1;
            currentWinStreak.set(dificultat, streak);
            if (streak > winStreak.get(dificultat)) winStreak.set(dificultat,streak);
        } else {
            Integer numlost = lostGames.get(dificultat) + 1;
            lostGames.set(dificultat, numlost);
            currentWinStreak.set(dificultat, 0);
        }

        calculaTotalStatsBreaker();
    }

    /**
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a codeMaker
     * @param algoritme algoritme seleccionat pel BotBreaker a la partida
     * @param intents intents del BotBreaker a la partida
     */
    void acabarPartidaMaker(Integer algoritme, Integer intents) throws DomainException {
        if (!NivellDificultat.isValid(algoritme)) throw new InvalidEnumValueException("NivellDificultat", algoritme.toString());
        if (intents < 0) throw new InvalidStatIntentsException(intents.toString());

        algoritme--; //Els valors de TipusAlgoritme comencen en 1

        int totalIntents = (int) (averageAsMaker.get(algoritme)*numGamesAsMaker.get(algoritme));
        totalIntents += intents;
        int numGames = numGamesAsMaker.get(algoritme) + 1;
        Double newAvg = (double) totalIntents /numGames;
        averageAsMaker.set(algoritme, newAvg);
        numGamesAsMaker.set(algoritme, numGames);

        calculaTotalStatsMaker();
    }    

    /**
     * Getter del valor de l'atribut name
     */
    String getName() {
        return name;
    }

    /**
     * Getter del valor de l'atribut username
     */
    String getUsername() {
        return username;
    }

    /**
     * Getter del valor de l'atribut personalRecord
     */
    List<Integer> getPersonalRecord(){
        return personalRecord;
    }

    /**
     * Getter del valor de l'atribut timePlayedFinishedGames
     */
    List<Long> getTimePlayed() {
        return timePlayedFinishedGames;
    }

    /**
     * Getter del valor de l'atribut wonGames
     */
    List<Integer> getWonGames() {
        return wonGames;
    }

    /**
     * Getter del valor de l'atribut lostGames
     */
    List<Integer> getLostGames(){
        return lostGames;
    }

    /**
     * Getter del valor de l'atribut currentWinStreak
     */
    List<Integer> getCurrentWinStreak() {
        return currentWinStreak;
    }

    /**
     * Getter del valor de l'atribut winStreak
     */
    List<Integer> getWinStreak() {
        return winStreak;
    }

    /**
     * Getter del valor de l'atribut averageAsBreaker
     */
    List<Double> getAvgAsBreaker() {
        return averageAsBreaker;
    }

    /**
     * Getter del valor de l'atribut averageAsMaker
     */
    List<Double> getAvgAsMaker() {
        return averageAsMaker;
    }

    /**
     * Getter del valor de l'atribut numGamesAsMaker
     */
    List<Integer> getNumGamesAsMaker() {
        return numGamesAsMaker;
    }

    /**
     * Mètode per determinar si els paràmetres d'usuari són adequats (bon format)
     * @param username contrasenya a comprovar
     * @param name nom a comprovar
     * @param password contrasenya a comprovar
     */
    static Boolean isValidUser(String username, String name, String password) {
        return !(username.isBlank() || name.isBlank() || password.isBlank());
    }

    /**
     * Mètode per obtenir el hash en SHA256 (encoded en base64) d'una contrasenya
     * @param password contrasenya per la qual se'n vol obtenir el hash
     */
    static String getPasswordHash(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return String.format("%064x", new BigInteger(1, md.digest()));
    }

}
