package domain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe amb utilitats comunes entre drivers de controladors
 * @author Albert Canales
 */
public class ControladorDriver {

    static Scanner in;

    static Integer scanInt() {
        if(in.hasNextInt()) {
            return in.nextInt();
        }
        System.out.print("Not an integer, enter again: ");
        in.next();
        return scanInt();
    }

    static Long scanLong() {
        if(in.hasNextLong()) {
            return in.nextLong();
        }
        System.out.print("Not an integer, enter again: ");
        in.next();
        return scanLong();
    }

    static List<Integer> scanSequence() {
        List<Integer> sequence = new ArrayList<>();
        System.out.print("Enter the next bola (negative to stop): ");
        int num = scanInt();
        while(num > -1) {
            sequence.add(num);
            System.out.print("Enter the next bola (negative to stop): ");
            num = scanInt();
        }
        return sequence;
    }

    static List<Integer> scanSolution() {
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

    static Integer scanNivellDificultat() {
        System.out.println("Enter a dificultat: ");

        int dificultat = scanInt();

        if(!NivellDificultat.isValid(dificultat)) {
            System.out.println("The given dificultat is invalid.");
            dificultat = scanNivellDificultat();
        }
        System.out.printf("The given dificultat is: %d%n", dificultat);
        return dificultat;
    }

    static Integer scanAlgorisme() {
        System.out.println("Enter an algorisme: ");
        int algorisme = scanInt();

        if(!TipusAlgorisme.isValid(algorisme)) {
            System.out.println("The given algorisme is invalid.");
            algorisme = scanAlgorisme();
        }
        System.out.printf("The given algorisme is: %d%n", algorisme);
        return algorisme;
    }

    static void printSequence(List<Integer> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print(sequence.get(i));
            if(i+1 < sequence.size()) System.out.print(" ");
            else System.out.print("\n");
        }
    }
}
