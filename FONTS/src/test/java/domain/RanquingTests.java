package domain;

import exceptions.domain.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class RanquingTests {

    private final Integer NUM_RANQUINGS = 3;
    private static List<List<List<String>>> ranquingTest, ranquingExpected;
    @Before
    public void setStats(){
        ranquingTest = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingTest.add(new ArrayList<>());
        }
        ranquingTest.get(0).add(List.of("albert", "2", "100"));
        ranquingTest.get(0).add(List.of("mar", "4", "200"));

        ranquingTest.get(1).add(List.of("arnau", "5", "400"));
        ranquingTest.get(1).add(List.of("kamil", "7", "300"));
        ranquingTest.get(1).add(List.of("mar", "10", "700"));

        ranquingExpected = new ArrayList<>(ranquingTest);
    }

    @Test
    public void createNewRanquing() throws DomainException{

        List<List<List<String>>> emptyRanquing = new ArrayList<>();
        for(int i = 0; i < 3; i++)
            emptyRanquing.add(new ArrayList<>());
        Ranquing ranquing = new Ranquing(emptyRanquing);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        assertEquals(NUM_RANQUINGS, (Integer) ranquingList.size());
        assertEquals(0, ranquingList.get(0).size());
        assertEquals(0, ranquingList.get(1).size());
        assertEquals(0, ranquingList.get(2).size());
    }

    @Test
    public void createExistingRanquing() throws DomainException{

        Ranquing ranquing = new Ranquing(ranquingTest);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        assertNotSame(ranquingExpected, ranquingList);
        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void createExistingRanquingWithInvalidNumRanquings() throws DomainException{
        ranquingTest.remove(2);

        assertThrows(InvalidNumRanquingsException.class, () -> new Ranquing(ranquingTest));
    }

    @Test
    public void updateForFinishedGameOnNewRanquing() throws DomainException {

        List<List<List<String>>> emptyRanquing = new ArrayList<>();
        for(int i = 0; i < 3; i++)
            emptyRanquing.add(new ArrayList<>());
        Ranquing ranquing = new Ranquing(emptyRanquing);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 6, 100L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }
        ranquingExpected.get(0).add(List.of("Doraemon","6","100"));

        assertEquals(ranquingExpected,ranquingList);
    }

    @Test
    public void updateForFinishedWorstGame() throws DomainException {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 10, 100L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }
        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));
        ranquingExpected.get(0).add(List.of("Doraemon","10","100"));

        ranquingExpected.get(1).add(List.of("arnau", "5", "400"));
        ranquingExpected.get(1).add(List.of("kamil", "7", "300"));
        ranquingExpected.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void updateForFinishedMiddleGame() throws DomainException {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 3, 300L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }

        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("Doraemon","3","300"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));

        ranquingExpected.get(1).add(List.of("arnau", "5", "400"));
        ranquingExpected.get(1).add(List.of("kamil", "7", "300"));
        ranquingExpected.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void updateForFinishedBestGame() throws DomainException {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 1, 150L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }
        ranquingExpected.get(0).add(List.of("Doraemon","1","150"));
        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));

        ranquingExpected.get(1).add(List.of("arnau", "5", "400"));
        ranquingExpected.get(1).add(List.of("kamil", "7", "300"));
        ranquingExpected.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void updateForFinishedBestTiedGame() throws DomainException {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 2, 100L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }
        ranquingExpected.get(0).add(List.of("Doraemon","2","100"));
        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));

        ranquingExpected.get(1).add(List.of("arnau", "5", "400"));
        ranquingExpected.get(1).add(List.of("kamil", "7", "300"));
        ranquingExpected.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void updateForFinishedAlmostBestGame() throws DomainException {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.acabarPartidaBreaker("Doraemon", 1, 2, 300L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }

        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("Doraemon","2","300"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));

        ranquingExpected.get(1).add(List.of("arnau", "5", "400"));
        ranquingExpected.get(1).add(List.of("kamil", "7", "300"));
        ranquingExpected.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void updateForFinishedBreakerGameInvalidDificultat() throws DomainException {
        Ranquing ranquing = new Ranquing(ranquingTest);
        assertThrows(InvalidEnumValueException.class, () -> ranquing.acabarPartidaBreaker("Doraemon",5,5,5L));
    }

    @Test
    public void updateForFinishedBreakerGameInvalidIntentsStat() throws DomainException {
        Ranquing ranquing = new Ranquing(ranquingTest);
        assertThrows(InvalidStatIntentsException.class, () -> ranquing.acabarPartidaBreaker("Doraemon",3,0,5L));
    }

    @Test
    public void updateForFinishedBreakerGameInvalidTempsStat() throws DomainException {
        Ranquing ranquing = new Ranquing(ranquingTest);
        assertThrows(InvalidStatTempsException.class, () -> ranquing.acabarPartidaBreaker("Doraemon",3,5,-1L));
    }

    @Test
    public void esborraUserFromRanquing() throws DomainException{

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.esborrarUserFromRanquings("mar");
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingTest = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingTest.add(new ArrayList<>());
        }
        ranquingTest.get(0).add(List.of("albert", "2", "100"));

        ranquingTest.get(1).add(List.of("arnau", "5", "400"));
        ranquingTest.get(1).add(List.of("kamil", "7", "300"));

        assertEquals(ranquingExpected, ranquingList);
    }
    @Test
    public void esborraTopUserFromRanquing() throws DomainException{

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.esborrarUserFromRanquings("arnau");
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingTest = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingTest.add(new ArrayList<>());
        }
        ranquingTest.get(0).add(List.of("albert", "2", "100"));
        ranquingTest.get(0).add(List.of("mar", "4", "200"));

        ranquingTest.get(1).add(List.of("kamil", "7", "300"));
        ranquingTest.get(1).add(List.of("mar", "10", "700"));

        assertEquals(ranquingExpected, ranquingList);
    }
}
