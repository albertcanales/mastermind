package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Vista inicial per a un usuari registrat
 *
 * @author Mar Gonzàlez Català
 */
public class HomeView {
    private final ControladorPresentacio controladorPresentacio;
    private JPanel panel;
    private JButton buttonNovaPartida;
    private JButton buttonCarregarPartida;
    private JButton buttonZonaUsuari;
    private JButton buttonRanquing;
    private JButton buttonSortir;
    private JButton buttonNormes;

    HomeView(ControladorPresentacio controladorPresentacio) {
        this.controladorPresentacio = controladorPresentacio;
        $$$setupUI$$$();
        initComponents();
    }

    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Mastermind");
    }

    private void initComponents() {
        if (!controladorPresentacio.existsPartidaGuardada()) {
            buttonCarregarPartida.setEnabled(false);
        }
        //buttonRanquing.addActionListener(actionEvent -> controladorPresentacio.showRanquingView());
        //buttonNormes.addActionListener(actionEvent -> controladorPresentacio.showNormesView());
        buttonNovaPartida.addActionListener(actionEvent -> controladorPresentacio.showNovaPartidaView());
        buttonCarregarPartida.addActionListener(actionEvent -> carregarPartidaButtonClick());
        buttonSortir.addActionListener(actionEvent -> sortirButtonClick());
        //buttonZonaUsuari.addActionListener(actionEvent -> controladorPresentacio.showZonaUsuariView());
    }

    private void sortirButtonClick() {
        controladorPresentacio.logoutUser();
        controladorPresentacio.showInitialView();
    }
    private void carregarPartidaButtonClick() {
        controladorPresentacio.carregarPartida();
        //controladorPresentacio.showCarregarPartidaView();
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
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel2.add(panel3);
        buttonSortir = new JButton();
        buttonSortir.setText("Sortir");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(buttonSortir, gbc);
        buttonNormes = new JButton();
        buttonNormes.setText("Normes");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(buttonNormes, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel4, BorderLayout.EAST);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel4.add(panel5);
        buttonZonaUsuari = new JButton();
        buttonZonaUsuari.setText("Zona Usuari");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(buttonZonaUsuari, gbc);
        buttonRanquing = new JButton();
        buttonRanquing.setText("Rànquing");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(buttonRanquing, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        panel.add(panel6, BorderLayout.CENTER);
        buttonNovaPartida = new JButton();
        buttonNovaPartida.setText("Nova Partida");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(buttonNovaPartida, gbc);
        buttonCarregarPartida = new JButton();
        buttonCarregarPartida.setText("Carregar Partida");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel6.add(buttonCarregarPartida, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
