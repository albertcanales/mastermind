package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.util.Arrays;
import java.util.List;


/**
 * Gestor de les PartidesActuals
 * @author Arnau Valls Fusté
 */
class GestorPartidesActuals {

    /**
     * String que representa l'arxiu que manipularà el Gestor
     */
    private static final String relativePathPartidesActuals = "partidesActuals/partidesActuals.csv";

    /**
     * Instància de GestorCSVFile que representa el fitxer
     */
    private final GestorCSVFile csvFile;

    /**
     * Constructor del Gestor de Partides Actuals
     * @param basePath directori base a partir d'on es crearan tots els arxius
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV ja existeix i no té un format legal
     */
    GestorPartidesActuals(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativePathPartidesActuals, HeaderPartides.getHeader(), HeaderPartides.USERNAME.start);

    }

    /**
     * Estableix els paràmetres passats a les columnes corresponents en una nova línia del CSV
     * @param username nom d'usuari (clau)
     * @param isBreaker la columna ISBREAKER
     * @param temps la columna TEMPS
     * @param dificultat la columna DIFICULTAT
     * @param algorisme la columna ALGORISME
     * @param solucio la columna SOLUCIO
     * @param intents la columna INTENTS
     * @param feedback la columna FEEDBACKS
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal o si la línia ja existeix
     */
    void novaPartida(String username, Boolean isBreaker, Long temps, Integer dificultat, Integer algorisme, List<Integer> solucio, List<List<Integer>> intents,
                     List<List<Integer>> feedback) throws PersistanceException {
        if (!csvFile.existsLinebyKey(username)) {
            String[] line = new String[HeaderPartides.getLength()];
            Arrays.fill(line, "");
            line[HeaderPartides.USERNAME.start] = username;
            line[HeaderPartides.ISBREAKER.start] = isBreaker.toString();
            if (temps != null) line[HeaderPartides.TEMPS.start] = temps.toString();
            if (dificultat != null) line[HeaderPartides.DIFICULTAT.start] = dificultat.toString();
            if (algorisme != null) line[HeaderPartides.ALGORISME.start] = algorisme.toString();

            csvFile.setListinString(solucio, line, HeaderPartides.SOLUCIO.start, HeaderPartides.SOLUCIO.end);

            csvFile.setListListinString(intents, line, HeaderPartides.INTENTS.start, HeaderPartides.INTENTS.end);

            csvFile.setListListinString(feedback, line, HeaderPartides.FEEDBACKS.start, HeaderPartides.FEEDBACKS.end);

            csvFile.addLine(line);

        } else {
            throw new LineAlreadyExistsException(username, relativePathPartidesActuals);
        }

    }

    /**
     * Comprova si existeix una entrada al CSV de la clau username
     * @param username nom d'usuari (clau)
     * @return cert o fals
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    Boolean existsPartidaActual(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    /**
     * Esborra una línia del CSV donada la clau username
     * @param username nom d'usuari (clau)
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void esborrarPartidaActual(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna a la columna ISBREAKER del CSV
     * @param username nom d'usuari (clau)
     * @return el Booleà ISBREAKER
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    Boolean isBreaker(String username) throws  PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Boolean.parseBoolean(gotline[HeaderPartides.ISBREAKER.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna la columna TEMPS del CSV
     * @param username nom d'usuari (clau)
     * @return un Long del TEMPS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    Long getTemps(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Long.parseLong(gotline[HeaderPartides.TEMPS.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Estableix la columna TEMPS del CSV
     * @param username nom d'usuari (clau)
     * @param temps un Long del TEMPS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void setTemps(String username, Long temps) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            gotline[HeaderPartides.TEMPS.start] = temps.toString();
            csvFile.setLinebyKey(username, gotline);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna la columna DIFICULTAT del CSV
     * @param username nom d'usuari (clau)
     * @return un enter de la DIFICULTAT
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    Integer getDificultat(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderPartides.DIFICULTAT.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna la columna ALGORISME del CSV
     * @param username nom d'usuari (clau)
     * @return un enter de l'ALGORISME
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    Integer getAlgorisme(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderPartides.ALGORISME.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna les columnes de la SOLUCIO del CSV
     * @param username nom d'usuari (clau)
     * @return una llista de la SOLUCIO
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<Integer> getSolucio(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListIntinString(gotline, HeaderPartides.SOLUCIO.start, HeaderPartides.SOLUCIO.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna les columnes dels INTENTS del CSV
     * @param username nom d'usuari (clau)
     * @return llista d'una llista d'enters dels INTENTS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<List<Integer>> getIntents(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderPartides.INTENTS.start, HeaderPartides.INTENTS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Estableix les columnes dels INTENTS del CSV
     * @param username nom d'usuari (clau)
     * @param intents llista d'una llista d'enters dels INTENTS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void setIntents(String username, List<List<Integer>> intents) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            csvFile.setListListinString(intents, gotline, HeaderPartides.INTENTS.start, HeaderPartides.INTENTS.end);
            csvFile.setLinebyKey(username, gotline);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna les columnes dels FEEDBACKS del CSV
     * @param username nom d'usuari (clau)
     * @return llista d'una llista d'enters dels FEEDBACKS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    List<List<Integer>> getFeedbacks(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderPartides.FEEDBACKS.start, HeaderPartides.FEEDBACKS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Estableix les columnes dels FEEDBACKS del CSV
     * @param username nom d'usuari (clau)
     * @param feedbacks llista d'una llista d'enters dels FEEDBACKS
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void setFeedbacks(String username, List<List<Integer>> feedbacks) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            csvFile.setListListinString(feedbacks, gotline, HeaderPartides.FEEDBACKS.start, HeaderPartides.FEEDBACKS.end);
            csvFile.setLinebyKey(username, gotline);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Retorna la columna de SOLUCIOVISTA del CSV
     * @param username nom d'usuari (clau)
     * @return un booleà de SOLUCIOVISTA
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    Boolean getSolucioVista(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Boolean.parseBoolean(gotline[HeaderPartides.SOLUCIOVISTA.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Estableix la columna de SOLUCIOVISTA del CSV
     * @param username nom d'usuari (clau)
     * @param SolucioVista un booleà de SOLUCIOVISTA
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    void setSolucioVista(String username, Boolean SolucioVista) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            gotline[HeaderPartides.SOLUCIOVISTA.start] = SolucioVista.toString();
            csvFile.setLinebyKey(username, gotline);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum HeaderPartides {
        USERNAME(0, 0),
        ISBREAKER(1, 1),
        SOLUCIOVISTA(2, 2),
        TEMPS(3, 3),
        DIFICULTAT(4, 4),
        ALGORISME(5, 5),
        SOLUCIO(6, 9),
        INTENTS(10, 61),
        FEEDBACKS(62, 109);


        private final int start;
        private final int end;

        /**
         * Constructor del Header
         *
         * @param start és el valor que representa la columna
         */
        HeaderPartides(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * Genera un array d'Strings amb el Header corresponent
         *
         * @return un array d'Strings
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
