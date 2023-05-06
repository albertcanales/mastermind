package persistance;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import persistance.exceptions.InvalidCSVException;
import persistance.exceptions.InvalidPermissionsException;
import persistance.exceptions.PersistanceException;

import java.io.*;
import java.util.List;

public class GestorCSV {
    private Integer getRowOf(String fileName, String key, CSVReader csvReader) throws PersistanceException {
        String[] row;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException e) { //encara no hem creat el fitxer
            return null;
        }

        Integer i = 0;
        try {
            while ((row = csvReader.readNext()) != null) {
                if (row[0].equals(key)) return i; //columna 0 és key (si en tenim)
                i++;
            }
        } catch (CsvValidationException e) {
            throw new InvalidCSVException(fileName);
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

        return -1;
    }
    public void createFileandDir(String fileName) throws InvalidPermissionsException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

    }

    public String getLineElement(String fileName, String key, Integer column) throws PersistanceException {//TODO: canviar getLine
        String[] row;
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException e) {
            throw new InvalidPermissionsException(fileName);
        }

        try {
            while ((row = csvReader.readNext()) != null) {
                if (row[0].equals(key)) return row[column]; //columna 0 és la key sempre
            }
        } catch (CsvValidationException e) {
            throw new InvalidCSVException(fileName);
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

        try {
            csvReader.close();
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

        return null;
    }

    public void addLine(String fileName, String[] line) throws PersistanceException {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

        csvWriter.writeNext(line);

        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }

    }

    /*
    public void deleteLine(String fileName, String id) throws PersistanceException {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException e) {
            throw new InvalidPermissionsException(fileName);
        }
        List<String[]> allLines = null;
        try {
            allLines = csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new InvalidPermissionsException(fileName);
        }
        try {
            csvReader.close();
        } catch (IOException e) {
            throw new InvalidPermissionsException(fileName);
        }
        allLines.remove(rowNumber);
        FileWriter sw = new FileWriter(filelocation);
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);
        writer.close();

    }
    */
}
