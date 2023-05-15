package persistance;

import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor del Rànquing guardat a ranquing/facil.csv, ranquing/normal.csv i ranquing/dificil.csv
 */
public class GestorRanquing {
    private static final Integer numFitxers = 3;
    private final GestorCSVFile[] csvFiles = new GestorCSVFile[numFitxers];
    private static final String[] relativePaths = new String[]{"ranquing/facil.csv", "ranquing/normal.csv", "ranquing/dificil.csv"};

    public static void main(String[] args) throws PersistanceException {
        List<List<Object>> ranquing = new ArrayList<>();
        ranquing.add(List.of("arnau", 5, 400L));
        ranquing.add(List.of("kamil", 7, 300L));
        ranquing.add(List.of("mar", 10, 700L));

        GestorRanquing gestorRanquing = new GestorRanquing("./db/");
        gestorRanquing.setRanquing(2, ranquing);
        Boolean equals = ranquing.equals(gestorRanquing.getRanquing(2));
        System.out.println(equals);

    }

    /**
     * Constructor del Rànquing
     * @param basePath directori base on es guardarà la carpeta
     * @throws PersistanceException si no es pot crear algun fitxer
     */
    GestorRanquing(String basePath) throws PersistanceException {
        for (int i = 0; i < csvFiles.length; ++i) {
            csvFiles[i] = new GestorCSVFile(basePath + relativePaths[i], HeaderRanquing.getHeader(), HeaderRanquing.USERNAME.number);
        }
    }

    /**
     * Retorna el rànquing d'un fitxer donat
     * @param file un enter de 0 a 2 representant l'arxiu
     * @return una llista d'objectes composta amb el rànquing
     * @throws PersistanceException si no es pot llegir algun fitxer
     */
    public List<List<Object>> getRanquing(Integer file) throws PersistanceException {

        List<String[]> allLines = csvFiles[file].readAllLines(true);

        List<List<Object>> ranquing = new ArrayList<>();
        for (String[] line : allLines) {
            ranquing.add(List.of(line[HeaderRanquing.USERNAME.number], Integer.parseInt(line[HeaderRanquing.NUMINTENTS.number]),
                    Long.parseLong(line[HeaderRanquing.TEMPS.number])));
        }

        return ranquing;

    }

    /**
     * Estableix el rànquing d'un fitxer donat
     * @param file un enter de 0 a 2 representant l'arxiu
     * @param ranquing una llista d'objectes composta amb el rànquing
     * @throws PersistanceException si no es pot escriure algun fitxer
     */
    public void setRanquing(Integer file, List<List<Object>> ranquing) throws PersistanceException {
        List<String[]> allLines = new ArrayList<>();

        for (List<Object> user : ranquing) {
            String[] line = new String[HeaderRanquing.getHeader().length];
            for (int i = 0; i < line.length; ++i) {
                line[i] = user.get(i).toString();
            }
            allLines.add(line);
        }

        csvFiles[file].writeAllLines(allLines);

    }


    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum HeaderRanquing {
        USERNAME(0),
        NUMINTENTS(1),
        TEMPS(2);

        private final int number;

        /**
         * Constructor del Header d'Usuaris
         *
         * @param number és el valor que representa la columna
         */
        HeaderRanquing(int number) {
            this.number = number;
        }

        /**
         * Genera un array d'strings amb el Header corresponent
         * @return un array d'strings
         */
        private static String[] getHeader() {
            String[] header = new String[values().length];

            for(int i = 0; i < values().length; ++i){
                header[i] = values()[i].name();

            }
            return header;
        }

    }

}
