package presentation;

import exceptions.presentation.PresentationException;
import exceptions.presentation.SequenciaNoExistent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista inicial per a un usuari no registrat
 *
 * @author Albert Canales
 */
public class PartidaBreakerView implements Observer {

    private static final String GUANYADA_TEXT = "Guanyada :)";
    private static final String PERDUDA_TEXT = "Perduda :(";

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;

    /**
     * Panell contenidor
     */
    private JPanel panel;
    private TaulellPanel taulellPanel;
    private BolaPalettePanel bolaPalettePanel;
    private JButton buttonValidar;
    private JButton buttonTorna;
    private TimerLabel timerLabel;
    private JLabel labelEstatPartida;

    Integer numIntentActual;

    /**
     * Constructor per defecte de la vista
     */
    PartidaBreakerView() throws PresentationException {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Partida Breaker");
    }

    /**
     * Mètode per inicialitzar els components de la vista
     */
    private void initComponents() throws PresentationException {
        List<List<Integer>> intents = controladorPresentacio.getIntents();
        List<List<Integer>> feedbacks = controladorPresentacio.getFeedbacks();

        numIntentActual = intents.size() - 1;

        // TODO S'ha de comprovar quan estigui implementat a persistència
        timerLabel.setTime(controladorPresentacio.getTempsPartidaMillis());

        taulellPanel.setIntentsColors(intents);
        taulellPanel.setFeedbacksColors(feedbacks);
        taulellPanel.setSolucioEnabled(!controladorPresentacio.isPartidaAcabada());
        if (controladorPresentacio.isPartidaAcabada()) {
            taulellPanel.setSolucioColors(controladorPresentacio.getSolucio());
            if (controladorPresentacio.isPartidaGuanyada())
                labelEstatPartida.setText(GUANYADA_TEXT);
            else
                labelEstatPartida.setText(PERDUDA_TEXT);
        } else {
            buttonValidar.setEnabled(controladorPresentacio.isUltimIntentPle());
            taulellPanel.setIntentEnabled(numIntentActual, true);
            timerLabel.start();
        }
        taulellPanel.attachToBoles(this);

        buttonValidar.addActionListener(actionEvent -> buttonValidarClicked());
        buttonTorna.addActionListener(actionEvent -> buttonTornaClick());
    }

    private void showSolution() {
        Boolean dialogResponse = controladorPresentacio.showYesNoDialog("Veure solució",
                "Segur que voleu veure la solució? Perdreu immediatament la partida");
        if (dialogResponse) {
            List<Integer> solution = controladorPresentacio.getSolucio();
            try {
                taulellPanel.setSolucioColors(solution);
                taulellPanel.setSolucioEnabled(false);
                taulellPanel.setIntentEnabled(numIntentActual, false);
                timerLabel.stop();
                controladorPresentacio.veureSolucio();
                labelEstatPartida.setText(PERDUDA_TEXT);

            } catch (PresentationException e) {
                controladorPresentacio.showErrorDialog("No s'ha pogut mostrar la solució");
            }
        }
    }

    private void setBolaColor(Integer indexBola, BolaColor color) {
        try {
            taulellPanel.setBolaIntentColor(numIntentActual, indexBola, color);
            controladorPresentacio.setBola(indexBola, color.getNumber());
            buttonValidar.setEnabled(controladorPresentacio.isUltimIntentPle());
        } catch (PresentationException e) {
            controladorPresentacio.showErrorDialog("No s'ha pogut assignar la bola");
        }
    }

    private void partidaAcabada() {
        List<Integer> solucio = controladorPresentacio.getSolucio();
        try {
            taulellPanel.setSolucioColors(solucio);
            taulellPanel.setSolucioEnabled(false);
            timerLabel.stop();
            if (controladorPresentacio.isPartidaGuanyada()) {
                controladorPresentacio.showInformationDialog("Has guanyat!",
                        "Has guanyat la partida amb " + numIntentActual + " intents :)");
                labelEstatPartida.setText(GUANYADA_TEXT);
            } else {
                controladorPresentacio.showInformationDialog("Has perdut!",
                        "Has perdut la partida :(, pots veure la solució a sobre");
                labelEstatPartida.setText(PERDUDA_TEXT);
            }
        } catch (PresentationException e) {
            controladorPresentacio.showErrorDialog("No s'ha pogut acabar la partida");
        }
    }

    private void bolaIntentClicked(Integer number) {
        if (bolaPalettePanel.isColorSelected()) {
            setBolaColor(number, bolaPalettePanel.getSelectedColor());
            // bolaPalettePanel.unselectAllColors();
        }
    }

    private void buttonValidarClicked() {
        try {
            List<Integer> feedback = controladorPresentacio.validarSequencia();
            taulellPanel.setFeedbackColors(numIntentActual, feedback);
            taulellPanel.setIntentEnabled(numIntentActual, false);
            numIntentActual++;
            buttonValidar.setEnabled(false);

            if (controladorPresentacio.isPartidaAcabada())
                partidaAcabada();
            else
                taulellPanel.setIntentEnabled(numIntentActual, true);

        } catch (PresentationException e) {
            throw new RuntimeException(e);
        }
    }

    private void buttonTornaClick() {
        controladorPresentacio.sortirPartida();
        controladorPresentacio.showHomeView();
    }

    @Override
    public void Update(Subject s) {
        if (s instanceof BolaButton) {
            String id = ((BolaButton) s).getID();
            switch (id.charAt(0)) {
                case 'S':
                    showSolution();
                    break;
                case 'I':
                    Integer indexBola = Integer.parseInt(id.substring(1));
                    bolaIntentClicked(indexBola);
                    break;
            }
        } else if (s instanceof TimerLabel) {
            controladorPresentacio.addTempsPartidaMillis(TimerLabel.PERIOD_MILLIS);
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
        panel1.setLayout(new BorderLayout(0, 0));
        panel.add(panel1, BorderLayout.CENTER);
        taulellPanel = new TaulellPanel();
        panel1.add(taulellPanel.$$$getRootComponent$$$(), BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.SOUTH);
        buttonValidar = new JButton();
        buttonValidar.setEnabled(false);
        buttonValidar.setText("Validar");
        panel2.add(buttonValidar);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel.add(panel3, BorderLayout.NORTH);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.add(panel4, BorderLayout.WEST);
        buttonTorna = new JButton();
        buttonTorna.setText("Torna");
        panel4.add(buttonTorna);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.add(panel5, BorderLayout.EAST);
        timerLabel = new TimerLabel();
        panel5.add(timerLabel.$$$getRootComponent$$$());
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.add(panel6, BorderLayout.CENTER);
        labelEstatPartida = new JLabel();
        labelEstatPartida.setHorizontalAlignment(0);
        labelEstatPartida.setHorizontalTextPosition(0);
        labelEstatPartida.setText("");
        panel6.add(labelEstatPartida);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridBagLayout());
        panel.add(panel7, BorderLayout.EAST);
        bolaPalettePanel = new BolaPalettePanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel7.add(bolaPalettePanel.$$$getRootComponent$$$(), gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel7.add(spacer1, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
