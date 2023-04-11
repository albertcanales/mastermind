package domain;

import java.util.Scanner;

import static java.lang.System.exit;

public class PartidaDriver {

    static ControladorPartida cp;

    private static void showUsage() {
        System.out.println("Llista de comandes:");
        System.out.println("     0 | sortir");
        System.out.println("     1 | ajuda");
        System.out.println("     2 | novaPartidaMaker");
        System.out.println("     3 | novaPartidaBreaker");
        System.out.println("     4 | carregarPartidaMaker");
        System.out.println("     5 | carregarPartidaBreaker");
        System.out.println("     6 | validarSequencia");
        System.out.println("     7 | botSolve");
        System.out.println("     8 | getTemps");
        System.out.println("     9 | addTemps");
        System.out.println("    10 | getNivellDificultat");
        System.out.println("    11 | getSequenciaSolucio");
        System.out.println("    12 | getIntents");
        System.out.println("    13 | getFeedbacks");
        System.out.println("    14 | isPartidaGuanyada");
        System.out.println("    15 | isPartidaPerduda");
        System.out.println("    16 | isPartidaAcabada");
        System.out.println("    17 | setBola");
        System.out.println("Cada comanda es pot executar pel seu nombre o pel seu nom.");
        System.out.println("La comanda 'ajuda (1)' mostra de nou aquesta informació");
    }

    private static void testNovaPartidaMaker() {

    }

    private static void testNovaPartidaBreaker() {

    }

    private static void testCarregarPartidaMaker() {

    }

    private static void testCarregarPartidaBreaker() {

    }

    private static void testValidarSequencia() {

    }

    private static void testBotSolve() {

    }

    private static void testGetTemps() {

    }

    private static void testAddTemps() {

    }

    private static void testGetNivellDificultat() {

    }

    private static void testGetSequenciaSolucio() {

    }

    private static void testGetIntents() {

    }

    private static void testGetFeedbacks() {

    }

    private static void testIsPartidaGuanyada() {

    }

    private static void testIsPartidaPerduda() {

    }

    private static void testIsPartidaAcabada() {

    }

    private static void testSetBola() {

    }

    public static void main(String[] args) {
        cp = new ControladorPartida();
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
                case "novaPartidaMaker":
                    testNovaPartidaMaker();
                    break;
                case "3":
                case "novaPartidaBreaker":
                    testNovaPartidaBreaker();
                    break;
                case "4":
                case "carregarPartidaMaker":
                    testCarregarPartidaMaker();
                    break;
                case "5":
                case "carregarPartidaBreaker":
                    testCarregarPartidaBreaker();
                    break;
                case "6":
                case "validarSequencia":
                    testValidarSequencia();
                    break;
                case "7":
                case "botSolve":
                    testBotSolve();
                    break;
                case "8":
                case "getTemps":
                    testGetTemps();
                    break;
                case "9":
                case "addTemps":
                    testAddTemps();
                    break;
                case "10":
                case "getNivellDificultat":
                    testGetNivellDificultat();
                    break;
                case "11":
                case "getSequenciaSolucio":
                    testGetSequenciaSolucio();
                    break;
                case "12":
                case "getIntents":
                    testGetIntents();
                    break;
                case "13":
                case "getFeedbacks":
                    testGetFeedbacks();
                    break;
                case "14":
                case "isPartidaGuanyuada":
                    testIsPartidaGuanyada();
                    break;
                case "15":
                case "isPartidaPerduda":
                    testIsPartidaPerduda();
                    break;
                case "16":
                case "isPartidaAcabada":
                    testIsPartidaAcabada();
                    break;
                case "17":
                case "setBola":
                    testSetBola();
                    break;
                default:
                    System.out.println("No es coneix la comanda, s'ignora.");
            }
        }
    }

}
