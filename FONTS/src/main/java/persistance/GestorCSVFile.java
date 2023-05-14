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
    private final String[] header;
    private static final int listSize = 4;
    private static final int listListRows = 48;
    private static final int listListSize = listListRows/listSize;


    /**
     * Posa el contingut d'una llista d'enters en un array d'Strings entre les dues posicions donades
     * @param list la llista d'enters a col·locar
     * @param line l'Array d'Strings on es col·locarà la llista
     * @param start la posició inicial de l'String on es col·locarà la llista
     * @param end la posició final de l'String on es col·locarà la llista
     */
    public void setListIntinString(List<Integer> list, String[] line, Integer start, Integer end) {
        for (int i = start; i <= end; ++i) {
            int listPosition = i - start;
            if (list.get(listPosition) == null) {
                line[i] = "null";
            } else {
                line[i] = list.get(listPosition).toString();
            }

        }
    }

    /**
     * Retorna una llista d'enters a partir d'un Array d'Strings i dues posicions
     * @param line l'Array d'Strings d'on es formarà la llista
     * @param start la posició inicial de l'String d'on es formarà la llista
     * @param end la posició final de l'String d'on es formarà la llista
     * @return una llista d'enters
     */
    public List<Integer> getListIntinString(String[] line, Integer start, Integer end) {
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

    /**
     * Posa el contingut d'una llista de Long(s) en un array d'Strings entre les dues posicions donades
     * @param list la llista de Long(s) a col·locar
     * @param line l'Array d'Strings on es col·locarà la llista
     * @param start la posició inicial de l'String on es col·locarà la llista
     * @param end la posició final de l'String on es col·locarà la llista
     */
    public void setListLonginString(List<Long> list, String[] line, Integer start, Integer end) {
        for (int i = start; i <= end; ++i) {
            int listPosition = i - start;
            line[i] = list.get(listPosition).toString();
        }
    }

    /**
     * Retorna una llista de Long(s) a partir d'un Array d'Strings i dues posicions
     * @param line l'Array d'Strings d'on es formarà la llista
     * @param start la posició inicial de l'String d'on es formarà la llista
     * @param end la posició final de l'String d'on es formarà la llista
     * @return una llista de Long(s)
     */
    public List<Long> getListLonginString(String[] line, Integer start, Integer end) {
        List<Long> list = new ArrayList<>();
        for (int i = start; i <= end; ++i)
            list.add(Long.parseLong(line[i]));
        return list;
    }

    /**
     * Posa el contingut d'una llista de Double(s) en un array d'Strings entre les dues posicions donades
     * @param list la llista de Double(s) a col·locar
     * @param line l'Array d'Strings on es col·locarà la llista
     * @param start la posició inicial de l'String on es col·locarà la llista
     * @param end la posició final de l'String on es col·locarà la llista
     */
    public void setListDoubleinString(List<Double> list, String[] line, Integer start, Integer end) {
        for (int i = start; i <= end; ++i) {
            int listPosition = i - start;
            line[i] = list.get(listPosition).toString();
        }
    }

    /**
     * Retorna una llista de Double(s) a partir d'un Array d'Strings i dues posicions
     * @param line l'Array d'Strings d'on es formarà la llista
     * @param start la posició inicial de l'String d'on es formarà la llista
     * @param end la posició final de l'String d'on es formarà la llista
     * @return una llista de Double(s)
     */
    public List<Double> getListDoubleinString(String[] line, Integer start, Integer end) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; ++i)
            list.add(Double.parseDouble(line[i]));
        return list;
    }

    /**
     * Posa el contingut d'una llista (d'una llista d'enters) en un array d'Strings entre les dues posicions donades
     * @param list la llista (d'una llista d'enters) a col·locar
     * @param line l'Array d'Strings on es col·locarà la llista
     * @param start la posició inicial de l'String on es col·locarà la llista
     * @param end la posició final de l'String on es col·locarà la llista
     */
    public void setListListinString(List<List<Integer>> list, String[] line, Integer start, Integer end) {
        List<Integer> contiguousList = listListToList(fillListListWithNull(list));
        setListIntinString(contiguousList, line, start, end);
    }

    /**
     * Retorna una llista (d'una llista d'enters) a partir d'un Array d'Strings i dues posicions
     * @param line l'Array d'Strings d'on es formarà la llista
     * @param start la posició inicial de l'String d'on es formarà la llista
     * @param end la posició final de l'String d'on es formarà la llista
     * @return una llista (d'una llista d'enters)
     */
    public List<List<Integer>> getListListinString(String[] line, Integer start, Integer end) {
        List<Integer> list = getListIntinString(line, start, end);
        List<List<Integer>> listList = listToListList(list);
        removeNullFromList(listList);
        return listList;
    }

    /**
     * Converteix una llista (d'una llista d'enters) en una llista contigua
     * @param list una llista (d'una llista d'enters)
     * @return una llista d'enters
     */
    private List<Integer> listListToList(List<List<Integer>> list) {
        List<Integer> flattenedlist = new ArrayList<>();

        for (int i = 0; i < listListRows; ++i) flattenedlist.add(0);

        for (int i = 0; i < list.size(); ++i) {
            for (int j = 0; j < list.get(i).size(); ++j) {
                flattenedlist.set(listSize * i + j, list.get(i).get(j));
            }
        }
        return flattenedlist;

    }

    /**
     * Converteix una llista en una (d'una llista d'enters)
     * @param list una llista (d'una llista d'enters)
     * @return una llista d'enters
     */
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

    /**
     * Omple una llista (d'una llista d'enters) amb null fins a arribar "listListSize" i "listSize"
     * @param list una llista (d'una llista d'enters)
     * @return una llista (d'una llista d'enters) plena amb null
     */
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

    /**
     * Treu les files amb "null" d'una llista d'enters
     * @param list una llista (d'una llista d'enters) sense els null
     */
    private void removeNullFromList(List<List<Integer>> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).get(0) == null) {
                list.remove(i);
            }
        }
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
            if (!Arrays.equals(readAllLines(false).get(headerPos), header)) {
                throw new InvalidCSVException(file.getName()); //Si existeix i el header és erroni tirem excepció
            }
        }

        this.keyPos = keyPos;
        this.header = header;

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
    private Integer getLineNumberByKey(List<String[]> allLines, String key) throws LineNotFoundException {
        int i = 0;
        while (i < allLines.size()) {
            if (allLines.get(i)[keyPos].equals(key)) return i;
            ++i;
        }
        throw new LineNotFoundException(key, file.getName());
    }

    /**
     * Busca si una línia amb la clau donada existeix
     *
     * @param allLines una llista d'String amb totes les línies
     * @param key      l'identificador de la línia a buscar
     * @return un Boolean cert o fals depenent de si existeix la línia o no
     */
    private Boolean foundLineByKey(List<String[]> allLines, String key) {
        int i = 0;
        while (i < allLines.size()) {
            if (allLines.get(i)[keyPos].equals(key)) return true;
            ++i;
        }
        return false;
    }

    /**
     * Retorna totes les línies d'un fitxer
     *
     * @param removeHeader si és true es treurà el header de la llista retornada
     * @return una llista d'Strings amb totes les línies d'un fitxer
     * @throws InvalidFileAccess   si no es pot accedir al fitxer
     * @throws InvalidCSVException si el CSV no té un format legal
     */
    public List<String[]> readAllLines(Boolean removeHeader) throws InvalidFileAccess, InvalidCSVException {
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

        if (removeHeader) allLines.remove(headerPos);
        return allLines;
    }

    /**
     * Escriu totes les línies d'un fitxer donat una llista d'Strings
     *
     * @param allLines una llista d'Strings amb totes les línies d'un fitxer (sense el header!!)
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void writeAllLines(List<String[]> allLines) throws InvalidFileAccess {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(file, false));
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }
        allLines.add(headerPos, header);
        csvWriter.writeAll(allLines);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(file.getName());
        }
    }

    /**
     * Retorna un línia d'un fitxer donat la clau d'aquesta
     *
     * @param key      l'identificador de la línia a buscar
     * @return un Array d'Strings amb la línia corresponent
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public String[] getLinebyKey(String key) throws PersistanceException {
        List<String[]> allLines = readAllLines(true);

        int rowNumber = getLineNumberByKey(allLines, key);
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
        List<String[]> allLines = readAllLines(true);

        return foundLineByKey(allLines, key);
    }

    /**
     * Esborra un línia d'un fitxer donat la clau d'aquesta
     *
     * @param key      l'identificador de la línia a buscar
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void removeLinebyKey(String key) throws PersistanceException {
        List<String[]> allLines = readAllLines(true);

        int rowNumber = getLineNumberByKey(allLines, key);
        allLines.remove(rowNumber);

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
        List<String[]> allLines = readAllLines(true);

        int rowNumber = getLineNumberByKey(allLines, key);
        allLines.set(rowNumber, line);

        writeAllLines(allLines);
    }

}
