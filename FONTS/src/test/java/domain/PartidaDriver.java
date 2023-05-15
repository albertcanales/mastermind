package domain;

import exceptions.domain.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Driver del ControladorPartida
 * @author Albert Canales
 */
public class PartidaDriver extends ControladorDriver {

    private static ControladorPartida cp;

    static List<List<Integer>> scanIntents() {
        System.out.println("Enter els intents de la partida.");
        System.out.println("S'aniran entrant els intents un a un, de més 'antic' a més recent");
        System.out.println("Per acabar d'inserir intents, escriu la lletra q");

        List<List<Integer>> intents = new ArrayList<>();
        List<Integer> sequencia = new ArrayList<>();

        while(true) {
            System.out.print("Enter the next bola (negative to stop this sequence): ");
            if(in.hasNextInt()) {
                int num = in.nextInt();
                if(num > -1) {
                    sequencia.add(num);
                }
                else {
                    intents.add(sequencia);
                    sequencia = new ArrayList<>();
                }
            }
            else {
                String s = in.nextLine();
                if(Objects.equals(s, "q"))
                    break;
                else {
                    System.out.println("Is not a number neither the exiting command (q). Repeat");
                }
            }
        }
        if(sequencia.size() > 0)
            intents.add(sequencia);

        return intents;
    }

    static List<List<Integer>> scanFeedbacks() {
        System.out.println("Enter els feedbacks de la partida.");
        System.out.println("S'aniran entrant els feedbacks un a un, de més 'antic' a més recent");
        System.out.println("Per acabar d'inserir feedbacks, escriu la lletra q");

        List<List<Integer>> feedbacks = new ArrayList<>();
        List<Integer> sequencia = new ArrayList<>();

        while(true) {
            System.out.print("Enter the next bola (negative to stop this sequence): ");
            if(in.hasNextInt()) {
                int num = in.nextInt();
                if(num > -1) {
                    sequencia.add(num);
                }
                else {
                    feedbacks.add(sequencia);
                    sequencia = new ArrayList<>();
                }
            }
            else {
                String s = in.nextLine();
                if(Objects.equals(s, "q"))
                    break;
                else {
                    System.out.println("Is not a number neither the exiting command (q). Repeat");
                }
            }
        }
        if(sequencia.size() > 0)
            feedbacks.add(sequencia);

        return feedbacks;
    }

    private static void showUsage() {
        System.out.println("Llista de comandes:");
        System.out.println("     0 | sortir");
        System.out.println("     1 | ajuda");
        System.out.println("     2 | isPartidaPresent");
        System.out.println("     3 | novaPartidaMaker");
        System.out.println("     4 | novaPartidaBreaker");
        System.out.println("     5 | carregarPartidaMaker");
        System.out.println("     6 | carregarPartidaBreaker");
        System.out.println("     7 | validarSequencia");
        System.out.println("     8 | botSolve");
        System.out.println("     9 | getTempsMillis");
        System.out.println("    10 | addTempsMillis");
        System.out.println("    11 | getNumBoles");
        System.out.println("    12 | getNivellDificultat");
        System.out.println("    13 | getAlgorisme");
        System.out.println("    14 | getSequenciaSolucio");
        System.out.println("    15 | getNumIntents");
        System.out.println("    16 | getIntents");
        System.out.println("    17 | getFeedbacks");
        System.out.println("    18 | isJugadorBreaker");
        System.out.println("    19 | isPartidaGuanyada");
        System.out.println("    20 | isPartidaPerduda");
        System.out.println("    21 | isPartidaAcabada");
        System.out.println("    22 | isUltimIntentPle");
        System.out.println("    23 | isValidIntentsFeedback");
        System.out.println("    24 | setBola");
        System.out.println("    25 | sortirPartida");
        System.out.println("Cada comanda es pot executar pel seu nombre o pel seu nom.");
    }

    private static void testIsPartidaPresent() {
        System.out.println("Testing isPartidaPresent...");

        if(cp.isPartidaPresent())
            System.out.println("S'està jugant una partida");
        else
            System.out.println("No s'està jugant una partida");
    }

    private static void testNovaPartidaMaker() throws DomainException {
        System.out.println("Testing novaPartidaMaker...");

        List<Integer> solution = scanSolution();
        Integer algorisme = scanAlgorisme();
        cp.novaPartidaMaker(solution, algorisme);
    }

    private static void testNovaPartidaBreaker() throws DomainException {
        System.out.println("Testing novaPartidaBreaker...");

        Integer dificultat = scanNivellDificultat();
        cp.novaPartidaBreaker(dificultat);
    }

    private static void testCarregarPartidaMaker() throws DomainException {
        System.out.println("Testing carregarPartidaMaker...");

        List<Integer> solution = scanSolution();
        Integer algorisme = scanAlgorisme();

        List<List<Integer>> intents = scanIntents();
        List<List<Integer>> feedbacks = scanFeedbacks();

        if(!cp.isValidIntentsFeedbacks(intents, feedbacks)) {
            System.out.println("Els intents o el feedback donats no són vàlids");
            return;
        }

        cp.carregarPartidaMaker(algorisme, intents, feedbacks, solution);

    }

    private static void testCarregarPartidaBreaker() throws DomainException {
        System.out.println("Testing carregarPartidaBreaker...");

        List<Integer> solution = scanSolution();
        Integer nivellDificultat = scanNivellDificultat();

        List<List<Integer>> intents = scanIntents();
        List<List<Integer>> feedbacks = scanFeedbacks();

        if(!cp.isValidIntentsFeedbacks(intents, feedbacks)) {
            System.out.println("Els intents o el feedback donats no són vàlids");
            return;
        }

        System.out.print("Enter el temps transcorregut en la partida: ");
        Long temps = scanLong();
        if(temps < 0) {
            System.out.println("El temps no pot ser negatiu.");
            System.out.print("Enter el temps transcorregut en la partida: ");
            temps = scanLong();
        }

        cp.carregarPartidaBreaker(nivellDificultat, intents, feedbacks, solution, temps);
    }

    private static void testValidarSequencia() throws DomainException {
        System.out.println("Testing validarSequencia...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el bot és el breaker");
            return;
        }
        if(cp.isPartidaAcabada()) {
            System.out.println("Error: La partida ja està acabada");
            return;
        }
        if(!cp.isUltimIntentPle()) {
            System.out.println("Error: Encara hi ha boles per assignar a l'últim intent");
            return;
        }

        cp.validarSequencia();
    }

    private static void testBotSolve() throws DomainException {
        System.out.println("Testing botSolve...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador és el breaker");
            return;
        }
        if(cp.isPartidaAcabada()) {
            System.out.println("Error: La partida ja està acabada");
            return;
        }

        cp.botSolve();
    }

    private static void testGetTempsMillis() throws DomainException {
        System.out.println("Testing getTempsMillis...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador és el maker");
            return;
        }
        System.out.printf("El temps transcorregut és de %d ms.%n", cp.getTempsMillis());
    }

    private static void testAddTempsMillis() throws DomainException {
        System.out.println("Testing addTempsMillis...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador és el maker");
            return;
        }
        System.out.print("Enter a temps a afegir: ");
        Long afegit = scanLong();
        System.out.printf("S'ha llegit el valor %d%n", afegit);
        cp.addTempsMillis(afegit);
    }

    private static void testGetNumBoles() {
        System.out.println("Testing getNumBoles...");

        System.out.printf("El nombre de boles per cada seqüència és %d%n", ControladorPartida.getNumBoles());
    }

    private static void testGetNivellDificultat() throws DomainException {
        System.out.println("Testing getNivellDificultat...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador no és breaker");
            return;
        }

        System.out.printf("El nivell de dificultat de la partida %d.%n", cp.getNivellDificultat());
    }

    private static void testGetAlgorisme() throws DomainException {
        System.out.println("Testing getAlgorisme...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(cp.isJugadorBreaker()) {
            System.out.println("Error: El bot no és el breaker de la partida");
            return;
        }

        System.out.printf("L'algorisme del bot de la partida és el %d%n", cp.getAlgorisme());
    }

    private static void testGetSequenciaSolucio() throws DomainException {
        System.out.println("Testing getSequenciaSolucio...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        System.out.print("La solució de la partida és: ");
        printSequence(cp.getSequenciaSolucio());
    }

    private static void testGetNumIntents() throws DomainException {
        System.out.println("Testing getNumIntents...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        System.out.printf("En la partida actual s'han jugat %d intents%n", cp.getNumIntents());
    }

    private static void testGetIntents() throws DomainException {
        System.out.println("Testing getIntents...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        System.out.println("Els intents de la partida actual són: ");
        List<List<Integer>> intents = cp.getIntents();
        for (List<Integer> intent : intents)
            printSequence(intent);
    }

    private static void testGetFeedbacks() throws DomainException {
        System.out.println("Testing getFeedbacks...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        System.out.println("Els feedbacks de la partida actual són: ");
        List<List<Integer>> feedbacks = cp.getFeedbacks();
        for (List<Integer> feedback : feedbacks)
            printSequence(feedback);
    }

    private static void testIsJugadorBreaker() throws DomainException {
        System.out.println("Testing isJugadorBreaker...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cp.isJugadorBreaker())
            System.out.println("El jugador és el breaker de la partida");
        else
            System.out.println("El jugador és el maker de la partida");
    }

    private static void testIsPartidaGuanyada() throws DomainException {
        System.out.println("Testing isPartidaGuanyada...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cp.isPartidaGuanyada())
            System.out.println("La partida actual està guanyada");
        else
            System.out.println("La partida actual no està guanyada");
    }

    private static void testIsPartidaPerduda() throws DomainException {
        System.out.println("Testing isPartidaPerduda...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cp.isPartidaPerduda())
            System.out.println("La partida actual està perduda");
        else
            System.out.println("La partida actual no està perduda");
    }

    private static void testIsPartidaAcabada() throws DomainException {
        System.out.println("Testing isPartidaAcabada...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cp.isPartidaAcabada())
            System.out.println("La partida actual està acabada");
        else
            System.out.println("La partida actual no està acabada");
    }

    private static void testIsUltimIntentPle() throws DomainException {
        System.out.println("Testing isUltimIntentPle...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        cp.isUltimIntentPle();
    }

    private static void testIsValidIntentsFeedback() {
        System.out.println("Testing isValidIntentsFeedback...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        List<List<Integer>> intents = scanIntents();
        List<List<Integer>> feedbacks = scanFeedbacks();

        cp.isValidIntentsFeedbacks(intents, feedbacks);
    }

    private static void testSetBola() throws DomainException {
        System.out.println("Testing setBola...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador és el maker");
            return;
        }
        if(cp.isPartidaAcabada()) {
            System.out.println("Error: La partida actual ja ha acabat");
            return;
        }

        System.out.print("Enter un index per la bola: ");
        int index = scanInt();
        while(index < 0 || index >= ControladorPartida.getNumBoles()) {
            System.out.println("L'índex no es correcte");
            System.out.print("Enter un index per la bola: ");
            index = scanInt();
        }
        System.out.printf("L'índex de la bola llegit és %d%n", index);

        System.out.print("Enter un color per la bola: ");
        int bola = scanInt();
        while(!Bola.isValid(bola)) {
            System.out.println("El color donat no és vàlid");
            System.out.print("Enter un color per la bola: ");
            bola = scanInt();
        }
        System.out.printf("El color de bola llegit és %d%n", bola);

        cp.setBola(index, bola);
    }

    private static void testSortirPartida() throws DomainException {
        System.out.println("Testing sortirPartida...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        cp.sortirPartida();
    }

    public static void main(String[] args) throws DomainException {
        cp = new ControladorPartida();
        in = new Scanner(System.in);
        System.out.println("A continuació es mostren les comandes possibles:");
        showUsage();
        while(true) {
            boolean runTest = true;
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
                    runTest = false;
                    break;
                case "2":
                case "isPartidaPresent":
                    testIsPartidaPresent();
                    break;
                case "3":
                case "novaPartidaMaker":
                    testNovaPartidaMaker();
                    break;
                case "4":
                case "novaPartidaBreaker":
                    testNovaPartidaBreaker();
                    break;
                case "5":
                case "carregarPartidaMaker":
                    testCarregarPartidaMaker();
                    break;
                case "6":
                case "carregarPartidaBreaker":
                    testCarregarPartidaBreaker();
                    break;
                case "7":
                case "validarSequencia":
                    testValidarSequencia();
                    break;
                case "8":
                case "botSolve":
                    testBotSolve();
                    break;
                case "9":
                case "getTempsMillis":
                    testGetTempsMillis();
                    break;
                case "10":
                case "addTempsMillis":
                    testAddTempsMillis();
                    break;
                case "11":
                case "getNumBoles":
                    testGetNumBoles();
                    break;
                case "12":
                case "getNivellDificultat":
                    testGetNivellDificultat();
                    break;
                case "13":
                case "getAlgorisme":
                    testGetAlgorisme();
                    break;
                case "14":
                case "getSequenciaSolucio":
                    testGetSequenciaSolucio();
                    break;
                case "15":
                case "getNumIntents":
                    testGetNumIntents();
                    break;
                case "16":
                case "getIntents":
                    testGetIntents();
                    break;
                case "17":
                case "getFeedbacks":
                    testGetFeedbacks();
                    break;
                case "18":
                case "isJugadorBreaker":
                    testIsJugadorBreaker();
                    break;
                case "19":
                case "isPartidaGuanyada":
                    testIsPartidaGuanyada();
                    break;
                case "20":
                case "isPartidaPerduda":
                    testIsPartidaPerduda();
                    break;
                case "21":
                case "isPartidaAcabada":
                    testIsPartidaAcabada();
                    break;
                case "22":
                case "isUltimIntentPle":
                    testIsUltimIntentPle();
                    break;
                case "23":
                case "isValidIntentsFeedback":
                    testIsValidIntentsFeedback();
                    break;
                case "24":
                case "setBola":
                    testSetBola();
                    break;
                case "25":
                case "sortirPartida":
                    testSortirPartida();
                    break;
                default:
                    runTest = false;
            }
            if (runTest)
                System.out.println("End of test");
        }
    }

}
