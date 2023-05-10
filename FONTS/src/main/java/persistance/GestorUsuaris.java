package persistance;

import exceptions.persistance.LineAlreadyExistsException;
import exceptions.persistance.LineNotFoundException;
import exceptions.persistance.PersistanceException;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorUsuaris {

    private static final String relativeUsersPath = "users/users.csv";

    private final GestorCSVFile csvFile;

    public static void main(String[] args) throws PersistanceException {
        GestorUsuaris gestor = new GestorUsuaris("./db/");

        gestor.registerUser("arnau", "Arnau", "securepass");
        gestor.registerUser("sussy", "Amongus", "securepass2");
        gestor.registerUser("morethansus", "AmogOS", "securepass3");

        System.out.println(String.format("Name for \"arnau:\" %s", gestor.getUserName("arnau")));
        System.out.println(String.format("Name for \"sussy:\" %s", gestor.getUserName("sussy")));
        System.out.println(String.format("Name for \"morethansus:\" %s", gestor.getUserName("morethansus")));

        System.out.println(String.format("Password for \"arnau:\" %s", gestor.getPasswordHash("arnau")));
        System.out.println(String.format("Password for \"sussy:\" %s", gestor.getPasswordHash("sussy")));
        System.out.println(String.format("Password for \"morethansus:\" %s", gestor.getPasswordHash("morethansus")));

        gestor.esborrarUsuari("sussy");
        System.out.println(String.format("Exists user \"sussy:\" %s", gestor.existsUser("sussy")));


    }
    GestorUsuaris(String basePath) throws PersistanceException {
        csvFile = new GestorCSVFile(basePath + relativeUsersPath, Header.getHeader(), Header.USERNAME.number);

    }

    public Boolean existsUser(String username) throws PersistanceException {
        return csvFile.existsLinebyKey(username);
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        if (!csvFile.existsLinebyKey(username))
        {
            csvFile.addLine(new String[]{username, name, password});
        }
        else {
            throw new LineAlreadyExistsException(username, relativeUsersPath);
        }

    }

    public String getPasswordHash(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[Header.PASSWORD.number];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public String getUserName(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            return csvFile.getLinebyKey(username)[Header.NAME.number];
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    public void esborrarUsuari(String username) throws PersistanceException {
        if (csvFile.existsLinebyKey(username)) {
            csvFile.removeLinebyKey(username);
        }
        else {
            throw new LineNotFoundException(username, relativeUsersPath);
        }
    }

    /**
     * Enum que serveix per definir el Header (les columnes) del CSV corresponent
     */
    private enum Header {
        USERNAME(0),
        NAME(1),
        PASSWORD(2);

        private final int number;

        /**
         * Constructor del Header d'Usuaris
         *
         * @param number és el valor que representa la columna
         */
        Header(int number) {
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
