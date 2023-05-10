package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Vista inicial per a un usuari no registrat
 * @author Albert Canales
 */
public class InitialView {

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;

    /**
     * Panell contenidor
     */
    private JPanel panel;

    /**
     * Botó per veure el rànquing
     */
    private JButton buttonRanquing;

    /**
     * Botó per veure les normes
     */
    private JButton buttonNormes;

    /**
     * Botó per iniciar sessió
     */
    private JButton buttonIniciaSessio;

    /**
     * Botó per registrar-se
     */
    private JButton buttonRegistra;

    /**
     * Constructor per defecte de la vista
     */
    InitialView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Mastermind");
    }

    /**
     * Mètode per inicialitzar els components de la vista
     */
    private void initComponents() {
        buttonRanquing.addActionListener(actionEvent -> controladorPresentacio.showInitialView());
        buttonNormes.addActionListener(actionEvent -> controladorPresentacio.showNormesView());
        buttonIniciaSessio.addActionListener(actionEvent -> controladorPresentacio.showLoginView());
        buttonRegistra.addActionListener(actionEvent -> controladorPresentacio.showRegisterView());
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel.add(panel1, BorderLayout.NORTH);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.WEST);
        buttonRanquing = new JButton();
        buttonRanquing.setHorizontalAlignment(0);
        buttonRanquing.setText("Rànquing");
        panel2.add(buttonRanquing);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, BorderLayout.EAST);
        buttonNormes = new JButton();
        buttonNormes.setText("Normes");
        panel3.add(buttonNormes);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel.add(panel4, BorderLayout.CENTER);
        buttonIniciaSessio = new JButton();
        buttonIniciaSessio.setText("Inicia Sessió");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(buttonIniciaSessio, gbc);
        buttonRegistra = new JButton();
        buttonRegistra.setText("Registra't");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel4.add(buttonRegistra, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
