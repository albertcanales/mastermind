package presentation;

import exceptions.presentation.PresentationException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista per jugar una partida on el jugador és el maker
 * @author Albert Canales Ros
 */
public class PartidaMakerView {

    private static final Integer PERIOD_TIMER_MILLIS = 1000;

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;
    private JPanel panel;
    private JButton tornaButton;
    private TaulellPanel taulellPanel;
    private JButton buttonParar;
    private JButton buttonAcabar;
    private JButton buttonReproduir;

    private List<List<Integer>> intentList;
    private List<List<Integer>> feedbackList;

    private Integer numIntentActual;
    private Timer timer;

    /**
     * Constructor per defecte de la vista
     */
    PartidaMakerView() throws PresentationException {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Partida Maker");
    }

    /**
     * Mètode per inicialitzar els components de la vista
     */
    private void initComponents() throws PresentationException {
        taulellPanel.setSolucioColors(controladorPresentacio.getSolucio());

        numIntentActual = 0;
        timer = new Timer(PERIOD_TIMER_MILLIS, actionEvent -> showNextIntent());
        buttonParar.setEnabled(false);

        if (!controladorPresentacio.isPartidaAcabada())
            controladorPresentacio.botSolve();

        intentList = controladorPresentacio.getIntents();
        feedbackList = controladorPresentacio.getFeedbacks();

        buttonParar.addActionListener(actionEvent -> buttonPararClick());
        buttonAcabar.addActionListener(actionEvent -> buttonAcabarClick());
        buttonReproduir.addActionListener(actionEvent -> buttonReproduirClick());
        tornaButton.addActionListener(actionEvent -> controladorPresentacio.showHomeView());
    }

    void buttonPararClick() {
        timer.stop();
        buttonReproduir.setEnabled(true);
        buttonAcabar.setEnabled(true);
        buttonParar.setEnabled(false);
    }

    void buttonAcabarClick() {
        timer.stop();
        buttonParar.setEnabled(false);
        buttonReproduir.setEnabled(false);
        buttonAcabar.setEnabled(false);
        try {
            taulellPanel.setIntentsColors(intentList);
            taulellPanel.setFeedbacksColors(feedbackList);
        } catch (PresentationException e) {
            e.printStackTrace();
            controladorPresentacio.showErrorDialog("No s'ha pogut mostrat tota la partida del bot");
        }
    }

    void buttonReproduirClick() {
        buttonReproduir.setEnabled(false);
        buttonParar.setEnabled(true);
        timer.start();
    }

    void showNextIntent() {
        try {
            taulellPanel.setIntentColors(numIntentActual, intentList.get(numIntentActual));
            taulellPanel.setFeedbackColors(numIntentActual, feedbackList.get(numIntentActual));
            numIntentActual++;
            if (numIntentActual == feedbackList.size()) {
                timer.stop();
                buttonAcabar.setEnabled(false);
                buttonParar.setEnabled(false);
            }
        } catch (PresentationException e) {
            throw new RuntimeException(e);
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(panel1, BorderLayout.NORTH);
        tornaButton = new JButton();
        tornaButton.setText("Torna");
        panel1.add(tornaButton);
        taulellPanel = new TaulellPanel();
        panel.add(taulellPanel.$$$getRootComponent$$$(), BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel2, BorderLayout.SOUTH);
        buttonParar = new JButton();
        buttonParar.setText("Parar");
        panel2.add(buttonParar);
        buttonReproduir = new JButton();
        buttonReproduir.setText("Reproduir");
        panel2.add(buttonReproduir);
        buttonAcabar = new JButton();
        buttonAcabar.setText("Acabar");
        panel2.add(buttonAcabar);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
