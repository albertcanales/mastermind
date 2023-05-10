package persistance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import exceptions.persistance.InvalidCSVException;
import exceptions.persistance.InvalidFileAccess;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gestor per crear, consultar, modificar i esborrar un CSV
 *
 * @author Arnau Valls Fusté
 */
public class GestorCSVFile {
    private final File file;

    private final Integer keyPos;

    private static final int headerPos = 0; //El header sempre està a la posició 0 d'un CSV

    private static final int listSize = 4;

    private static final int listListRows = 48;
    private static final int listListSize = listListRows/listSize;

    private void listToStringArray(List<Integer> list, String[] line, Integer start, Integer end) {
        for (int i = start; i <= end; ++i) {
            int listPosition = i - start;
            if (list.get(listPosition) == null) {
                line[i] = "null";
            } else {
                line[i] = list.get(listPosition).toString();
            }

        }
    }

    private List<Integer> stringArrayToList(String[] line, Integer start, Integer end) {
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            if (line[i].equals("null")) {
                list.add(null);
            } else {
                list.add(Integer.parseInt(line[i]));
            }
        }
        return list;
    }

    private List<Integer> listListToList(List<List<Integer>> list) {
        List<Integer> flattenedlist = new ArrayList<>();

        for (int i = 0; i < listListRows; ++i) flattenedlist.add(0);

        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < list.get(i).size(); ++j) {
                flattenedlist.set((4) * i + j, list.get(i).get(j));
            }
        }
        return flattenedlist;

    }

    private List<List<Integer>> listToListList(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < listListSize; ++i) {
            List<Integer> resultTemp = new ArrayList<>();
            for (int j = 0; j < listSize; ++j) {
                resultTemp.add(0);
            }
            result.add(resultTemp);
        }

        for (int i = 0; i < listListSize; ++i) {
            for (int j = 0; j < listSize; ++j) {
                result.get(i).set(j, list.get(listSize * i + j));
            }
        }
        return result;

    }

    private List<List<Integer>> fillListListWithNull(List<List<Integer>> list) {
        List<List<Integer>> nullList = new ArrayList<>(list);
        for (int i = nullList.size(); i < listListSize; ++i) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < listSize; ++j) {
                temp.add(null);
            }
            nullList.add(temp);
        }
        return nullList;
    }

    private void removeNullFromList(List<List<Integer>> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).get(0) == null) {
                list.remove(i);
            }
        }
    }

    public void setListinString(List<Integer> list, String[] line, Integer start, Integer end) {
        listToStringArray(list, line, start, end);
    }

    public List<Integer> getListinString(String[] line, Integer start, Integer end) {
        return stringArrayToList(line, start, end);
    }

    public void setListListinString(List<List<Integer>> list, String[] line, Integer start, Integer end) {
        List<Integer> contiguousList = listListToList(fillListListWithNull(list));
        listToStringArray(contiguousList, line, start, end);
    }

    public List<List<Integer>> getListListinString(String[] line, Integer start, Integer end) {
        List<Integer> list = stringArrayToList(line, start, end);
        List<List<Integer>> listList = listToListList(list);
        removeNullFromList(listList);
        return listList;
    }

    /**
     * Crea el fitxer donat si no existeix i el seu directori pare si no existeix
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param header   un Array d'Strings amb el header a col·locar al fitxer
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV ja existeix i no té un format legal
     */
    public GestorCSVFile(String fileName, String[] header, Integer keyPos) throws PersistanceException {

        file = new File(fileName);
        file.getParentFile().mkdirs();

        boolean hasJustBeenCreated;
        try {
            hasJustBeenCreated = file.createNewFile();
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

        if (hasJustBeenCreated) addLine(header); //Si l'acabem de crear, posem el header
        else {
            if (!Arrays.equals(readAllLines().get(headerPos), header)) {
                throw new InvalidCSVException(file.getName()); //Si existeix i el header és erroni tirem excepció
            }
        }

        this.keyPos = keyPos;


    }

    /**
     * Esborra el fitxer donat si existeix
     *
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void deleteFile() throws InvalidFileAccess {
            if (!file.delete()) throw new InvalidFileAccess(file.getName());
    }

    /**
     * Afegeix la línia donada al final del fitxer
     *
     * @param line     un Array d'Strings amb la línia a col·locar al fitxer
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void addLine(String[] line) throws InvalidFileAccess {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(file, true)); //fem append
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }

        csvWriter.writeNext(line);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }

    }

    /**
     * Troba la línia amb la primera ocurrència de key a allLines
     *
     * @param allLines una llista d'String amb totes les línies
     * @param key      l'identificador de la línia a buscar
     * @return un Enter amb el número de la línia
     */
    private Integer getLineNumberByKey(List<String[]> allLines, String key) {
        int i = 0;
        while (i < allLines.size()) {
            if (allLines.get(i)[keyPos].equals(key)) return i;
            ++i;
        }
        return -1;
    }

    /**
     * Retorna totes les línies d'un fitxer
     *
     * @return una llista d'Strings amb totes les línies d'un fitxer
     * @throws InvalidFileAccess   si no es pot accedir al fitxer
     * @throws InvalidCSVException si el CSV no té un format legal
     */
    private List<String[]> readAllLines() throws InvalidFileAccess, InvalidCSVException {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(file));
        } catch (java.io.FileNotFoundException e) {
            throw new InvalidFileAccess(file.getName());
        }
        List<String[]> allLines;
        try {
            allLines = csvReader.readAll();
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        } catch (CsvException e) {
            throw new InvalidCSVException(file.getName());
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }

        return allLines;
    }

    /**
     * Escriu totes les línies d'un fitxer donat una llista d'Strings
     *
     * @param allLines una llista d'Strings amb totes les línies d'un fitxer
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    private void writeAllLines(List<String[]> allLines) throws InvalidFileAccess {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(file, false));
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }
        csvWriter.writeAll(allLines);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }
    }

    /**
     * Retorna totes les línies d'un fitxer sense el header
     *
     * @return una llista d'Strings amb totes les línies d'un fitxer
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public List<String[]> getLines() throws PersistanceException {
        List<String[]> allLines = readAllLines();

        allLines.remove(headerPos); //Treiem el Header
        return allLines;
    }

    /**
     * Retorna un línia d'un fitxer donat la clau d'aquesta
     *
     * @param key      l'identificador de la línia a buscar
     * @return un Array d'Strings amb la línia corresponent
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public String[] getLinebyKey(String key) throws PersistanceException {
        List<String[]> allLines = readAllLines();

        allLines.remove(headerPos); //Treiem el Header per no recorre'l
        int rowNumber = getLineNumberByKey(allLines, key);
        if (rowNumber == -1) throw new LineNotFoundException(key, file.getName());
        return allLines.get(rowNumber);
    }

    /**
     * Retorna un booleà cert o fals depenent de si la línia existeix
     *
     * @param key      l'identificador de la línia a buscar
     * @return un booleà cert o fals depenent de si la línia existeix
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public Boolean existsLinebyKey(String key) throws PersistanceException {
        List<String[]> allLines = readAllLines();

        allLines.remove(headerPos); //Treiem el Header per no recorre'l

        int rowNumber = getLineNumberByKey(allLines, key);
        return rowNumber != -1;

    }

    /**
     * Esborra un línia d'un fitxer donat la clau d'aquesta
     *
     * @param key      l'identificador de la línia a buscar
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void removeLinebyKey(String key) throws PersistanceException {
        List<String[]> allLines = readAllLines();

        String[] header = allLines.get(headerPos);
        allLines.remove(headerPos); //Treiem el Header per no recorre'l


        int rowNumber = getLineNumberByKey(allLines, key);
        if (rowNumber == -1) throw new LineNotFoundException(key, file.getName());
        allLines.remove(rowNumber);

        allLines.add(headerPos, header); //I el tornem a posar

        writeAllLines(allLines);

    }

    /**
     * Posa el contingut a una línia donat la seva key
     *
     * @param key      l'identificador de la línia a buscar
     * @param line     line un Array d'Strings amb la línia a col·locar al fitxer
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setLinebyKey(String key, String[] line) throws PersistanceException {
        List<String[]> allLines = readAllLines();

        String[] header = allLines.get(headerPos);
        allLines.remove(headerPos); //Treiem el Header per no recorre'l


        int rowNumber = getLineNumberByKey(allLines, key);
        if (rowNumber == -1) throw new LineNotFoundException(key, file.getName());
        allLines.set(rowNumber, line);

        allLines.add(headerPos, header); //I el tornem a posar

        writeAllLines(allLines);

    }

}
