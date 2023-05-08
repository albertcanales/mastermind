package persistance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import persistance.exceptions.InvalidCSVException;
import persistance.exceptions.InvalidFileAccess;
import persistance.exceptions.LineNotFoundException;
import persistance.exceptions.PersistanceException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Gestor per crear, consultar, modificar i esborrar fitxers CSV
 *
 * @author Arnau Valls Fusté
 */
public class GestorCSV {
    private static final int headerPos = 0; //El header sempre està a la posició 0 d'un CSV

    /**
     * Crea el fitxer donat i el seu directori pare si no existeix
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param header   un Array d'Strings amb el header a col·locar al fitxer
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void createFileandDir(String fileName, String[] header) throws InvalidFileAccess {
        File file = new File(fileName);
        file.getParentFile().mkdirs();

        try {
            if (file.createNewFile()) addLine(fileName, header); //Si l'acabem de crear, posem el header
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

    }

    /**
     * Esborra el fitxer donat si existeix
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void deleteFile(String fileName) throws InvalidFileAccess {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e)
        {
            throw new InvalidFileAccess(fileName);
        }
    }

    /**
     * Afegeix la línia donada al final del fitxer
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param line     un Array d'Strings amb la línia a col·locar al fitxer
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    public void addLine(String fileName, String[] line) throws InvalidFileAccess {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(fileName, true)); //fem append
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

        csvWriter.writeNext(line);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

    }

    /**
     * Troba la línia amb la primera ocurrència de key a allLines
     *
     * @param allLines una llista d'String amb totes les línies
     * @param key      l'identificador de la línia a buscar
     * @param keyPos   un enter amb la posició de la key
     * @return un Enter amb el número de la línia
     */
    private static Integer getLineNumberByKey(List<String[]> allLines, String key, Integer keyPos) {
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
     * @param fileName un String amb el nom del fitxer en format absolut
     * @return una llista d'Strings amb totes les línies d'un fitxer
     * @throws InvalidFileAccess   si no es pot accedir al fitxer
     * @throws InvalidCSVException si el CSV no té un format legal
     */
    private List<String[]> readAllLines(String fileName) throws InvalidFileAccess, InvalidCSVException {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException e) {
            throw new InvalidFileAccess(fileName);
        }
        List<String[]> allLines;
        try {
            allLines = csvReader.readAll();
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        } catch (CsvException e) {
            throw new InvalidCSVException(fileName);
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

        return allLines;
    }

    /**
     * Escriu totes les línies d'un fitxer donat una llista d'Strings
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param allLines una llista d'Strings amb totes les línies d'un fitxer
     * @throws InvalidFileAccess si no es pot accedir al fitxer
     */
    private void writeAllLines(String fileName, List<String[]> allLines) throws InvalidFileAccess {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(fileName, false));
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }
        csvWriter.writeAll(allLines);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }
    }

    /**
     * Retorna totes les línies d'un fitxer sense el header
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @return una llista d'Strings amb totes les línies d'un fitxer
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public List<String[]> getLines(String fileName) throws PersistanceException {
        List<String[]> allLines = readAllLines(fileName);

        allLines.remove(headerPos); //Treiem el Header
        return allLines;
    }

    /**
     * Retorna un línia d'un fitxer donat la clau d'aquesta
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param key      l'identificador de la línia a buscar
     * @param keyPos   un enter amb la posició de la key
     * @return un Array d'Strings amb la línia corresponent
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public String[] getLinebyKey(String fileName, String key, Integer keyPos) throws PersistanceException {
        List<String[]> allLines = readAllLines(fileName);

        allLines.remove(headerPos); //Treiem el Header per no recorre'l
        int rowNumber = getLineNumberByKey(allLines, key, keyPos);
        if (rowNumber == -1) throw new LineNotFoundException(key, fileName);
        return allLines.get(rowNumber);
    }

    /**
     * Retorna un booleà cert o fals depenent de si la línia existeix
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param key      l'identificador de la línia a buscar
     * @param keyPos   un enter amb la posició de la key
     * @return un booleà cert o fals depenent de si la línia existeix
     * @throws PersistanceException si no es pot accedir al fitxer o si el CSV no té un format legal
     */
    public Boolean existsLinebyKey(String fileName, String key, Integer keyPos) throws PersistanceException {
        List<String[]> allLines = readAllLines(fileName);

        allLines.remove(headerPos); //Treiem el Header per no recorre'l

        int rowNumber = getLineNumberByKey(allLines, key, keyPos);
        return rowNumber != -1;

    }

    /**
     * Esborra un línia d'un fitxer donat la clau d'aquesta
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param key      l'identificador de la línia a buscar
     * @param keyPos   un enter amb la posició de la key
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void removeLinebyKey(String fileName, String key, Integer keyPos) throws PersistanceException {
        List<String[]> allLines = readAllLines(fileName);

        String[] header = allLines.get(headerPos);
        allLines.remove(headerPos); //Treiem el Header per no recorre'l


        int rowNumber = getLineNumberByKey(allLines, key, keyPos);
        if (rowNumber == -1) throw new LineNotFoundException(key, fileName);
        allLines.remove(rowNumber);

        allLines.add(headerPos, header); //I el tornem a posar

        writeAllLines(fileName, allLines);

    }

    /**
     * Posa el contingut a una línia donat la seva key
     *
     * @param fileName un String amb el nom del fitxer en format absolut
     * @param key      l'identificador de la línia a buscar
     * @param keyPos   un enter amb la posició de la key
     * @param line     line un Array d'Strings amb la línia a col·locar al fitxer
     * @throws PersistanceException si no es pot accedir al fitxer, si el CSV no té un format legal o la línia no existeix
     */
    public void setLinebyKey(String fileName, String key, Integer keyPos, String[] line) throws PersistanceException {
        List<String[]> allLines = readAllLines(fileName);

        String[] header = allLines.get(headerPos);
        allLines.remove(headerPos); //Treiem el Header per no recorre'l


        int rowNumber = getLineNumberByKey(allLines, key, keyPos);
        if (rowNumber == -1) throw new LineNotFoundException(key, fileName);
        allLines.set(rowNumber, line);

        allLines.add(headerPos, header); //I el tornem a posar

        writeAllLines(fileName, allLines);

    }

}
