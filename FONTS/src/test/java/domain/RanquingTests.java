package domain;

import exceptions.domain.DomainException;
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
    public void createNewRanquing() {

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
    public void createExistingRanquing() {

        Ranquing ranquing = new Ranquing(ranquingTest);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        assertNotSame(ranquingExpected, ranquingList);
        assertEquals(ranquingExpected, ranquingList);
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
        ranquing.acabarPartidaBreaker("Doraemon", 1, 6, 100L);
        List<List<List<String>>> ranquingList = ranquing.getRanquings();

        ranquingExpected = new ArrayList<>();
        for (int i = 0; i < NUM_RANQUINGS; ++i){
            ranquingExpected.add(new ArrayList<>());
        }
        ranquingExpected.get(0).add(List.of("albert", "2", "100"));
        ranquingExpected.get(0).add(List.of("mar", "4", "200"));
        ranquingExpected.get(0).add(List.of("Doraemon","6","100"));

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
    public void esborraUserFromRanquing() {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.esborrarUserFromRanquing("mar");
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
    public void esborraTopUserFromRanquing() {

        Ranquing ranquing = new Ranquing(ranquingTest);
        ranquing.esborrarUserFromRanquing("arnau");
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
