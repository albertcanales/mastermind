package domain;

import java.util.List;

/**
 * Classe que representa a un usuari del Joc i les seves estadístiques
 * @author Kamil Przybyszewski
 */
public class User {

    String name;
    String username;

    List<Integer> PersonalRecord;
    List<Integer> TimePlayedFinishedGames;
    List<Integer> WonGames;
    List<Integer> LostGames;
    List<Integer> WinStreak;
    List<Double> AverageAsBreaker;
    List<Double> AverageAsMaker;
    List<Integer> NumGamesAsMaker;

    /**
     * Constructor d'un usuari
     * @param name nom de l'usuari
     * @param username username de l'usuari
     * @author Kamil Przybyszewski
     */
    public User(String name, String username) {
        this.name = name;
        this.username = username;
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
     * Getter del valor de l'atribut PersonalRecord
     * @author Kamil Przybyszewski
     */
    public List<Integer> getPersonalRecord(){
        return PersonalRecord;
    }

    /**
     * Setter del valor de l'atribut PersonalRecord
     * @param PersonalRecord s'assigna a PersonalRecord
     * @author Kamil Przybyszewski
     */
    void setPersonalRecord(List<Integer> PersonalRecord){
        this.PersonalRecord = PersonalRecord;
    }

    /**
     * Getter del valor de l'atribut TimePlayedFinishedGames
     * @author Kamil Przybyszewski
     */
    public List<Integer> getTimePlayed(){ return TimePlayedFinishedGames;}

    /**
     * Setter del valor de l'atribut TimePlayedFinishedGames
     * @param TimePlayedFinishedGames s'assigna a TimePlayedFinishedGames
     * @author Kamil Przybyszewski
     */
    void setTimePlayed(List<Integer> TimePlayedFinishedGames){ this.TimePlayedFinishedGames = TimePlayedFinishedGames; }

    /**
     * Getter del valor de l'atribut WonGames
     * @author Kamil Przybyszewski
     */
    public List<Integer> getWonGames(){ return WonGames;}

    /**
     * Setter del valor de l'atribut WonGames
     * @param WonGames s'assigna a WonGames
     * @author Kamil Przybyszewski
     */
    void setWonGames(List<Integer> WonGames){ this.WonGames = WonGames; }

    /**
     * Getter del valor de l'atribut LostGames
     * @author Kamil Przybyszewski
     */
    public List<Integer> getLostGames(){ return LostGames;}

    /**
     * Setter del valor de l'atribut LostGames
     * @param LostGames s'assigna a LostGames
     * @author Kamil Przybyszewski
     */
    void setLostGames(List<Integer> LostGames){ this.LostGames = LostGames; }

    /**
     * Getter del valor de l'atribut AverageAsMaker
     * @author Kamil Przybyszewski
     */
    public List<Double> getAvgAsMaker(){ return AverageAsMaker;}

    /**
     * Setter del valor de l'atribut AverageAsMaker
     * @param AvgAsMaker s'assigna a AverageAsMaker
     * @author Kamil Przybyszewski
     */
    void setAvgAsMaker(List<Double> AvgAsMaker){ this.AverageAsMaker = AvgAsMaker; }

    /**
     * Getter del valor de l'atribut NumGamesAsMaker
     * @author Kamil Przybyszewski
     */
    public List<Integer> getNumGamesAsMaker(){ return NumGamesAsMaker;}

    /**
     * Setter del valor de l'atribut NumGamesAsMaker
     * @param NumGamesAsMaker s'assigna a NumGamesAsMaker
     * @author Kamil Przybyszewski
     */
    void setNumGamesAsMaker(List<Integer> NumGamesAsMaker){ this.NumGamesAsMaker = NumGamesAsMaker; }

}
