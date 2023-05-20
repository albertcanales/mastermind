package presentation;

import exceptions.presentation.PresentationException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista d'una partida en joc amb el jugador com a breaker
 * @author Albert Canales
 */
public class PartidaBreakerView implements Observer {

    /**
     * Text de l'estat per una partida guanyada
     */
    private static final String GUANYADA_TEXT = "Guanyada :)";

    /**
     * Text de l'estat per una partida perduda
     */
    private static final String PERDUDA_TEXT = "Perduda :(";

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;

    /**
     * Panell contenidor
     */
    private JPanel panel;

    /**
     * Panell del taulell de la partida
     */
    private TaulellPanel taulellPanel;

    /**
     * Panell amb la paleta de boles a seleccionar
     */
    private BolaPalettePanel bolaPalettePanel;

    /**
     * Botó per validar l'última seqüència
     */
    private JButton buttonValidar;

    /**
     * Button per tornar a la pantalla de benvinguda
     */
    private JButton buttonTorna;

    /**
     * Label que mostra el temps transcorregut
     */
    private TimerLabel timerLabel;

    /**
     * Label amb l'estat de la partida (guanyada, perduda o en joc)
     */
    private JLabel labelEstatPartida;

    /**
     * Nombre de l'intent en joc
     */
    private Integer numIntentActual;

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
        timerLabel.Attach(this);

        buttonValidar.addActionListener(actionEvent -> buttonValidarClicked());
        buttonTorna.addActionListener(actionEvent -> buttonTornaClick());
    }

    /**
     * Mètode per si l'usuari vol veure la solució
     */
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
                e.printStackTrace();
                controladorPresentacio.showErrorDialog("No s'ha pogut mostrar la solució");
            }
        }
    }

    /**
     * Mẁetode per si l'usuari vol assignar una bola
     * @param indexBola Índex de la bola dins de la seqüència
     * @param color Color de la bola seleccionada en la paleta
     */
    private void setBolaColor(Integer indexBola, BolaColor color) {
        try {
            taulellPanel.setBolaIntentColor(numIntentActual, indexBola, color);
            controladorPresentacio.setBola(indexBola, color.getNumber());
            buttonValidar.setEnabled(controladorPresentacio.isUltimIntentPle());
        } catch (PresentationException e) {
            e.printStackTrace();
            controladorPresentacio.showErrorDialog("No s'ha pogut assignar la bola");
        }
    }

    /**
     * Mètode per acabar una partida (ja sigui per haver fet massa intents o haver encertat la solució)
     */
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
            e.printStackTrace();
            controladorPresentacio.showErrorDialog("No s'ha pogut acabar la partida");
        }
    }

    /**
     * Mètode a cridar quan una bola de l'últim intent ha estat clicada
     */
    private void bolaIntentClicked(Integer number) {
        if (bolaPalettePanel.isColorSelected()) {
            setBolaColor(number, bolaPalettePanel.getSelectedColor());
            // bolaPalettePanel.unselectAllColors();
        }
    }

    /**
     * Mètode a cridar quan el botó de validar ha estat cridat
     */
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
            e.printStackTrace();
            controladorPresentacio.showErrorDialog("No s'ha pogut validar la seqüència");
        }
    }

    /**
     * Mètode per tornar a la vista anterior (HomeView)
     */
    private void buttonTornaClick() {
        timerLabel.stop();
        controladorPresentacio.sortirPartida();
        controladorPresentacio.showHomeView();
    }

    /**
     * Mètode que es crida quan alguns dels Subjectes adjunts envia una notificació
     */
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
            controladorPresentacio.addTempsPartidaMillis(Long.valueOf(TimerLabel.PERIOD_MILLIS));
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
