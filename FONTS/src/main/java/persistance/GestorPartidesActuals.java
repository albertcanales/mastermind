package persistance;

import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorPartidesActuals {

    private static final String relativePathPartidesActualsBreaker = "partidesActuals/partidesActualsBreaker.csv";
    private static final String relativePathPartidesActualsMaker = "partidesActuals/partidesActualsMaker.csv";


    private final GestorCSVFile csvFileBreaker, csvFileMaker;

    private final int resultSizej = (HeaderMaker.SOLUCIO.end - HeaderMaker.SOLUCIO.start + 1);
    private final int resultSizei = ((HeaderMaker.INTENTS.end - HeaderMaker.INTENTS.start) + 1) / resultSizej;

    private List<Integer> getList(long seed) {
        List<Integer> list = new ArrayList<>();
        Random rand = new Random(seed);
        for (int i = 0; i < 4; i++) {
            int enter = rand.nextInt(5);
            list.add(enter);
        }

        return list;
    }

    private List<List<Integer>> getListList(long seed) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<Integer> sequence = getList(seed+i);
            list.add(sequence);
        }
        return list;
    }

    public static void main(String[] args) throws PersistanceException {
        GestorPartidesActuals gestor = new GestorPartidesActuals("./db/");
        gestor.novaPartidaMaker("arnau", 2, List.of(0, 4, 6, 8));


        List<List<Integer>> list = gestor.getListList(32131);
        System.out.println(list);
        //List<String> list = List.of("Alexandru", "John");
        //System.out.println(list.toString());

        //String s = list.toString();
        //s = s.replace("[","").replace("]","");
        //System.out.println(Arrays.asList("[Alexandru, John]"));
        //List<String> myList = Arrays.asList(s.split(",",-1));
        return;
    }

    GestorPartidesActuals(String basePath) throws PersistanceException {
        csvFileBreaker = new GestorCSVFile(basePath + relativePathPartidesActualsBreaker, HeaderBreaker.getHeader(), HeaderBreaker.USERNAME.start);
        csvFileMaker = new GestorCSVFile(basePath + relativePathPartidesActualsMaker, HeaderMaker.getHeader(), HeaderMaker.USERNAME.start);



    }
    private void listToStringArray(List<Integer> list, String[] line, Integer start, Integer end) {
        for (int i = start; i <= end; ++i) {
            int listPosition = i - start;
            if (list.get(listPosition) == null)
            {
                line[i] = "null";
            }
            else {
                line[i] = list.get(listPosition).toString();
            }

        }
    }

    private List<Integer> stringArrayToList(String[] line, Integer start, Integer end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            if (line[i].equals("null")) {
                list.add(null);
            }
            else {
                list.add(Integer.parseInt(line[i]));
            }
        }
        return list;
    }

    private List<Integer> listListToList(List<List<Integer>> list) {
        List<Integer> flattenedlist = new ArrayList<>();

        for (int i = 0; i < (HeaderMaker.INTENTS.end - HeaderMaker.INTENTS.start) + 1; ++i) flattenedlist.add(0);

        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < list.get(i).size(); ++j) {
                flattenedlist.set((HeaderMaker.SOLUCIO.end - HeaderMaker.SOLUCIO.start + 1)*i+j, list.get(i).get(j));
            }
        }
        return flattenedlist;

    }

    private List<List<Integer>> listToListList(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < resultSizei; ++i) {
            List<Integer> resultTemp = new ArrayList<>();
            for (int j = 0; j < resultSizej; ++j) {
                resultTemp.add(0);
            }
            result.add(resultTemp);
        }

        for (int i = 0; i < resultSizei; ++i) {
            for (int j = 0; j < resultSizej; ++j) {
                result.get(i).set(j, list.get((HeaderMaker.SOLUCIO.end - HeaderMaker.SOLUCIO.start + 1)*i+j));
            }
        }
        return result;

    }

    private List<List<Integer>> fillListListWithNull(List<List<Integer>> list)
    {
        List<List<Integer>> nullList = new ArrayList<>(list);
        for (int i = nullList.size(); i < resultSizei; ++i) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < resultSizej; ++j){
                temp.add(null);
            }
            nullList.add(temp);
        }
        return nullList;
    }

    private void removeNullFromList(List<List<Integer>> list)
    {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).get(0) == null) {
                list.remove(i);
            }
        }
    }

    private void setListinString(List<Integer> list, String[] line, Integer start, Integer end) {
        listToStringArray(list, line, start, end);
    }

    private List<Integer> getListinString(String[] line, Integer start, Integer end) {
        return stringArrayToList(line, start, end);
    }

    private void setListListinString(List<List<Integer>> list, String[] line, Integer start, Integer end) {
        List<Integer> contiguousList = listListToList(fillListListWithNull(list));
        listToStringArray(contiguousList, line, start, end);
    }

    private List<List<Integer>> getListListinString(String[] line, Integer start, Integer end) {
        List<Integer> list = stringArrayToList(line, start, end);
        List<List<Integer>> listList = listToListList(list);
        removeNullFromList(listList);
        return listList;
    }

    public void novaPartidaMaker(String username, Integer algorisme, List<Integer> solucio) throws PersistanceException {
        //TODO: solucio size 4 etc
        String[] line = new String[HeaderMaker.getLength()];
        line[HeaderMaker.USERNAME.start] = username;
        line[HeaderMaker.ALGORISME.start] = algorisme.toString();

        setListinString(solucio, line, HeaderMaker.SOLUCIO.start, HeaderMaker.SOLUCIO.end);

        List<List<Integer>> putListList = new ArrayList<>();
        //List<List<Integer>> putListList = getListList(32131);

        for (int i = 0; i < 1; ++i) {
            putListList.add(Arrays.asList(0, 0, 0, 0));
        }

        setListListinString(putListList, line, HeaderMaker.INTENTS.start, HeaderMaker.INTENTS.end);

        setListListinString(putListList, line, HeaderMaker.FEEDBACKS.start, HeaderMaker.FEEDBACKS.end);

        csvFileMaker.addLine(line);

        String [] gotline = csvFileMaker.getLinebyKey("arnau");


        List<List<Integer>> intentsRead = getListListinString(gotline, HeaderMaker.INTENTS.start, HeaderMaker.INTENTS.end);
        List<Integer> solucionsRead = getListinString(gotline, HeaderMaker.SOLUCIO.start, HeaderMaker.SOLUCIO.end);

        if (solucio.equals(solucionsRead)) System.out.println("funciona");

        if (intentsRead.equals(putListList)) System.out.println("funcionaaaaaaaaaaaaaaa");
        return; //pel debugger
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

                }
                else {
                    int firstCompondValue = i;
                    while (firstCompondValue < values().length) {

                        int listNumber = 0;
                        for (int startToEnd = values()[i].start; startToEnd <= values()[i].end; ++startToEnd)
                        {
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

                }
                else {
                    int firstCompondValue = i;
                    while (firstCompondValue < values().length) {

                        int listNumber = 0;
                        for (int startToEnd = values()[i].start; startToEnd <= values()[i].end; ++startToEnd)
                        {
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
