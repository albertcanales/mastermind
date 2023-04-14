package domain;

import org.junit.Test;
import org.junit.Before;

import domain.exceptions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTests {
    private static List<Integer> personalRecordTest, personalRecordExpected;
    private static List<Long> timePlayedTest, timePlayedExpected;
    private static List<Integer> wonGamesTest, wonGamesExpected;
    private static List<Integer> lostGamesTest, lostGamesExpected;
    private static List<Integer> currentWinStreakTest, currentWinStreakExpected;
    private static List<Integer> winStreakTest, winStreakExpected;
    private static List<Double> avgAsBreakerTest, avgAsBreakerExpected;
    private static List<Double> avgAsMakerTest, avgAsMakerExpected;
    private static List<Integer> numGamesAsMakerTest, numGamesAsMakerExpected;

    @Before
    public void setStats(){
        //Fem deep copy per evitar la dependencia entre el paràmetre que passem i el valor esperat amb el que comparem
        personalRecordTest = new ArrayList<>(List.of(2,4,4));
        personalRecordExpected = new ArrayList<>(personalRecordTest);
        timePlayedTest = new ArrayList<>(List.of(0L,1334L,1000000L));
        timePlayedExpected = new ArrayList<>(timePlayedTest);
        wonGamesTest = new ArrayList<>(List.of(1,500,3));
        wonGamesExpected = new ArrayList<>(wonGamesTest);
        lostGamesTest = new ArrayList<>(List.of(0,500,1));
        lostGamesExpected = new ArrayList<>(lostGamesTest);
        currentWinStreakTest = new ArrayList<>(List.of(1,20,0));
        currentWinStreakExpected = new ArrayList<>(currentWinStreakTest);
        winStreakTest = new ArrayList<>(List.of(1, 23, 1));
        winStreakExpected = new ArrayList<>(winStreakTest);
        avgAsBreakerTest = new ArrayList<>(List.of(2d,3.996d,29d));
        avgAsBreakerExpected = new ArrayList<>(avgAsBreakerTest);
        avgAsMakerTest = new ArrayList<>(List.of(23.1234d,15d));
        avgAsMakerExpected = new ArrayList<>(avgAsMakerTest);
        numGamesAsMakerTest = new ArrayList<>(List.of(10,2));
        numGamesAsMakerExpected = new ArrayList<>(numGamesAsMakerTest);
    }

    @Test
    public void createNewUser(){

        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username);

        List<Integer> tot_zero = new ArrayList<>(List.of(0,0,0));
        List<Long> tot_zero_L = new ArrayList<>(List.of(0L,0L,0L));
        List<Double> tot_zero_d = new ArrayList<>(List.of(0d,0d,0d));
        List<Double> small_tot_zero_d = new ArrayList<>(List.of(0d,0d));
        List<Integer> small_tot_zero = new ArrayList<>(List.of(0,0));

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        assertEquals(tot_zero, user.getPersonalRecord());
        assertEquals(tot_zero_L, user.getTimePlayed());
        assertEquals(tot_zero, user.getWonGames());
        assertEquals(tot_zero, user.getLostGames());
        assertEquals(tot_zero, user.getCurrentWinStreak());
        assertEquals(tot_zero, user.getWinStreak());
        assertEquals(tot_zero_d, user.getAvgAsBreaker());
        assertEquals(small_tot_zero_d, user.getAvgAsMaker());
        assertEquals(small_tot_zero, user.getNumGamesAsMaker());
    }

    @Test
    public void createExistingUser() throws DomainException {

        String name = "Przybyszewski"; String username = "AbCd00";
        User user = new User(name,username,personalRecordTest,timePlayedTest,wonGamesTest,lostGamesTest,currentWinStreakTest,winStreakTest,avgAsBreakerTest,avgAsMakerTest,numGamesAsMakerTest);

        assertEquals("Przybyszewski", user.getName());
        assertEquals("AbCd00", user.getUsername());
        assertEquals(personalRecordExpected, user.getPersonalRecord());
        assertEquals(timePlayedExpected, user.getTimePlayed());
        assertEquals(wonGamesExpected, user.getWonGames());
        assertEquals(lostGamesExpected, user.getLostGames());
        assertEquals(currentWinStreakExpected,user.getCurrentWinStreak());
        assertEquals(winStreakExpected, user.getWinStreak());
        assertEquals(avgAsBreakerExpected, user.getAvgAsBreaker());
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void createExistingUserInvalidStat() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        wonGamesTest = new ArrayList<>(List.of(1,500,3,0));
        assertThrows(InvalidStatSizeException.class, () -> {
            new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        });
    }

    @Test
    public void updateForFinishedBreakerGameOnNewUser() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name,username);

        user.acabarPartidaBreaker(1,5,true,5L);

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        personalRecordExpected = new ArrayList<>(List.of(5,0,0));
        assertEquals(personalRecordExpected, user.getPersonalRecord());
        timePlayedExpected = new ArrayList<>(List.of(5L,0L,0L));
        assertEquals(timePlayedExpected, user.getTimePlayed());
        wonGamesExpected = new ArrayList<>(List.of(1,0,0));
        assertEquals(wonGamesExpected, user.getWonGames());
        lostGamesExpected = new ArrayList<>(List.of(0,0,0));
        assertEquals(lostGamesExpected, user.getLostGames());
        currentWinStreakExpected = new ArrayList<>(List.of(1,0,0));
        assertEquals(currentWinStreakExpected, user.getCurrentWinStreak());
        winStreakExpected = new ArrayList<>(List.of(1,0,0));
        assertEquals(winStreakExpected, user.getWinStreak());
        avgAsBreakerExpected = new ArrayList<>(List.of(5d,0d,0d));
        assertEquals(avgAsBreakerExpected, user.getAvgAsBreaker());
        avgAsMakerExpected = new ArrayList<>(List.of(0d,0d));
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        numGamesAsMakerExpected = new ArrayList<>(List.of(0,0));
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void updateForFinishedWonBreakerGame() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name,username,personalRecordTest,timePlayedTest,wonGamesTest,lostGamesTest,currentWinStreakTest,winStreakTest,avgAsBreakerTest,avgAsMakerTest,numGamesAsMakerTest);

        user.acabarPartidaBreaker(1,1,true,5L);

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        personalRecordExpected.set(0,1);
        assertEquals(personalRecordExpected, user.getPersonalRecord());
        timePlayedExpected.set(0,5L);
        assertEquals(timePlayedExpected, user.getTimePlayed());
        wonGamesExpected.set(0,2);
        assertEquals(wonGamesExpected, user.getWonGames());
        assertEquals(lostGamesExpected, user.getLostGames());
        currentWinStreakExpected.set(0,2);
        assertEquals(currentWinStreakExpected, user.getCurrentWinStreak());
        winStreakExpected.set(0,2);
        assertEquals(winStreakExpected, user.getWinStreak());
        avgAsBreakerExpected.set(0,1.5d);
        assertEquals(avgAsBreakerExpected, user.getAvgAsBreaker());
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void updateForFinishedLostBreakerGame() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name,username,personalRecordTest,timePlayedTest,wonGamesTest,lostGamesTest,currentWinStreakTest,winStreakTest,avgAsBreakerTest,avgAsMakerTest,numGamesAsMakerTest);

        user.acabarPartidaBreaker(2,8,false,16L);

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        assertEquals(personalRecordExpected, user.getPersonalRecord());
        timePlayedExpected.set(1,1350L);
        assertEquals(timePlayedExpected, user.getTimePlayed());
        assertEquals(wonGamesExpected, user.getWonGames());
        lostGamesExpected.set(1,501);
        assertEquals(lostGamesExpected, user.getLostGames());
        currentWinStreakExpected.set(1,0);
        assertEquals(currentWinStreakExpected, user.getCurrentWinStreak());
        winStreakExpected.set(1,23);
        assertEquals(winStreakExpected, user.getWinStreak());
        avgAsBreakerExpected.set(1,4d);
        assertEquals(avgAsBreakerExpected, user.getAvgAsBreaker());
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void updateForFinishedBreakerGameInvalidDificultat() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        assertThrows(InvalidEnumValueException.class, () -> {
            user.acabarPartidaBreaker(-2,5,true,5L);
        });
    }

    @Test
    public void updateForFinishedBreakerGameInvalidIntentsStat() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        assertThrows(InvalidStatIntentsException.class, () -> {
            user.acabarPartidaBreaker(2,-100,true,5L);
        });
    }

    @Test
    public void updateForFinishedBreakerGameInvalidTempsStat() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        assertThrows(InvalidStatTempsException.class, () -> {
            user.acabarPartidaBreaker(2,5,true,-100L);
        });
    }

    @Test
    public void updateForFinishedMakerGameOnNewUser() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name,username);

        user.acabarPartidaMaker(1,8);

        List<Integer> tot_zero = new ArrayList<>(List.of(0,0,0));
        List<Long> tot_zero_L = new ArrayList<>(List.of(0L,0L,0L));
        List<Double> tot_zero_d = new ArrayList<>(List.of(0d,0d,0d));

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        assertEquals(tot_zero, user.getPersonalRecord());
        assertEquals(tot_zero_L, user.getTimePlayed());
        assertEquals(tot_zero, user.getWonGames());
        assertEquals(tot_zero, user.getLostGames());
        assertEquals(tot_zero, user.getCurrentWinStreak());
        assertEquals(tot_zero, user.getWinStreak());
        assertEquals(tot_zero_d, user.getAvgAsBreaker());
        avgAsMakerExpected = new ArrayList<>(List.of(8d,0d));
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        numGamesAsMakerExpected = new ArrayList<>(List.of(1,0));
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void updateForFinishedMakerGame() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name,username,personalRecordTest,timePlayedTest,wonGamesTest,lostGamesTest,currentWinStreakTest,winStreakTest,avgAsBreakerTest,avgAsMakerTest,numGamesAsMakerTest);

        user.acabarPartidaMaker(2,15);

        assertEquals("Pepe", user.getName());
        assertEquals("pepe2316", user.getUsername());
        assertEquals(personalRecordExpected, user.getPersonalRecord());
        assertEquals(timePlayedExpected, user.getTimePlayed());
        assertEquals(wonGamesExpected, user.getWonGames());
        assertEquals(lostGamesExpected, user.getLostGames());
        assertEquals(currentWinStreakExpected, user.getCurrentWinStreak());
        assertEquals(winStreakExpected, user.getWinStreak());
        assertEquals(avgAsBreakerExpected, user.getAvgAsBreaker());
        avgAsMakerExpected.set(1,15d);
        assertEquals(avgAsMakerExpected, user.getAvgAsMaker());
        numGamesAsMakerExpected.set(1,3);
        assertEquals(numGamesAsMakerExpected, user.getNumGamesAsMaker());
    }

    @Test
    public void updateForFinishedMakerGameInvalidAlgoritme() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        assertThrows(InvalidEnumValueException.class, () -> {
            user.acabarPartidaMaker(500,5);
        });
    }

    @Test
    public void updateForFinishedMakerGameInvalidIntentsStat() throws DomainException {
        String name = "Pepe"; String username = "pepe2316";
        User user = new User(name, username, personalRecordTest, timePlayedTest, wonGamesTest, lostGamesTest, currentWinStreakTest, winStreakTest, avgAsBreakerTest, avgAsMakerTest, numGamesAsMakerTest);
        assertThrows(InvalidStatIntentsException.class, () -> {
            user.acabarPartidaMaker(1,-666);
        });
    }

    @Test
    public void validUser() {
        String username = "ringdestroyer_4000";
        String name = "Frodo Bolsón";
        String password = "sauronbad";

        assertEquals(true, User.isValidUser(username, name, password));
    }

    @Test
    public void invalidUsername() {
        String username = "";
        String name = "Albert Canales";
        String password = "contrasenya";

        assertEquals(false, User.isValidUser(username, name, password));
    }

    @Test
    public void invalidName() {
        String username = "pau";
        String name = "";
        String password = "socsegura";

        assertEquals(false, User.isValidUser(username, name, password));
    }

    @Test
    public void invalidPassword() {
        String username = "mar";
        String name = "Mar González Català";
        String password = "";

        assertEquals(false, User.isValidUser(username, name, password));
    }

    @Test
    public void validPasswordHash() {
        String password = "patata";
        String hash = "da248eeaffa573da8c323c3eb56aaf32ec6ce244e401a24c55f30c907d0bbfb5";

        assertEquals(hash, User.getPasswordHash(password));
    }

    @Test
    public void emptyPasswordHash() {
        String hash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

        assertEquals(hash, User.getPasswordHash(""));
    }

    public static void main(String[] args) {
        TestRunner.runTestClass(UserTests.class);
    }
}
