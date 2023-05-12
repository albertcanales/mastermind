package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.util.List;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorUsuaris {

    private static final String relativeUsersPath = "users/users.csv";

    private final GestorCSVFile csvFile;

    public static void main(String[] args) throws PersistanceException {
        GestorUsuaris gestor = new GestorUsuaris("./db/");

        gestor.registerUser("arnau", "Arnau", "securepass");
        gestor.registerUser("sussy", "Amongus", "securepass2");
        gestor.registerUser("morethansus", "AmogOS", "securepass3");

        System.out.println(String.format("Name for \"arnau:\" %s", gestor.getUserName("arnau")));
        System.out.println(String.format("Name for \"sussy:\" %s", gestor.getUserName("sussy")));
        System.out.println(String.format("Name for \"morethansus:\" %s", gestor.getUserName("morethansus")));

        System.out.println(String.format("Password for \"arnau:\" %s", gestor.getPasswordHash("arnau")));
        System.out.println(String.format("Password for \"sussy:\" %s", gestor.getPasswordHash("sussy")));
        System.out.println(String.format("Password for \"morethansus:\" %s", gestor.getPasswordHash("morethansus")));

        gestor.esborrarUsuari("sussy");
        System.out.println(String.format("Exists user \"sussy:\" %s", gestor.existsUser("sussy")));


    }
    GestorUsuaris(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativeUsersPath, Header.getHeader(), Header.USERNAME.start);

    }

    public Boolean existsUser(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        if (!csvFile.existsLinebyKey(username))
        {
            String[] line = new String[Header.getLength()];
            line[Header.USERNAME.start] = username;
            line[Header.NAME.start] = name;
            line[Header.PASSWORD.start] = password;

            csvFile.addLine(line);
        }
        else {
            throw new LineAlreadyExistsException(username, relativeUsersPath);
        }

    }

    public String getPasswordHash(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[Header.PASSWORD.start];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public String getUserName(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[Header.NAME.start];
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

            String[] line = new String[Header.getLength()];
            line[Header.USERNAME.start] = username;
            line[Header.NAME.start] = csvFile.getLinebyKey(username)[Header.NAME.start];
            line[Header.PASSWORD.start] = csvFile.getLinebyKey(username)[Header.PASSWORD.start];
            csvFile.setListIntinString(pr, line, Header.PERSRECORD.start, Header.PERSRECORD.end);
            csvFile.setListLonginString(time, line, Header.TIMEPLAYED.start, Header.TIMEPLAYED.end);
            csvFile.setListIntinString(won, line, Header.WONGAMES.start, Header.WONGAMES.end);
            csvFile.setListIntinString(lost, line, Header.LOSTGAMES.start, Header.LOSTGAMES.end);
            csvFile.setListIntinString(currentWs, line, Header.CURRENTWS.start, Header.CURRENTWS.end);
            csvFile.setListIntinString(ws, line, Header.WINSTREAK.start, Header.WINSTREAK.end);
            csvFile.setListDoubleinString(avgMaker, line, Header.AVGASMAKER.start, Header.AVGASMAKER.end);
            csvFile.setListDoubleinString(avgBreaker, line, Header.AVGASBREAKER.start, Header.AVGASBREAKER.end);
            csvFile.setListIntinString(gamesMaker, line, Header.NUMGAMESMAKER.start, Header.NUMGAMESMAKER.end);

            csvFile.setLinebyKey(username, line);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getPersonalRecord(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.PERSRECORD.start, Header.PERSRECORD.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Long> getTimePlayed(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListLonginString(csvFile.getLinebyKey(username), Header.TIMEPLAYED.start, Header.TIMEPLAYED.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getWonGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.WONGAMES.start, Header.WONGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getLostGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.LOSTGAMES.start, Header.LOSTGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }
    public List<Integer> getCurrentWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.CURRENTWS.start, Header.CURRENTWS.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.WINSTREAK.start, Header.WINSTREAK.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Double> getAvgAsMaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), Header.AVGASMAKER.start, Header.AVGASMAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Double> getAvgAsBreaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), Header.AVGASBREAKER.start, Header.AVGASBREAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public List<Integer> getNumGamesAsMaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), Header.NUMGAMESMAKER.start, Header.NUMGAMESMAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum Header {
        USERNAME(0, 0),
        NAME(1, 1),
        PASSWORD(2, 2),
        PERSRECORD(3, 5),
        TIMEPLAYED(6, 8),
        WONGAMES(9, 11),
        LOSTGAMES(12, 14),
        CURRENTWS(15, 17),
        WINSTREAK(18, 20),
        AVGASMAKER(21, 22),
        AVGASBREAKER(23, 25),
        NUMGAMESMAKER(26, 27);

        private final int start;
        private final int end;

        /**
         * Constructor del Header d'Usuaris
         *
         * @param start és el valor que representa la columna
         */
        Header(int start, int end) {
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
