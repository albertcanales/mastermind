package domain;

import java.util.Scanner;

import static java.lang.System.exit;

public class DominiDriver {

    static ControladorDomini cd;

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
        System.out.println("Cada comanda es pot excutar pel seu nombre o pel seu nom.");
        System.out.println("La comanda 'ajuda (1)' mostra de nou aquesta informació");
    }

    private static void testConstructor() {

    }

    private static void testExistsUser() {

    }

    private static void testLoginUser() {

    }

    private static void testRegisterUser() {

    }

    private static void testNovaPartidaMaker() {

    }

    private static void testNovaPartidaBreaker() {

    }

    private static void testCarregarPartida() {

    }

    private static void testGetRanquing() {

    }

    private static void testGetPersonalRecord() {

    }

    private static void testGetTimePlayed() {

    }

    private static void testGetWinLost() {

    }

    private static void testGetWinstreak() {

    }

    private static void testGetAverageAsBreaker() {

    }

    private static void testGetAverageAsMaker() {

    }

    private static void testGetSolucio() {

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

    public static void main(String[] args) {
        cd = new ControladorDomini();
        Scanner in = new Scanner(System.in);
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
                case "12":
                case "getWinLost":
                    testGetWinLost();
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
