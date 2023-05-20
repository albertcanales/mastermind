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
class GestorUsuaris {

    /**
     * String que representa l'arxiu que manipularà el Gestor
     */
    private static final String relativeUsersPath = "users/users.csv";

    /**
     * Instància de GestorCSVFile que representa el fitxer
     */
    private final GestorCSVFile csvFile;

    /**
     * Constructor del Gestor d'Usuaris
     * @param basePath directori base a partir d'on es crearan tots els arxius
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV ja existeix i no té un format legal
     */
    GestorUsuaris(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativeUsersPath, HeaderUsuaris.getHeader(), HeaderUsuaris.USERNAME.start);

    }

    /**
     * Comprova si existeix una entrada al CSV de la clau username
     * @param username nom d'usuari (clau)
     * @return cert o fals
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    Boolean existsUser(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    /**
     * Estableix els paràmetres passats a les columnes corresponents en una nova línia del CSV
     * @param username nom d'usuari (clau)
     * @param name nom complet
     * @param password contrasenya
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal o si la línia ja existeix
     */
    void registerUser(String username, String name, String password) throws PersistanceException {
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

    /**
     * Retorna a la columna PASSWORD del CSV
     * @param username nom d'usuari (clau)
     * @return l'String del PASSWORD
     * @throws PersistanceException
     */
    String getPasswordHash(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[HeaderUsuaris.PASSWORD.start];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna a la columna USERNAME del CSV
     * @param username nom d'usuari (clau)
     * @return l'String del USERNAME
     * @throws PersistanceException
     */
    String getUserName(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[HeaderUsuaris.NAME.start];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Esborra una línia del CSV donada la clau username
     * @param username nom d'usuari (clau)
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void esborrarUsuari(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Estableix els paràmetres passats a les columnes corresponents en una línia existent del CSV
     * @param username nom d'usuari (clau)
     * @param pr llista de PERSRECORD
     * @param time llista de TIMEPLAYED
     * @param won llista de WONGAMES
     * @param lost llista de LOSTGAMES
     * @param currentWs llista de CURRENTWS
     * @param ws llista de WINSTREAK
     * @param avgMaker llista de AVGASMAKER
     * @param avgBreaker llista de AVGASBREAKER
     * @param gamesMaker llista de NUMGAMESMAKER
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void setStats(String username, List<Integer> pr, List<Long> time, List<Integer> won, List<Integer> lost,
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

    /**
     * Retorna la columna PERSRECORD del CSV
     * @param username nom d'usuari (clau)
     * @return llista de PERSRECORD
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getPersonalRecord(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.PERSRECORD.start, HeaderUsuaris.PERSRECORD.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna TIMEPLAYED del CSV
     * @param username nom d'usuari (clau)
     * @return llista de TIMEPLAYED
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Long> getTimePlayed(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListLonginString(csvFile.getLinebyKey(username), HeaderUsuaris.TIMEPLAYED.start, HeaderUsuaris.TIMEPLAYED.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna WONGAMES del CSV
     * @param username nom d'usuari (clau)
     * @return llista de WONGAMES
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getWonGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.WONGAMES.start, HeaderUsuaris.WONGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna LOSTGAMES del CSV
     * @param username nom d'usuari (clau)
     * @return llista de LOSTGAMES
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getLostGames(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.LOSTGAMES.start, HeaderUsuaris.LOSTGAMES.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna CURRENTWS del CSV
     * @param username nom d'usuari (clau)
     * @return llista de CURRENTWS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getCurrentWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.CURRENTWS.start, HeaderUsuaris.CURRENTWS.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna WINSTREAK del CSV
     * @param username nom d'usuari (clau)
     * @return llista de WINSTREAK
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getWinstreak(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListIntinString(csvFile.getLinebyKey(username), HeaderUsuaris.WINSTREAK.start, HeaderUsuaris.WINSTREAK.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna AVGASMAKER del CSV
     * @param username nom d'usuari (clau)
     * @return llista de AVGASMAKER
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Double> getAvgAsMaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), HeaderUsuaris.AVGASMAKER.start, HeaderUsuaris.AVGASMAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna AVGASBREAKER del CSV
     * @param username nom d'usuari (clau)
     * @return llista de AVGASBREAKER
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Double> getAvgAsBreaker(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getListDoubleinString(csvFile.getLinebyKey(username), HeaderUsuaris.AVGASBREAKER.start, HeaderUsuaris.AVGASBREAKER.end);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Retorna la columna NUMGAMESMAKER del CSV
     * @param username nom d'usuari (clau)
     * @return llista de NUMGAMESMAKER
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getNumGamesAsMaker(String username) throws PersistanceException {
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

        /**
         * Retorna la llargada real del header
         * @return un enter amb la llargada del header
         */
        private static Integer getLength() {
            return values()[(values().length - 1)].end + 1;
        }
    }
}
