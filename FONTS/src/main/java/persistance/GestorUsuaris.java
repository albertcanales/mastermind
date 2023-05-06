package persistance;

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

    }
    public GestorUsuaris(String basePath) throws PersistanceException {
        this.absoluteUsersPath = basePath + relativeUsersPath;
        createFileandDir(absoluteUsersPath);
    }

    public Boolean existsUser(String username) throws PersistanceException {
        return username.equals(getLineElement(absoluteUsersPath, username, Header.USERNAME.number));
    }

    public void registerUser(String username, String name, String password) throws PersistanceException {
        if (!existsUser(username)) { //TODO: Maybe not needed
            addLine(absoluteUsersPath, new String[]{username, name, password});
        }
    }

    public String getPasswordHash(String username) throws PersistanceException {
        return getLineElement(absoluteUsersPath, username, Header.PASSWORD.number);
    }

    public String getUserName(String username) throws PersistanceException {
        return getLineElement(absoluteUsersPath, username, Header.NAME.number);
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

    }
}
