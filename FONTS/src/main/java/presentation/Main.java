package presentation;

public class Main {

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                () -> {
                    ControladorPresentacio controladorPresentacio = new ControladorPresentacio();
                    controladorPresentacio.run();
                }
        );
    }

}