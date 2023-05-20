package persistance;

import exceptions.persistance.PersistanceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor del Rànquing guardat a ranquing/facil.csv, ranquing/normal.csv i ranquing/dificil.csv
 * @author Arnau Valls Fusté
 */
class GestorRanquing {
    /**
     * Nombre de fitxers que gestionarà el Gestor
     */
    static final Integer numFitxers = 3;

    /**
     * Instància de GestorCSVFile que representa el fitxer
     */
    private final GestorCSVFile[] csvFiles = new GestorCSVFile[numFitxers];

    /**
     * Strings que representen els arxius que manipularà el Gestor
     */
    private static final String[] relativePaths = new String[]{"ranquing/facil.csv", "ranquing/normal.csv", "ranquing/dificil.csv"};

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
    List<List<String>> getRanquing(Integer file) throws PersistanceException {

        List<String[]> allLines = csvFiles[file].readAllLines(true);

        List<List<String>> ranquing = new ArrayList<>();
        for (String[] line : allLines) {
            ranquing.add(List.of(line[HeaderRanquing.USERNAME.number], (line[HeaderRanquing.NUMINTENTS.number]),
                    (line[HeaderRanquing.TEMPS.number])));
        }

        return ranquing;

    }

    /**
     * Estableix el rànquing d'un fitxer donat
     * @param file un enter de 0 a 2 representant l'arxiu
     * @param ranquing una llista d'objectes composta amb el rànquing
     * @throws PersistanceException si no es pot escriure algun fitxer
     */
    void setRanquing(Integer file, List<List<String>> ranquing) throws PersistanceException {
        List<String[]> allLines = new ArrayList<>();

        for (List<String> user : ranquing) {
            String[] line = new String[HeaderRanquing.getHeader().length];
            for (int i = 0; i < line.length; ++i) {
                line[i] = user.get(i);
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
