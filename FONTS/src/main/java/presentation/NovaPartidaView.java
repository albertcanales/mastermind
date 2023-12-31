package presentation;

import exceptions.presentation.BolaNoExistentException;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * Vista per crear una nova partida
 * @author Kamil Przybyszewski
 */
class NovaPartidaView implements Observer {

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;

    /**
     * Panell contenidor
     */
    private JPanel panel;

    /**
     * Botó per tornar a la vista de benvinguda
     */
    private JButton buttonTorna;

    /**
     * Panell per crear una nova partida amb el jugador com a breaker
     */
    private NovaPartidaBreakerPanel panelBreaker;

    /**
     * Panell per crear una nova partida amb el jugador com a maker
     */
    private NovaPartidaMakerPanel panelMaker;

    /**
     * Botó per crear la partida
     */
    private JButton buttonJuga;

    /**
     * Panell per tabular entre partida maker i breaker
     */
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
    private void initComponents() {
        panelMaker.attachSolucio(this);

        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showHomeView());
        buttonJuga.addActionListener(actionEvent -> jugaButtonClick());
    }

    /**
     * Mètode que es crida en clicar el botó de jugar (crear la partida)
     */
    private void jugaButtonClick() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controladorPresentacio.novaPartidaBreaker(panelBreaker.getDificultat());
                controladorPresentacio.showPartidaBreakerView();
                break;
            case 1:
                List<Integer> solucio = panelMaker.getSolucio();
                for (Integer bolaNumber : solucio) {
                    if (Objects.equals(bolaNumber, BolaColor.NUL.getNumber())) {
                        controladorPresentacio.showWarningDialog("No s'ha pogut crear la partida",
                                "Hi ha alguna bola buida a la seqüència solució");
                        return;
                    }
                }
                controladorPresentacio.novaPartidaMaker(solucio, panelMaker.getAlgorisme());
                controladorPresentacio.showPartidaMakerView();
                break;
        }
    }

    /**
     * Mètode perquè per cada actualització dels botons de selecció de la solució, adoptin el color seleccionat
     * @param s Botó de selecció del qual prové la notificació
     */
    @Override
    public void Update(Subject s) {
        try {
            panelMaker.bolaSolucioClicked(((BolaButton) s).getID());
        } catch (BolaNoExistentException e) {
            controladorPresentacio.showErrorDialog("No s'ha pogut canviar el color de la bola");
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
        panel2.setLayout(new BorderLayout(0, 0));
        tabbedPane.addTab("Maker", panel2);
        panelMaker = new NovaPartidaMakerPanel();
        panel2.add(panelMaker.$$$getRootComponent$$$(), BorderLayout.CENTER);
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
