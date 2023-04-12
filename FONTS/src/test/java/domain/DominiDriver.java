package domain;

import domain.exceptions.DomainException;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Driver del ControladorDomini
 * @author Albert Canales
 */
public class DominiDriver extends ControladorDriver {

    private static ControladorDomini cd;

    private static void showUsage() {
        System.out.println("Llista de comandes:");
        System.out.println("     0 | sortir");
        System.out.println("     1 | ajuda");
        System.out.println("     2 | constructor");
        System.out.println("     3 | existsUser");
        System.out.println("     4 | loginUser");
        System.out.println("     5 | registerUser");
        System.out.println("     6 | novaPartidaMaker");
        System.out.println("     7 | novaPartidaBreaker");
        System.out.println("     8 | carregarPartida");
        System.out.println("     9 | getRanquing");
        System.out.println("    10 | getPersonalRecord");
        System.out.println("    11 | getTimePlayed");
        System.out.println("    12 | getWinLost");
        System.out.println("    13 | getWinstreak");
        System.out.println("    14 | getAverageAsBreaker");
        System.out.println("    15 | getAverageAsMaker");
        System.out.println("    16 | getSolucio");
        System.out.println("    17 | getIntents");
        System.out.println("    18 | getFeedbacks");
        System.out.println("    19 | setBola");
        System.out.println("    20 | validarSequencia");
        System.out.println("    21 | botSolve");
        System.out.println("Cada comanda es pot executar pel seu nombre o pel seu nom.");
        System.out.println("La comanda 'ajuda (1)' mostra de nou aquesta informació");
    }

    private static void testConstructor() {
        System.out.println("Testing constructor...");

        cd = new ControladorDomini();
    }

    private static void testExistsUser() {
        System.out.println("Testing loginUser...");

        System.out.print("Enter un username: ");
        String username = in.nextLine();

        if (cd.existsUser(username))
            System.out.printf("User %s does exists%n", username);
        else
            System.out.printf("User %s does not exists%n", username);
    }

    private static void testLoginUser() throws DomainException {
        System.out.println("Testing loginUser...");

        System.out.print("Enter un username: ");
        String username = in.nextLine();
        if (!cd.existsUser(username)) {
            System.out.println("Error: User does not exists");
            return;
        }

        System.out.print("Enter the password: ");
        String password = in.nextLine();

        if(cd.loginUser(username, password))
            System.out.println("Successfully logged in.");
        else
            System.out.println("Error: Wrong password");
    }

    private static void testRegisterUser() throws DomainException {
        System.out.println("Testing registerUser...");

        // TODO Valid username, name and password. Waiting for implementation...
        System.out.print("Enter un username: ");
        String username = in.nextLine();
        if (cd.existsUser(username)) {
            System.out.println("Error: User already exists");
            return;
        }

        System.out.print("Enter a name: ");
        String name = in.nextLine();
        System.out.print("Enter a password: ");
        String password = in.nextLine();

        cd.registerUser(username, name, password);
    }

    private static void testNovaPartidaMaker() {

    }

    private static void testNovaPartidaBreaker() {

    }

    private static void testCarregarPartida() {

    }

    private static void testGetRanquing() {

    }

    private static void testGetPersonalRecord() throws DomainException {
        System.out.println("Testing getPersonalRecord...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> personalRecords = cd.getPersonalRecord();
        for (int i = 0; i < personalRecords.size(); i++) {
            System.out.printf("El PR per la dificultat %d és %d%n", i+1, personalRecords.get(i));
        }
    }

    private static void testGetTimePlayed() throws DomainException {
        System.out.println("Testing getTimePlayed...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Long> timePlayed = cd.getTimePlayed();
        for (int i = 0; i < timePlayed.size(); i++) {
            System.out.printf("El temps jugat en la dificultat %d és %d%n", i+1, timePlayed.get(i));
        }
    }

    private static void testGetWonGames() throws DomainException {
        System.out.println("Testing getWonGames...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> wonGames = cd.getWonGames();
        for (int i = 0; i < wonGames.size(); i++) {
            System.out.printf("S'han guanyat %d partides com a breaker en la dificultat %d%n", wonGames.get(i), i+1);
        }
    }

    private static void testGetLostGames() throws DomainException {
        System.out.println("Testing getLostGames...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> lostGames = cd.getLostGames();
        for (int i = 0; i < lostGames.size(); i++) {
            System.out.printf("S'han perdut %d partides com a breaker en la dificultat %d%n", lostGames.get(i), i+1);
        }
    }


    private static void testGetWinstreak() throws DomainException {
        System.out.println("Testing getWinstreak...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> winstreak = cd.getWinstreak();
        for (int i = 0; i < winstreak.size(); i++) {
            System.out.printf("La màxima ratxa de victòries és %d en la dificultat %d%n", winstreak.get(i), i+1);
        }
    }

    private static void testGetAverageAsBreaker() throws DomainException {
        System.out.println("Testing getAverageAsBreaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Double> averageAsBreaker = cd.getAverageAsBreaker();
        for (int i = 0; i < averageAsBreaker.size(); i++) {
            System.out.printf("La mitjana d'intents en la dificultat %d és de %.2f%n", i+1, averageAsBreaker.get(i));
        }
    }

    private static void testGetAverageAsMaker() throws DomainException {
        System.out.println("Testing getAverageAsMaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Double> averageAsMaker = cd.getAverageAsMaker();
        for (int i = 0; i < averageAsMaker.size(); i++) {
            System.out.printf("La mitjana d'intents en la dificultat %d és de %.2f%n", i+1, averageAsMaker.get(i));
        }
    }

    private static void testGetSolucio() throws DomainException {

    }

    private static void testGetIntents() {

    }

    private static void testGetFeedbacks() {

    }

    private static void testSetBola() {

    }

    private static void testValidarSequencia() {

    }

    private static void testBotSolve() {

    }

    // TODO Fer main com l'altre
    public static void main(String[] args) throws DomainException {
        cd = new ControladorDomini();
        in = new Scanner(System.in);
        System.out.println("A continuació es mostren les comandes possibles:");
        showUsage();
        while(true) {
            System.out.print("Escolleix una comanda: ");
            String cmd = in.nextLine();
            switch (cmd) {
                case "0":
                case "sortir":
                    exit(0);
                    break;
                case "1":
                case "ajuda":
                    showUsage();
                    break;
                case "2":
                case "constructor":
                    testConstructor();
                    break;
                case "3":
                case "existsUser":
                    testExistsUser();
                    break;
                case "4":
                case "loginUser":
                    testLoginUser();
                    break;
                case "5":
                case "registerUser":
                    testRegisterUser();
                    break;
                case "6":
                case "novaPartidaMaker":
                    testNovaPartidaMaker();
                    break;
                case "7":
                case "novaPartidaBreaker":
                    testNovaPartidaBreaker();
                    break;
                case "8":
                case "carregarPartida":
                    testCarregarPartida();
                    break;
                case "9":
                case "getRanquing":
                    testGetRanquing();
                    break;
                case "10":
                case "getPersonalRecord":
                    testGetPersonalRecord();
                    break;
                case "11":
                case "getTimePlayed":
                    testGetTimePlayed();
                    break;
                case "getWonGames":
                    testGetWonGames();
                    break;
                case "getLostGames":
                    testGetWonGames();
                    break;
                case "13":
                case "getWinstreak":
                    testGetWinstreak();
                    break;
                case "14":
                case "getAverageAsBreaker":
                    testGetAverageAsBreaker();
                    break;
                case "15":
                case "getAverageAsMaker":
                    testGetAverageAsMaker();
                    break;
                case "16":
                case "getSolucio":
                    testGetSolucio();
                    break;
                case "17":
                case "getIntents":
                    testGetIntents();
                    break;
                case "18":
                case "getFeedbacks":
                    testGetFeedbacks();
                    break;
                case "19":
                case "setBola":
                    testSetBola();
                    break;
                case "20":
                case "validarSequencia":
                    testValidarSequencia();
                    break;
                case "21":
                case "botSolve":
                    testBotSolve();
                    break;
                default:
                    System.out.println("No es coneix la comanda, s'ignora.");
            }
        }
    }

}
