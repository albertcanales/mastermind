package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import exceptions.presentation.BolaNoExistent;
import exceptions.presentation.PresentationException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

    private List<BolaButton> buttonColorList;

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
        // Taulell
        List<List<Integer>> intents = controladorPresentacio.getIntents();
        List<List<Integer>> feedbacks = controladorPresentacio.getFeedbacks();

        taulellPanel.setIntentsColors(intents);
        taulellPanel.setFeedbacksColors(feedbacks);
        taulellPanel.setIntentEnabled(intents.size() - 1, true);
        taulellPanel.setSolucioEnabled(true);
        taulellPanel.setOKButtonVisible(true);
        taulellPanel.attachToBoles(this);
    }

    private void showSolution() {
        List<Integer> solution = controladorPresentacio.getSolucio();
        try {
            taulellPanel.setSolucioColors(solution);
            taulellPanel.setSolucioEnabled(false);
            // TODO Desactivar l'intent actual
            // TODO Caldria també perdre la partida
        } catch (BolaNoExistent e) {
            // TODO S'hauria de pensar el tractament d'això
            throw new RuntimeException(e);
        }
    }

    private void bolaIntentClicked(Integer number) {
        if (bolaPalettePanel.isColorSelected()) {
            try {
                taulellPanel.setBolaIntentColor(number, bolaPalettePanel.getSelectedColor());
            } catch (BolaNoExistent e) {
                // TODO S'hauria de pensar el tractament d'això
                throw new RuntimeException(e);
            }
            bolaPalettePanel.unselectAllColors();
        }
    }

    @Override
    public void Update(Subject s) {
        if (s instanceof BolaButton) {
            String id = ((BolaButton) s).getID();
            Integer number = Integer.parseInt(id.substring(1));
            switch (id.charAt(0)) {
                case 'S':
                    showSolution();
                    break;
                case 'I':
                    bolaIntentClicked(number);
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
        taulellPanel = new TaulellPanel();
        panel.add(taulellPanel.$$$getRootComponent$$$(), BorderLayout.CENTER);
        bolaPalettePanel = new BolaPalettePanel();
        panel.add(bolaPalettePanel.$$$getRootComponent$$$(), BorderLayout.EAST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
