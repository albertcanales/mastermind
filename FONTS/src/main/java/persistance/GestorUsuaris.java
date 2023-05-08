package persistance;

import persistance.exceptions.LineNotFoundException;
import persistance.exceptions.InvalidCSVException;
import persistance.exceptions.PersistanceException;


/**
 * Gestor dels Usuaris guardats a users/users.csv
 *
 * @author Arnau Valls Fusté
 */
public class GestorUsuaris extends GestorCSV {

    private static final String relativeUsersPath = "users/users.csv";
    private final String absoluteUsersPath;

    public static void main(String[] args) throws PersistanceException {
        GestorUsuaris gestor = new GestorUsuaris("./");

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
        //gestor.deleteFile(gestor.absoluteUsersPath);

    }
    GestorUsuaris(String basePath) throws PersistanceException {
        this.absoluteUsersPath = basePath + relativeUsersPath;
        createFileandDir(absoluteUsersPath, Header.getHeader());
    }

    public Boolean existsUser(String username) throws PersistanceException {
        return existsLinebyKey(absoluteUsersPath, username, Header.USERNAME.number);
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        if (!existsUser(username)) { //TODO: Maybe not needed si Domini ens ho garanteix
            addLine(absoluteUsersPath, new String[]{username, name, password});
        }
        if (!existsLinebyKey(absoluteUsersPath, username, Header.USERNAME.number))
        {
            addLine(absoluteUsersPath, new String[]{username, name, password});
        }
        //throws??

    }

    public String getPasswordHash(String username) throws PersistanceException {
        if (existsLinebyKey(absoluteUsersPath, username, Header.USERNAME.number))
        {
            return getLinebyKey(absoluteUsersPath, username, Header.USERNAME.number)[Header.PASSWORD.number];
        }
        return null;

    }

    public String getUserName(String username) throws PersistanceException {
        try {
            return getLinebyKey(absoluteUsersPath, username, Header.USERNAME.number)[Header.NAME.number];
        } catch (LineNotFoundException e) {
            throw new InvalidCSVException(absoluteUsersPath); //TODO: Assumim que Domini ens garanteix que l'usuari existeix, llavors vol dir que l'arxiu s'ha corromput
        }
    }

    public void esborrarUsuari(String username) throws PersistanceException {
        if (existsUser(username)) { //TODO: Maybe not needed si Domini ens ho garanteix
            try {
                removeLinebyKey(absoluteUsersPath, username, Header.USERNAME.number);
            } catch (LineNotFoundException e) {
                throw new InvalidCSVException(absoluteUsersPath); //TODO: Assumim que Domini ens garanteix que l'usuari existeix, llavors vol dir que l'arxiu s'ha corromput
            }
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
            String[] header = new String[values().length + 1];

            for(int i = 0; i < values().length; ++i){
                header[i] = values()[i].name();

            }
            return header;
        }
    }
}
