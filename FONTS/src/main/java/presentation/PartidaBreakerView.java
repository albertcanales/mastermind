package presentation;

import exceptions.presentation.BolaNoExistent;
import exceptions.presentation.PresentationException;

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
    }

    private void showSolution() {
        List<Integer> solution = controladorPresentacio.getSolucio();
        try {
            taulellPanel.setSolucioColors(solution);
            taulellPanel.setSolucioEnabled(false);
            taulellPanel.setIntentEnabled(numIntentActual, false);
            // TODO Caldria també perdre la partida
        } catch (PresentationException e) {
            // TODO S'hauria de pensar el tractament d'això
            throw new RuntimeException(e);
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

    private void bolaIntentClicked(Integer number) {
        if (bolaPalettePanel.isColorSelected()) {
            setBolaColor(number, bolaPalettePanel.getSelectedColor());
            bolaPalettePanel.unselectAllColors();
        }
    }

    private void buttonValidarClicked() {
        try {
            List<Integer> feedback = controladorPresentacio.validarSequencia();
            taulellPanel.setFeedbackColors(numIntentActual, feedback);
            taulellPanel.setIntentEnabled(numIntentActual, false);
            numIntentActual++;
            taulellPanel.setIntentEnabled(numIntentActual, true);
            buttonValidar.setEnabled(false);
            // TODO Comprovar si s'ha guanyat, perdut, etc
        } catch (PresentationException e) {
            throw new RuntimeException(e);
        }
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
        bolaPalettePanel = new BolaPalettePanel();
        panel.add(bolaPalettePanel.$$$getRootComponent$$$(), BorderLayout.EAST);
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
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
