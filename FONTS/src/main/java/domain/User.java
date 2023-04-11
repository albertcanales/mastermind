package domain;

import java.time.Duration;
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
    private List<Duration> timePlayedFinishedGames;
    private List<Integer> wonGames;
    private List<Integer> winStreak;
    private List<Double> averageAsBreaker;
    private List<Integer> numGamesAsBreaker;

    private List<Double> averageAsMaker;
    private List<Integer> numGamesAsMaker;

    /**
     * Mètode per assignar el valor inicial a les estadístiques com a CodeBreaker de l'usuari
     * @author Kamil Przybyszewski
     */
    private void initializeBreakerStats(){
        Integer numDificultats = NivellDificultat.numDificultats();
        List<Integer> tot_zero = new ArrayList<>(Collections.nCopies(numDificultats,0));

        personalRecord = new ArrayList<>(tot_zero);
        timePlayedFinishedGames = new ArrayList<>(Collections.nCopies(numDificultats,Duration.ZERO));
        wonGames = new ArrayList<>(tot_zero);
        winStreak = new ArrayList<>(tot_zero);
        averageAsBreaker = new ArrayList<>(Collections.nCopies(numDificultats,0.0));
        numGamesAsBreaker = new ArrayList<>(tot_zero);
    }

    /**
     * Mètode per assignar el valor inicial a les estadístiques com a CodeMaker de l'usuari
     * @author Kamil Przybyszewski
     */
    private void initializeMakerStats(){
        Integer numAlgoritmes = TipusAlgorisme.numAlgoritmes();

        averageAsMaker = new ArrayList<>(Collections.nCopies(numAlgoritmes,0.0));
        numGamesAsMaker = new ArrayList<>(Collections.nCopies(numAlgoritmes,0));
    }

    /**
     * Constructor d'un usuari nou, amb les estadístiques a 0
     * @param name nom de l'usuari
     * @param username username de l'usuari
     * @author Kamil Przybyszewski
     */
    public User(String name, String username) {
        this.name = name;
        this.username = username;

        initializeBreakerStats();
        initializeMakerStats();
    }

    /**
     * Constructor d'un usuari ja existent
     * @author Kamil Przybyszewski
     */
    //TODO Excepcions si tamany no es numDificultats o numAlgoritmes
    public User(String name, String username, List<Integer> personalRecord, List<Duration> timePlayed, List<Integer> wonGames, List<Integer> winStreak, List<Double> avgAsBreaker, List<Integer> numGamesAsBreaker, List<Double> avgAsMaker, List<Integer> numGamesAsMaker) {
        this.name = name;
        this.username = username;

        this.personalRecord = personalRecord;
        this.timePlayedFinishedGames = timePlayed;
        this.wonGames = wonGames;
        this.winStreak = winStreak;
        this.averageAsBreaker = avgAsBreaker; this.numGamesAsBreaker = numGamesAsBreaker;

        this.averageAsMaker = avgAsMaker; this.numGamesAsMaker = numGamesAsMaker;
    }

    /**
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a CodeBreaker
     * @param dificultat nivell de dificultat de la partida acabada
     * @param intents intents realitzats a la partida
     * @param guanyada si l'usuari ha guanyat la partida
     * @param temps duració de la partida
     * @author Kamil Przybyszewski
     */
    public void acabarPartidaBreaker(Integer dificultat, Integer intents, Boolean guanyada, Duration temps){
        dificultat--; //Els valors de NivellDificultat no comencen a 0

        if (intents < personalRecord.get(dificultat)) personalRecord.set(dificultat, intents);

        Duration newTimePlayed = timePlayedFinishedGames.get(dificultat).plus(temps);
        timePlayedFinishedGames.set(dificultat,newTimePlayed);

        if (guanyada) {
            Integer numwon = wonGames.get(dificultat) + 1;
            wonGames.set(dificultat, numwon);
            Integer streak = winStreak.get(dificultat) + 1;
            winStreak.set(dificultat, streak);
        }
        else winStreak.set(dificultat,0);

        Integer totalIntents = (int) (averageAsBreaker.get(dificultat)*numGamesAsBreaker.get(dificultat));
        totalIntents += intents;
        Integer numGames = numGamesAsBreaker.get(dificultat) + 1;
        Double newAvg = totalIntents.doubleValue()/numGames;
        averageAsBreaker.set(dificultat, newAvg);
        numGamesAsBreaker.set(dificultat, numGames);
    }

    /**
     * Mètode per actualitzar les estadístiques de l'usuari quan acaba una partida com a codeMaker
     * @param algoritme algoritme seleccionat pel BotBreaker a la partida
     * @param intents intents del BotBreaker a la partida
     * @author Kamil Przybyszewski
     */
    public void acabarPartidaMaker(Integer algoritme, Integer intents){
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
    public String getName() {
        return name;
    }

    /**
     * Getter del valor de l'atribut username
     * @author Kamil Przybyszewski
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter del valor de l'atribut personalRecord
     * @author Kamil Przybyszewski
     */
    public List<Integer> getPersonalRecord(){
        return personalRecord;
    }

    /**
     * Getter del valor de l'atribut timePlayedFinishedGames
     * @author Kamil Przybyszewski
     */
    public List<Duration> getTimePlayed(){ return timePlayedFinishedGames;}

    /**
     * Getter del valor de l'atribut wonGames
     * @author Kamil Przybyszewski
     */
    public List<Integer> getWonGames(){ return wonGames;}

    /**
     * Getter del valor de l'atribut winStreak
     * @author Kamil Przybyszewski
     */
    public List<Integer> getWinStreak(){ return winStreak;}

    /**
     * Getter del valor de l'atribut averageAsBreaker
     * @author Kamil Przybyszewski
     */
    public List<Double> getAvgAsBreaker(){ return averageAsBreaker;}

    /**
     * Getter del valor de l'atribut numGamesAsBreaker
     * @author Kamil Przybyszewski
     */
    public List<Integer> getNumGamesAsBreaker(){ return numGamesAsBreaker;}

    /**
     * Getter del valor de l'atribut averageAsMaker
     * @author Kamil Przybyszewski
     */
    public List<Double> getAvgAsMaker(){ return averageAsMaker;}

    /**
     * Getter del valor de l'atribut numGamesAsMaker
     * @author Kamil Przybyszewski
     */
    public List<Integer> getNumGamesAsMaker(){ return numGamesAsMaker;}

}
