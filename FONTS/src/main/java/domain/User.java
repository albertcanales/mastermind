package domain;

import domain.exceptions.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que representa a un usuari del Joc i les seves estadístiques
 * @author Kamil Przybyszewski
 */
public class User {
    private String name;
    private String username;

    private List<Integer> personalRecord;
    private List<Long> timePlayedFinishedGames;
    private List<Integer> wonGames;
    private List<Integer> lostGames;
    private List<Integer> currentWinStreak;
    private List<Integer> winStreak;
    private List<Double> averageAsBreaker;


    private List<Double> averageAsMaker;
    private List<Integer> numGamesAsMaker;

    /**
     * Mètode per assignar el valor inicial a les estadístiques com a CodeBreaker de l'usuari
     * @author Kamil Przybyszewski
     */
    private void initializeBreakerStats(){
        int numDificultats = NivellDificultat.numDificultats();

        List<Integer> tot_zero = new ArrayList<>();

        timePlayedFinishedGames = new ArrayList<>();
        averageAsBreaker = new ArrayList<>();
        for (int i = 0; i < numDificultats; ++i){
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
     * @author Kamil Przybyszewski
     */
    private void initializeMakerStats(){
        Integer numAlgoritmes = TipusAlgorisme.numAlgorismes();

        averageAsMaker = new ArrayList<>(); numGamesAsMaker = new ArrayList<>();
        for (int i = 0; i < numAlgoritmes; ++i){
            averageAsMaker.add(0d);
            numGamesAsMaker.add(0);
        }
    }

    /**
     * Mètode per comprovar els tamanys de les estadístiques
     * @author Kamil Przybyszewski
     */
    private void comprovaSizeStats(List<Integer> personalRecord, List<Long> timePlayed, List<Integer> wonGames, List<Integer> lostGames, List<Integer> currentWinStreak, List<Integer> winStreak, List<Double> avgAsBreaker, List<Double> avgAsMaker, List<Integer> numGamesAsMaker) throws InvalidStatSizeException {
        Integer numDificultats = NivellDificultat.numDificultats();
        if (!numDificultats.equals(personalRecord.size())) throw new InvalidStatSizeException("PersonalRecord", personalRecord.size());
        if (!numDificultats.equals(timePlayed.size())) throw new InvalidStatSizeException("timePlayed", timePlayed.size());
        if (!numDificultats.equals(wonGames.size())) throw new InvalidStatSizeException("wonGames", wonGames.size());
        if (!numDificultats.equals(lostGames.size())) throw new InvalidStatSizeException("lostGames", lostGames.size());
        if (!numDificultats.equals(currentWinStreak.size())) throw new InvalidStatSizeException("currentWinStreak", currentWinStreak.size());
        if (!numDificultats.equals(winStreak.size())) throw new InvalidStatSizeException("winStreak", winStreak.size());
        if (!numDificultats.equals(avgAsBreaker.size())) throw new InvalidStatSizeException("avgAsBreaker", avgAsBreaker.size());

        Integer numAlgoritmes = TipusAlgorisme.numAlgorismes();
        if (!numAlgoritmes.equals(avgAsMaker.size())) throw new InvalidStatSizeException("avgAsMaker", avgAsMaker.size());
        if (!numAlgoritmes.equals(numGamesAsMaker.size())) throw new InvalidStatSizeException("numGamesAsMaker", numGamesAsMaker.size());
    }

    /**
     * Constructor d'un usuari nou, amb les estadístiques a 0
     * @param name nom de l'usuari
     * @param username username de l'usuari
     * @author Kamil Przybyszewski
     */
    User(String name, String username) {
        this.name = name;
        this.username = username;

        initializeBreakerStats();
        initializeMakerStats();
    }

    /**
     * Constructor d'un usuari ja existent
     * @author Kamil Przybyszewski
     */
    User(String name, String username, List<Integer> personalRecord, List<Long> timePlayed, List<Integer> wonGames, List<Integer> lostGames, List<Integer> currentWinStreak, List<Integer> winStreak, List<Double> avgAsBreaker, List<Double> avgAsMaker, List<Integer> numGamesAsMaker) throws DomainException{
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
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a CodeBreaker
     * @param dificultat nivell de dificultat de la partida acabada
     * @param intents intents realitzats a la partida
     * @param guanyada si l'usuari ha guanyat la partida
     * @param temps duració de la partida
     * @author Kamil Przybyszewski
     */
    void acabarPartidaBreaker(Integer dificultat, Integer intents, Boolean guanyada, Long temps) throws DomainException {
        if (!NivellDificultat.isValid(dificultat)) throw new InvalidEnumValueException("NivellDificultat", dificultat.toString());
        if (intents < 0) throw new InvalidStatIntentsException(intents.toString());
        if (temps < 0L) throw new InvalidStatTempsException(temps.toString());

        dificultat--; //Els valors de NivellDificultat comencen a 1

        Integer PR = personalRecord.get(dificultat);
        if (PR == 0) personalRecord.set(dificultat, intents);
        else if (intents < PR) personalRecord.set(dificultat, intents);

        Long newTimePlayed = timePlayedFinishedGames.get(dificultat) + temps;
        timePlayedFinishedGames.set(dificultat,newTimePlayed);

        Integer numGamesAsBreaker = wonGames.get(dificultat)+lostGames.get(dificultat);
        Integer totalIntents = (int) (averageAsBreaker.get(dificultat)*numGamesAsBreaker);
        totalIntents += intents;
        numGamesAsBreaker++;
        Double newAvg = totalIntents.doubleValue()/numGamesAsBreaker;
        averageAsBreaker.set(dificultat, newAvg);

        if (guanyada) {
            Integer numwon = wonGames.get(dificultat) + 1;
            wonGames.set(dificultat, numwon);
            Integer streak = currentWinStreak.get(dificultat) + 1;
            currentWinStreak.set(dificultat, streak);
            if (streak > winStreak.get(dificultat)) winStreak.set(dificultat,streak);
        } else {
            Integer numlost = lostGames.get(dificultat) + 1;
            lostGames.set(dificultat, numlost);
            currentWinStreak.set(dificultat, 0);
        }
    }

    /**
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a codeMaker
     * @param algoritme algoritme seleccionat pel BotBreaker a la partida
     * @param intents intents del BotBreaker a la partida
     * @author Kamil Przybyszewski
     */
    void acabarPartidaMaker(Integer algoritme, Integer intents) throws DomainException {
        if (!NivellDificultat.isValid(algoritme)) throw new InvalidEnumValueException("NivellDificultat", algoritme.toString());
        if (intents < 0) throw new InvalidStatIntentsException(intents.toString());

        algoritme--; //Els valors de TipusAlgoritme no comencen en 0

        Integer totalIntents = (int) (averageAsMaker.get(algoritme)*numGamesAsMaker.get(algoritme));
        totalIntents += intents;
        Integer numGames = numGamesAsMaker.get(algoritme) + 1;
        Double newAvg = totalIntents.doubleValue()/numGames;
        averageAsMaker.set(algoritme, newAvg);
        numGamesAsMaker.set(algoritme, numGames);
    }    

    /**
     * Getter del valor de l'atribut name
     * @author Kamil Przybyszewski
     */
    String getName() {
        return name;
    }

    /**
     * Getter del valor de l'atribut username
     * @author Kamil Przybyszewski
     */
    String getUsername() {
        return username;
    }

    /**
     * Getter del valor de l'atribut personalRecord
     * @author Kamil Przybyszewski
     */
    List<Integer> getPersonalRecord(){
        return personalRecord;
    }

    /**
     * Getter del valor de l'atribut timePlayedFinishedGames
     * @author Kamil Przybyszewski
     */
    List<Long> getTimePlayed(){ return timePlayedFinishedGames;}

    /**
     * Getter del valor de l'atribut wonGames
     * @author Kamil Przybyszewski
     */
    List<Integer> getWonGames(){ return wonGames;}

    /**
     * Getter del valor de l'atribut lostGames
     * @author Kamil Przybyszewski
     */
    List<Integer> getLostGames(){
        return lostGames;
    }

    /**
     * Getter del valor de l'atribut currentWinStreak
     * @author Kamil Przybyszewski
     */
    List<Integer> getCurrentWinStreak(){ return currentWinStreak;}

    /**
     * Getter del valor de l'atribut winStreak
     * @author Kamil Przybyszewski
     */
    List<Integer> getWinStreak(){ return winStreak;}

    /**
     * Getter del valor de l'atribut averageAsBreaker
     * @author Kamil Przybyszewski
     */
    List<Double> getAvgAsBreaker(){ return averageAsBreaker;}

    /**
     * Getter del valor de l'atribut averageAsMaker
     * @author Kamil Przybyszewski
     */
    List<Double> getAvgAsMaker(){ return averageAsMaker;}

    /**
     * Getter del valor de l'atribut numGamesAsMaker
     * @author Kamil Przybyszewski
     */
    List<Integer> getNumGamesAsMaker(){ return numGamesAsMaker;}

    /**
     * Mètode determinar si els paràmetres d'usuari són adequats (bon format)
     * @param username contrasenya a comprovar
     * @param name nom a comprovar
     * @param password contrasenya a comprovar
     * @author Albert Canales
     */
    static Boolean isValidUser(String username, String name, String password) {
        return !(username.isBlank() || name.isBlank() || password.isBlank());
    }

    /**
     * Mètode per obtenir el hash en SHA256 (encoded en base64) d'una contrasenya
     * @param password contrasenya per la qual se'n vol obtenir el hash
     * @author Albert Canales
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
