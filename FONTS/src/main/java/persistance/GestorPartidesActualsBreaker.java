package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorPartidesActualsBreaker {

    private static final String relativePathPartidesActualsBreaker = "partidesActuals/partidesActualsBreaker.csv";

    private final GestorCSVFile csvFile;

    private List<List<Integer>> getListListFirstNull() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sequence = Arrays.asList(0, 0, 0, 0);
        list.add(sequence);
        return list;
    }

    public static void main(String[] args) throws PersistanceException {
        GestorPartidesActualsBreaker gestor = new GestorPartidesActualsBreaker("./db/");
        gestor.novaPartidaBreaker("arnau", 1000L, 2, List.of(0, 4, 6, 8));


        //List<List<Integer>> list = gestor.getListList(32131);
        //System.out.println(list);
        //List<String> list = List.of("Alexandru", "John");
        //System.out.println(list.toString());

        //String s = list.toString();
        //s = s.replace("[","").replace("]","");
        //System.out.println(Arrays.asList("[Alexandru, John]"));
        //List<String> myList = Arrays.asList(s.split(",",-1));
        return;
    }

    GestorPartidesActualsBreaker(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativePathPartidesActualsBreaker, HeaderBreaker.getHeader(), HeaderBreaker.USERNAME.start);

    }

    public void novaPartidaBreaker(String username, Long temps, Integer dificultat, List<Integer> solucio) throws PersistanceException {
        //TODO: solucio size 4 etc
        if (!csvFile.existsLinebyKey(username)) {
            String[] line = new String[HeaderBreaker.getLength()];
            line[HeaderBreaker.USERNAME.start] = username;
            line[HeaderBreaker.TEMPS.start] = temps.toString();
            line[HeaderBreaker.DIFICULTAT.start] = dificultat.toString();

            csvFile.setListIntinString(solucio, line, HeaderBreaker.SOLUCIO.start, HeaderBreaker.SOLUCIO.end);

            List<List<Integer>> intents = getListListFirstNull();

            List<List<Integer>> feedbacks = new ArrayList<>();

            csvFile.setListListinString(intents, line, HeaderBreaker.INTENTS.start, HeaderBreaker.INTENTS.end);

            csvFile.setListListinString(feedbacks, line, HeaderBreaker.FEEDBACKS.start, HeaderBreaker.FEEDBACKS.end);

            csvFile.addLine(line);

        } else {
            throw new LineAlreadyExistsException(username, relativePathPartidesActualsBreaker);
        }

    }

    public Boolean existsPartidaActual(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void esborrarPartidaActual(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    public Long getTemps(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Long.parseLong(gotline[HeaderBreaker.TEMPS.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    public Integer getDificultat(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderBreaker.DIFICULTAT.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    public List<Integer> getSolucio(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListIntinString(gotline, HeaderBreaker.SOLUCIO.start, HeaderBreaker.SOLUCIO.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    public List<List<Integer>> getIntents(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderBreaker.INTENTS.start, HeaderBreaker.INTENTS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    public List<List<Integer>> getFeedbacks(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderBreaker.FEEDBACKS.start, HeaderBreaker.FEEDBACKS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsBreaker);
        }
    }

    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum HeaderBreaker {
        USERNAME(0, 0),
        TEMPS(1, 1),
        DIFICULTAT(2, 2),
        SOLUCIO(3, 6),
        INTENTS(7, 54),
        FEEDBACKS(55, 102);


        private final int start;
        private final int end;

        /**
         * Constructor del Header de Breaker
         *
         * @param start és el valor que representa la columna
         */
        HeaderBreaker(int start, int end) {
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

        public static Integer getLength() {
            return values()[(values().length - 1)].end + 1;
        }
    }
}
