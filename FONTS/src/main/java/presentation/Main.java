package presentation;

/**
 * Punt d'entrada a l'aplicació
 * @author Albert Canales
 */
public class Main {

    /**
     * Funció "main" d'entrada a l'aplicació
     * @param args Arguments del programa, seran ignorats
     * @author Albert Canales
     */
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                () -> ControladorPresentacio.getInstance().run()
        );
    }

}