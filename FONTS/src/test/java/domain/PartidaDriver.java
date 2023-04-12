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

        // TODO falta també comprovar que és el num de boles que toca
        boolean valid = true;
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

    }

    private static void testCarregarPartidaBreaker() {

    }

    private static void testValidarSequencia() {

    }

    private static void testBotSolve() {

    }

    private static void testGetTemps() throws DomainException {
        System.out.println("Testing getTemps...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }
        System.out.printf("El temps transcorregut és de %d ms.%n", cp.getTempsMillis());
    }

    private static void testAddTemps() throws DomainException {
        System.out.println("Testing addTemps...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }

        System.out.print("Enter a temps a afegir: ");
        Long afegit = in.nextLong();
        System.out.printf("S'ha llegit el valor %d%n", afegit);
        cp.addTempsMillis(afegit);
    }

    private static void testGetNivellDificultat() throws DomainException {
        System.out.println("Testing getNivellDificultat...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }
        if(!cp.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el jugador no és breaker");
            return;
        }

        System.out.printf("El nivell de dificultat de la partida %d.%n", cp.getNivellDificultat());
    }

    private static void testGetSequenciaSolucio() throws DomainException {
        System.out.println("Testing getSequenciaSolucio...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }

        System.out.print("La solució de la partida és: ");
        printSequence(cp.getSequenciaSolucio());
    }

    private static void testGetIntents() throws DomainException {
        System.out.println("Testing getIntents...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
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
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }

        System.out.println("Els feedbacks de la partida actual són: ");
        List<List<Integer>> feedbacks = cp.getFeedbacks();
        for (List<Integer> feedback : feedbacks)
            printSequence(feedback);
    }

    private static void testIsPartidaGuanyada() throws DomainException {
        System.out.println("Testing isPartidaGuanyada...");

        if(!cp.isPartidaPresent()) {
            System.out.println("Error: No s'està jugant cap partida ");
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
            System.out.println("Error: No s'està jugant cap partida ");
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
            System.out.println("Error: No s'està jugant cap partida ");
            return;
        }

        if(cp.isPartidaAcabada())
            System.out.println("La partida actual està acabada");
        else
            System.out.println("La partida actual no està acabada");
    }

    private static void testSetBola() {

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
                    runTest = false;
            }
            if (runTest)
                System.out.println("End of test");
        }
    }

}
