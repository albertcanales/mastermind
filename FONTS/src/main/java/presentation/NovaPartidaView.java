package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NovaPartidaView {

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;
    /**
     * Panell contenidor
     */
    private JPanel panel;
    /**
     * Botó per tornar a la pantalla de home
     */
    private JButton buttonTorna;
    private NovaPartidaBreakerPanel panelBreaker;
    private NovaPartidaMakerPanel panelMaker;
    private JButton buttonJuga;
    private JTabbedPane tabbedPane;


    /**
     * Constructor per defecte de la vista
     */
    NovaPartidaView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }
    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Crear Partida");
    }
    /**
     * Mètode per inicialitzar els components de la vista
     */
    void initComponents() {

        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showHomeView());
        buttonJuga.addActionListener(actionEvent -> jugaButtonClick());
    }

    private void jugaButtonClick() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controladorPresentacio.novaPartidaBreaker(panelBreaker.getDificultat());
                controladorPresentacio.showPartidaBreakerView();
                break;
            case 1:
                List<Integer> solucio = panelMaker.getSolucio();
                for (int i = 0; i < solucio.size(); ++i) {
                    if (solucio.get(i) == 0) {
                        controladorPresentacio.showWarningDialog("No s'ha pogut crear la partida", "Hi ha alguna bola buida a la seqüència solució");
                        return;
                    }
                }
                controladorPresentacio.novaPartidaMaker(solucio, panelMaker.getAlgorisme());
                //controladorPresentacio.showPartidaMaker();
                break;
        }
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
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(1);
        panel.add(tabbedPane, BorderLayout.CENTER);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        tabbedPane.addTab("Breaker", panel1);
        panelBreaker = new NovaPartidaBreakerPanel();
        panel1.add(panelBreaker.$$$getRootComponent$$$(), BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tabbedPane.addTab("Maker", panel2);
        panelMaker = new NovaPartidaMakerPanel();
        panel2.add(panelMaker.$$$getRootComponent$$$());
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(panel3, BorderLayout.NORTH);
        buttonTorna = new JButton();
        buttonTorna.setText("Torna");
        panel3.add(buttonTorna);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel4, BorderLayout.SOUTH);
        buttonJuga = new JButton();
        buttonJuga.setText("Juga!");
        panel4.add(buttonJuga);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
