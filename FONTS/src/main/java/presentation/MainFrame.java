package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Frame de l'aplicació
 * @author Albert Canales
 */
public class MainFrame{

    private final JFrame frame;

    /**
     * Constructor del frame de l'aplicació
     */
    MainFrame() {
        frame = new JFrame();
        frame.setTitle("Mastermind");
        frame.setSize(new Dimension(400, 600));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Mètode per assignar el títol del frame de l'aplicació
     * @param title Títol a assignar
     */
    void setTitle(String title) {
        frame.setTitle(title);
    }

    /**
     * Mètode per assignar el contingut del frame de l'aplicació
     * @param contentPane Contingut a assignar
     */
    void setContent(Container contentPane) {
        frame.setContentPane(contentPane);
        frame.revalidate();
    }

    /**
     * Mètode per mostrar un diàleg de warning
     * @param title Títol del diàleg
     * @param message Missatge del diàleg
     */
    void showWarningDialog(String title, String message) {
        JOptionPane.showMessageDialog(frame,
                message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Mètode per mostrar un diàleg d'error
     * @param message Missatge del diàleg
     */
    void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame,
                message, "Error Intern", JOptionPane.ERROR_MESSAGE);
    }
}
