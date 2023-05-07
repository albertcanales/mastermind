package persistance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import persistance.exceptions.ElementNotFoundException;
import persistance.exceptions.InvalidCSVException;
import persistance.exceptions.InvalidFileAccess;
import persistance.exceptions.PersistanceException;

import java.io.*;
import java.util.List;

/**
 * Gestor per crear, consultar, modificar i esborrar fitxers CSV
 *
 * @author Arnau Valls Fusté
 */
public class GestorCSV {
    private static final int headerPos = 0; //El header sempre està a la posició 0 d'un CSV

    public void createFileandDir(String fileName, String[] header) throws PersistanceException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();

        try {
            if (file.createNewFile()) addLine(fileName, header); //Si l'acabem de crear, posem el header
        } catch (IOException e) {
            throw new InvalidFileAccess(fileName);
        }

    }

    public void addLine(String fileName, String[] line) throws PersistanceException {
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

    private static Integer getRowNumberOf(List<String[]> allLines, String key, Integer keyPos, String fileName) throws PersistanceException {
        int i = 0;
        while (i < allLines.size()) {
            if (allLines.get(i)[keyPos].equals(key)) return i;
            ++i;
        }
        throw new ElementNotFoundException(key, fileName);
    }

    public List<String[]> getLines(String fileName) throws PersistanceException {
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

        allLines.remove(headerPos); //Treiem el Header
        return allLines;
    }

    public String[] getLinebyKey(String fileName, String key, Integer keyPos) throws PersistanceException {
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

        allLines.remove(headerPos); //Treiem el Header per no recorre'l
        int rowNumber = getRowNumberOf(allLines, key, keyPos, fileName);
        return allLines.get(rowNumber);
    }

    public void removeLinebyKey(String fileName, String key, Integer keyPos) throws PersistanceException {
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



        String[] header = allLines.get(headerPos);
        allLines.remove(headerPos); //Treiem el Header per no recorre'l


        int rowNumber = getRowNumberOf(allLines, key, keyPos, fileName);
        allLines.remove(rowNumber); //TODO: Assumim que Domini ens garanteix que la key existeix

        allLines.add(headerPos, header); //I el tornem a posar

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

}
