package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.util.Arrays;
import java.util.List;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorUsuaris {

    private static final String relativeUsersPath = "users/users.csv";

    private final GestorCSVFile csvFile;

    GestorUsuaris(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativeUsersPath, HeaderUsuaris.getHeader(), HeaderUsuaris.USERNAME.start);

    }

    public Boolean existsUser(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        if (!csvFile.existsLinebyKey(username))
        {
            String[] line = new String[HeaderUsuaris.getLength()];
            Arrays.fill(line, "");
            line[HeaderUsuaris.USERNAME.start] = username;
            line[HeaderUsuaris.NAME.start] = name;
            line[HeaderUsuaris.PASSWORD.start] = password;

            csvFile.addLine(line);
        }
        else {
            throw new LineAlreadyExistsException(username, relativeUsersPath);
        }

    }

    public String getPasswordHash(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[HeaderUsuaris.PASSWORD.start];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public String getUserName(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[HeaderUsuaris.NAME.start];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public void esborrarUsuari(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public void setStats(String username, List<Integer> pr, List<Long> time, List<Integer> won, List<Integer> lost,
                         List<Integer> currentWs, List<Integer> ws, List<Double> avgMaker, List<Double> avgBreaker,
                         List<Integer> gamesMaker) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {

            String[] line = new String[HeaderUsuaris.getLength()];
            line[HeaderUsuaris.USERNAME.start] = username;
            line[HeaderUsuaris.NAME.start] = csvFile.getLinebyKey(username)[HeaderUsuaris.NAME.start];
            line[HeaderUsuaris.PASSWORD.start] = csvFile.getLinebyKey(username)[HeaderUsuaris.PASSWORD.start];
            csvFile.setListinString(pr, line, HeaderUsuaris.PERSRECORD.start, HeaderUsuaris.PERSRECORD.end);
            csvFile.setListinString(time, line, HeaderUsuaris.TIMEPLAYED.start, HeaderUsuaris.TIMEPLAYED.end);
            csvFile.setListinString(won, line, HeaderUsuaris.WONGAMES.start, HeaderUsuaris.WONGAMES.end);
            csvFile.setListinString(lost, line, HeaderUsuaris.LOSTGAMES.start, HeaderUsuaris.LOSTGAMES.end);
            csvFile.setListinString(currentWs, line, HeaderUsuaris.CURRENTWS.start, HeaderUsuaris.CURRENTWS.end);
            csvFile.setListinString(ws, line, HeaderUsuaris.WINSTREAK.start, HeaderUsuaris.WINSTREAK.end);
            csvFile.setListinString(avgMaker, line, HeaderUsuaris.AVGASMAKER.start, HeaderUsuaris.AVGASMAKER.end);
            csvFile.setListinString(avgBreaker, line, HeaderUsuaris.AVGASBREAKER.start, HeaderUsuaris.AVGASBREAKER.end);
            csvFile.setListinString(gamesMaker, line, HeaderUsuaris.NUMGAMESMAKER.start, HeaderUsuaris.NUMGAMESMAKER.end);

            csvFile.setLinebyKey(username, line);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getPersonalRecord(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.PERSRECORD.start, HeaderUsuaris.PERSRECORD.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Long> getTimePlayed(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListLonginString(csvFile.getLinebyKey(username), HeaderUsuaris.TIMEPLAYED.start, HeaderUsuaris.TIMEPLAYED.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getWonGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.WONGAMES.start, HeaderUsuaris.WONGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getLostGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.LOSTGAMES.start, HeaderUsuaris.LOSTGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }
    public List<Integer> getCurrentWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.CURRENTWS.start, HeaderUsuaris.CURRENTWS.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.WINSTREAK.start, HeaderUsuaris.WINSTREAK.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Double> getAvgAsMaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), HeaderUsuaris.AVGASMAKER.start, HeaderUsuaris.AVGASMAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Double> getAvgAsBreaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), HeaderUsuaris.AVGASBREAKER.start, HeaderUsuaris.AVGASBREAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getNumGamesAsMaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.NUMGAMESMAKER.start, HeaderUsuaris.NUMGAMESMAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum HeaderUsuaris {
        USERNAME(0, 0),
        NAME(1, 1),
        PASSWORD(2, 2),
        PERSRECORD(3, 6),
        TIMEPLAYED(7, 10),
        WONGAMES(11, 14),
        LOSTGAMES(15, 18),
        CURRENTWS(19, 22),
        WINSTREAK(23, 26),
        AVGASMAKER(27, 29),
        AVGASBREAKER(30, 33),
        NUMGAMESMAKER(34, 36);

        private final int start;
        private final int end;

        /**
         * Constructor del Header d'Usuaris
         *
         * @param start és el valor que representa la columna
         */
        HeaderUsuaris(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * Genera un array d'strings amb el Header corresponent
         * @return un array d'strings
         */
        private static String[] getHeader() {
            String[] header = new String[getLength()];

            for (int i = 0; i < values().length; ++i) {
                if (values()[i].start == values()[i].end) {

                    header[i] = values()[i].name();

                } else {
                    int firstCompondValue = i;
                    while (firstCompondValue < values().length) {

                        int listNumber = 0;
                        for (int startToEnd = values()[i].start; startToEnd <= values()[i].end; ++startToEnd) {
                            header[startToEnd] = values()[i].name() + " " + listNumber;
                            ++listNumber;
                        }
                        ++firstCompondValue;
                    }
                }
            }
            return header;
        }

        public static Integer getLength() {
            return values()[(values().length - 1)].end + 1;
        }
    }
}
