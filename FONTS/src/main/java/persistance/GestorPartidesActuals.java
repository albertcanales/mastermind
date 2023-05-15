package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.util.Arrays;
import java.util.List;


/**
 * Gestor de les PartidesActuals
 *
 * @author Arnau Valls Fusté
 */
public class GestorPartidesActuals {

    private static final String relativePathPartidesActuals = "partidesActuals/partidesActuals.csv";

    private final GestorCSVFile csvFile;

    GestorPartidesActuals(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativePathPartidesActuals, HeaderPartides.getHeader(), HeaderPartides.USERNAME.start);

    }

    public void novaPartida(String username, Boolean isBreaker, Long temps, Integer dificultat, Integer algorisme, List<Integer> solucio, List<List<Integer>> intents,
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

    public Boolean existsPartidaActual(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void esborrarPartidaActual(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public Boolean isBreaker(String username) throws  PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Boolean.parseBoolean(gotline[HeaderPartides.ISBREAKER.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public Long getTemps(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Long.parseLong(gotline[HeaderPartides.TEMPS.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public Integer getDificultat(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderPartides.DIFICULTAT.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public Integer getAlgorisme(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderPartides.ALGORISME.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public List<Integer> getSolucio(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListIntinString(gotline, HeaderPartides.SOLUCIO.start, HeaderPartides.SOLUCIO.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public List<List<Integer>> getIntents(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderPartides.INTENTS.start, HeaderPartides.INTENTS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public void setIntents(String username, List<List<Integer>> intents) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            csvFile.setListListinString(intents, gotline, HeaderPartides.INTENTS.start, HeaderPartides.INTENTS.end);
            csvFile.setLinebyKey(username, gotline);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public List<List<Integer>> getFeedbacks(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderPartides.FEEDBACKS.start, HeaderPartides.FEEDBACKS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActuals);
        }
    }

    public void setFeedbacks(String username, List<List<Integer>> feedbacks) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            csvFile.setListListinString(feedbacks, gotline, HeaderPartides.FEEDBACKS.start, HeaderPartides.FEEDBACKS.end);
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
        TEMPS(2, 2),
        DIFICULTAT(3, 3),
        ALGORISME(4, 4),
        SOLUCIO(5, 8),
        INTENTS(9, 60),
        FEEDBACKS(61, 108);


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
         * Genera un array d'strings amb el Header corresponent
         *
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

        private static Integer getLength() {
            return values()[(values().length - 1)].end + 1;
        }
    }
}
