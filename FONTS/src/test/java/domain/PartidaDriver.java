package domain;

import domain.exceptions.DomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class PartidaDriver {

    private static ControladorPartida cp;
    private static Scanner in;

    // TODO quan s'escaneja un int cal comprovar que ho és realment

    private static List<Integer> scanSequence() {
        List<Integer> sequence = new ArrayList<>();
        System.out.print("Enter the next bola (negative to stop): ");
        int num = in.nextInt();
        while(num > -1) {
            sequence.add(num);
            System.out.print("Enter the next bola (negative to stop): ");
            num = in.nextInt();
        }
        return sequence;
    }
    
    private static List<Integer> scanSolution() {
        System.out.println("Enter a solution: ");
        List<Integer> solution = scanSequence();
        System.out.println("The read solution is: ");
        printSequence(solution);

        boolean valid = solution.size() == ControladorPartida.getNumBoles();
        for (Integer bola : solution) {
            if(!Bola.isColor(bola)) valid = false;
        }

        if(!valid) {
            System.out.println("The given solution is invalid. Please enter a correct one.");
            return scanSolution();
        }
        return solution;
    }
    
    private static Integer scanNivellDificultat() {
        System.out.println("Enter a dificultat: ");

        int dificultat = in.nextInt();

        if(!NivellDificultat.isValid(dificultat)) {
            System.out.println("The given dificultat is invalid.");
            dificultat = scanNivellDificultat();
        }
        System.out.printf("The given dificultat is: %d%n", dificultat);
        return dificultat;
    }

    private static Integer scanAlgorisme() {
        System.out.println("Enter an algorisme: ");
        int algorisme = in.nextInt();

        if(!TipusAlgorisme.isValid(algorisme)) {
            System.out.println("The given algorisme is invalid.");
            algorisme = scanAlgorisme();
        }
        System.out.printf("The given algorisme is: %d%n", algorisme);
        return algorisme;
    }

    private static void printSequence(List<Integer> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print(sequence.get(i));
            if(i+1 < sequence.size()) System.out.print(" ");
            else System.out.println("\n");
        }
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
        System.out.println("    22 | setBola");
        System.out.println("Cada comanda es pot executar pel seu nombre o pel seu nom.");
    }

    private static void testIsPartidaPresent() {
        System.out.println("Testing novaPartidaMaker...");

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

    private static void testCarregarPartidaMaker() {
        System.out.println("Testing carregarPartidaMaker...");
    }

    private static void testCarregarPartidaBreaker() {
        System.out.println("Testing carregarPartidaBreaker...");
    }

    private static void testValidarSequencia() {
        System.out.println("Testing validarSequencia...");
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
        System.out.println("Testing getTemps...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        System.out.printf("El temps transcorregut és de %d ms.%n", cp.getTempsMillis());
    }

    private static void testAddTempsMillis() throws DomainException {
        System.out.println("Testing addTemps...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        System.out.print("Enter a temps a afegir: ");
        Long afegit = in.nextLong();
        System.out.printf("S'ha llegit el valor %d%n", afegit);
        cp.addTempsMillis(afegit);
    }

    private static void testGetNumBoles() {
        System.out.println("Testing getNivellDificultat...");

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

    private static void testSetBola() throws DomainException {
        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cp.isPartidaAcabada()) {
            System.out.println("Error: La partida actual ja ha acabat");
            return;
        }

        System.out.print("Enter un index per la bola: ");
        int index = in.nextInt();
        while(index < 0 || index >= cp.getNumBoles()) {
            System.out.println("L'índex no es correcte");
            System.out.print("Enter un index per la bola: ");
            index = in.nextInt();
        }
        System.out.printf("L'índex de la bola llegit és %d%n", index);

        System.out.print("Enter un color per la bola: ");
        int bola = in.nextInt();
        while(Bola.isValid(bola)) {
            System.out.println("El color donat no és vàlid");
            System.out.print("Enter un color per la bola: ");
            bola = in.nextInt();
        }
        System.out.printf("El color de bola llegit és %d%n", index);

        cp.setBola(index, bola);
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
                case "isPartidaGuanyuada":
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
                case "setBola":
                    testSetBola();
                    break;
                default:
                    runTest = false;
            }
            if (runTest)
                System.out.println("End of test");
        }
    }

}
