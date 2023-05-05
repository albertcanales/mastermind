package persistance;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import persistance.exceptions.InvalidCSVException;
import persistance.exceptions.InvalidPermissionsException;
import persistance.exceptions.PersistanceException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestorUsuaris {

    private static final String usersPath = "users/users.csv";


    private enum Header {
        USERNAME(0),
        NAME(1),
        PASSWORD(2);

        private final int number;

        /**
         * Constructor a partir de l'enter que representa el color
         *
         * @param number és el valor que representa el nivell de dificultat
         */
        Header(int number) {
            this.number = number;
        }

    }

    public void writetest () throws java.io.IOException {
        FileWriter filewriter = new FileWriter("./test.csv"); //posar algo+concatenar
        CSVWriter csvWriter = new CSVWriter(filewriter);



        String[] header = { "username", "name", "hash" };
        csvWriter.writeNext(header);
        csvWriter.close();
    }

    public Boolean existsUser(String basePath, String username) throws PersistanceException {
        String[] row;
        CSVReader csvReader = null;
        String fileName = basePath + usersPath;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException e) { //encara no hem creat el fitxer
            return false;
        }


        try {
            while ((row = csvReader.readNext()) != null) {
                if (row[Header.USERNAME.number].equals(username)) return true;
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

        return false;

    }

    public void registerUser(String basePath, String username, String name, String password) throws PersistanceException{
        String fileName = basePath + usersPath;

        if (!existsUser(basePath, username)) {
            CSVWriter csvWriter = null;
            try {
                csvWriter = new CSVWriter(new FileWriter(fileName, true));
            } catch (IOException e) {
                throw new InvalidPermissionsException(fileName);
            }

            csvWriter.writeNext(new String[]{username, name, password});

            try {
                csvWriter.close();
            } catch (IOException e) {
                throw new InvalidPermissionsException(fileName);
            }
        }
    }

    public static void main(String[] args) throws PersistanceException {
        GestorUsuaris gestor = new GestorUsuaris();
        //gestor.writetest();
        //gestor.existsUser("./", "arnau");
        if (gestor.existsUser("./", "arnau")) System.out.println("exists");
        //gestor.registerUser("./", "arnau", "Arnau", "securepass");
        //gestor.registerUser("./", "sussy", "aa", "securepass");
    }
}
