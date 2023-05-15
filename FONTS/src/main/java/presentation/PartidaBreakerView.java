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

        taulellPanel.setIntentsColors(intents);
        taulellPanel.setFeedbacksColors(feedbacks);
        taulellPanel.setIntentEnabled(numIntentActual, true);
        taulellPanel.setSolucioEnabled(true);
        taulellPanel.attachToBoles(this);

        buttonValidar.setEnabled(false);
        buttonValidar.addActionListener(actionEvent -> buttonValidarClicked());

        timerLabel.start();

        buttonTorna.addActionListener(actionEvent -> buttonTornaClick());

        for (Integer bola : controladorPresentacio.getSolucio()) {
            System.out.print(bola + " ");
        }
        System.out.println();
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

            } catch (PresentationException e) {
                // TODO S'hauria de pensar el tractament d'això
                throw new RuntimeException(e);
            }
        }
    }

    private void setBolaColor(Integer indexBola, BolaColor color) {
        try {
            taulellPanel.setBolaIntentColor(numIntentActual, indexBola, color);
            controladorPresentacio.setBola(indexBola, color.getNumber());
            buttonValidar.setEnabled(controladorPresentacio.isUltimIntentPle());
        } catch (PresentationException e) {
            // TODO S'hauria de pensar el tractament d'això
            throw new RuntimeException(e);
        }
    }

    private void partidaAcabada() {
        List<Integer> solucio = controladorPresentacio.getSolucio();
        try {
            taulellPanel.setSolucioColors(solucio);
            taulellPanel.setSolucioEnabled(false);
            timerLabel.stop();
            if (controladorPresentacio.isPartidaGuanyada())
                controladorPresentacio.showInformationDialog("Has guanyat!",
                        "Has guanyat la partida amb " + numIntentActual + " intents :)");
            else
                controladorPresentacio.showInformationDialog("Has perdut!",
                        "Has perdut la partida :(, pots veure la solució a sobre");
        } catch (PresentationException e) {
            // TODO Això també s'hauria de fer millor
            throw new RuntimeException(e);
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
        buttonValidar.setText("Validar");
        panel2.add(buttonValidar);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel1.add(panel3, BorderLayout.EAST);
        bolaPalettePanel = new BolaPalettePanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel3.add(bolaPalettePanel.$$$getRootComponent$$$(), gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer1, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(0, 0));
        panel.add(panel4, BorderLayout.NORTH);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.add(panel5, BorderLayout.WEST);
        buttonTorna = new JButton();
        buttonTorna.setText("Torna");
        panel5.add(buttonTorna);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.add(panel6, BorderLayout.EAST);
        timerLabel = new TimerLabel();
        panel6.add(timerLabel.$$$getRootComponent$$$());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
