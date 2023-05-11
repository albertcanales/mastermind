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
public class GestorPartidesActualsMaker {

    private static final String relativePathPartidesActualsMaker = "partidesActuals/partidesActualsMaker.csv";


    private final GestorCSVFile csvFile;

    private List<List<Integer>> getListListFirstNull() {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sequence = Arrays.asList(null, null, null, null);
        list.add(sequence);
        return list;
    }

    public static void main(String[] args) throws PersistanceException {
        GestorPartidesActualsMaker gestor = new GestorPartidesActualsMaker("./db/");
        gestor.novaPartidaMaker("arnau", 2, List.of(0, 4, 6, 8));


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

    GestorPartidesActualsMaker(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativePathPartidesActualsMaker, HeaderMaker.getHeader(), HeaderMaker.USERNAME.start);


    }

    public void novaPartidaMaker(String username, Integer algorisme, List<Integer> solucio) throws PersistanceException {
        //TODO: solucio size 4 etc
        if (!csvFile.existsLinebyKey(username)) {
            String[] line = new String[HeaderMaker.getLength()];
            line[HeaderMaker.USERNAME.start] = username;
            line[HeaderMaker.ALGORISME.start] = algorisme.toString();

            csvFile.setListinString(solucio, line, HeaderMaker.SOLUCIO.start, HeaderMaker.SOLUCIO.end);

            List<List<Integer>> intents = getListListFirstNull();

            List<List<Integer>> feedbacks = new ArrayList<>();

            csvFile.setListListinString(intents, line, HeaderMaker.INTENTS.start, HeaderMaker.INTENTS.end);

            csvFile.setListListinString(feedbacks, line, HeaderMaker.FEEDBACKS.start, HeaderMaker.FEEDBACKS.end);

            csvFile.addLine(line);

        } else {
            throw new LineAlreadyExistsException(username, relativePathPartidesActualsMaker);
        }

    }

    public Boolean existsPartidaActual(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void esborrarPartidaActual(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsMaker);
        }
    }

    public Integer getAlgorisme(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return Integer.parseInt(gotline[HeaderMaker.ALGORISME.start]);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsMaker);
        }
    }

    public List<Integer> getSolucio(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListinString(gotline, HeaderMaker.SOLUCIO.start, HeaderMaker.SOLUCIO.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsMaker);
        }
    }

    public List<List<Integer>> getIntents(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderMaker.INTENTS.start, HeaderMaker.INTENTS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsMaker);
        }
    }

    public List<List<Integer>> getFeedbacks(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            String[] gotline = csvFile.getLinebyKey(username);

            return csvFile.getListListinString(gotline, HeaderMaker.FEEDBACKS.start, HeaderMaker.FEEDBACKS.end);
        } else {
            throw new LineNotFoundException(username, relativePathPartidesActualsMaker);
        }
    }


    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum HeaderMaker {
        USERNAME(0, 0),
        ALGORISME(1, 1),
        SOLUCIO(2, 5),
        INTENTS(6, 53),
        FEEDBACKS(54, 101);


        private final int start;
        private final int end;

        /**
         * Constructor del Header de Breaker
         *
         * @param start és el valor que representa la columna
         */
        HeaderMaker(int start, int end) {
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
