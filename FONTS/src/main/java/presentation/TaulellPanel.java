package presentation;

import exceptions.presentation.BolaNoExistent;
import exceptions.presentation.PresentationException;
import exceptions.presentation.SequenciaNoExistent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaulellPanel {

    /**
     * Nombre de boles d'una seqüència
     */
    final static int SEQUENCIA_SIZE = SequenciaPanel.SEQUENCIA_SIZE;

    /**
     * Panell contenidor del taulell
     */
    private JPanel panel;
    private SequenciaPanel sequenciaIntent0;
    private SequenciaPanel sequenciaIntent1;
    private SequenciaPanel sequenciaIntent2;
    private SequenciaPanel sequenciaIntent3;
    private SequenciaPanel sequenciaIntent4;
    private SequenciaPanel sequenciaIntent5;
    private SequenciaPanel sequenciaIntent6;
    private SequenciaPanel sequenciaIntent7;
    private SequenciaPanel sequenciaIntent8;
    private SequenciaPanel sequenciaIntent9;
    private SequenciaPanel sequenciaIntent10;
    private SequenciaPanel sequenciaIntent11;
    private SequenciaPanel sequenciaFeedback0;
    private SequenciaPanel sequenciaFeedback1;
    private SequenciaPanel sequenciaFeedback2;
    private SequenciaPanel sequenciaFeedback3;
    private SequenciaPanel sequenciaFeedback4;
    private SequenciaPanel sequenciaFeedback5;
    private SequenciaPanel sequenciaFeedback6;
    private SequenciaPanel sequenciaFeedback7;
    private SequenciaPanel sequenciaFeedback8;
    private SequenciaPanel sequenciaFeedback9;
    private SequenciaPanel sequenciaFeedback10;
    private SequenciaPanel sequenciaFeedback11;

    /**
     * Solució del taulell
     */
    private SequenciaPanel sequenciaSolucio;

    /**
     * Llista dels intents del taullel
     */
    private ArrayList<SequenciaPanel> sequenciaIntentList;

    /**
     * Llista dels feedbacks del taullel
     */
    private ArrayList<SequenciaPanel> sequenciaFeedbackList;

    /**
     * Constructor per defecte del taulell
     */
    TaulellPanel() {
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per inicialitzar tots els components del taulell
     */
    private void initComponents() {
        initSolucio();
        initFeedbacks();
        initIntents();
    }

    /**
     * Mètode per inicialitzar la solució del taulell
     */
    private void initSolucio() {
        sequenciaSolucio.setEnabled(false);
        for (int bola = 0; bola < SequenciaPanel.SEQUENCIA_SIZE; bola++) {
            sequenciaSolucio.setBolaID(bola, "S" + bola);
        }
    }

    /**
     * Mètode per inicialitzar els feedbacks del taulell
     */
    private void initFeedbacks() {
        sequenciaFeedbackList = new ArrayList<>(List.of(sequenciaFeedback0, sequenciaFeedback1, sequenciaFeedback2,
                sequenciaFeedback3, sequenciaFeedback4, sequenciaFeedback5, sequenciaFeedback6, sequenciaFeedback7,
                sequenciaFeedback8, sequenciaFeedback9, sequenciaFeedback10, sequenciaFeedback11));

        for (SequenciaPanel feedback : sequenciaFeedbackList) {
            feedback.setEnabled(false);
        }
    }

    /**
     * Mètode per inicialitzar els intents del taulell
     */
    private void initIntents() {
        sequenciaIntentList = new ArrayList<>(List.of(sequenciaIntent0, sequenciaIntent1, sequenciaIntent2,
                sequenciaIntent3, sequenciaIntent4, sequenciaIntent5, sequenciaIntent6, sequenciaIntent7,
                sequenciaIntent8, sequenciaIntent9, sequenciaIntent10, sequenciaIntent11));

        for (SequenciaPanel sequenciaPanel : sequenciaIntentList) {
            sequenciaPanel.setEnabled(false);
            for (int bola = 0; bola < SEQUENCIA_SIZE; bola++) {
                sequenciaPanel.setBolaID(bola, "I" + bola);
            }
        }
    }

    /**
     * Mètode per assignar el color d'una bola d`un intent del taulell
     *
     * @param indexIntent Índex de l'intent a modificar
     * @param indexBola   Índex de la bola a modificar
     * @throws PresentationException si cap dels dos índexs no són correctes
     */
    void setBolaIntentColor(Integer indexIntent, Integer indexBola, BolaColor bolaColor) throws PresentationException {
        if (indexIntent < 0 || indexIntent >= sequenciaIntentList.size())
            throw new SequenciaNoExistent();
        sequenciaIntentList.get(indexIntent).setBolaColor(indexBola, bolaColor);
    }

    /**
     * Mètode per assignar els valors de la seqüencia d'un dels feedbacks
     *
     * @param index    Índex del feedback a modificar
     * @param feedback Llista amb els valors dels colors per al feedback
     * @throws PresentationException si l'índex o la mida del feedback no són correctes
     */
    void setFeedbackColors(Integer index, List<Integer> feedback) throws PresentationException {
        if (index < 0 || index >= sequenciaFeedbackList.size())
            throw new SequenciaNoExistent();
        sequenciaFeedbackList.get(index).setSequenciaColors(feedback);
    }

    /**
     * Mètode per assignar els valors de la seqüencia d'un dels intents
     *
     * @param index    Índex de l'intent a modificar
     * @param intent Llista amb els valors dels colors per a l'intent
     * @throws PresentationException si l'índex o la mida del feedback no són correctes
     */
    void setIntentColors(Integer index, List<Integer> intent) throws PresentationException {
        if (index < 0 || index >= sequenciaIntentList.size())
            throw new SequenciaNoExistent();
        sequenciaIntentList.get(index).setSequenciaColors(intent);
    }

    /**
     * Mètode per assignar els valors de les seqüències d'intents
     *
     * @param intents Llista amb els valors dels colors per als intents
     * @throws PresentationException si les mides dels intents no són correctes
     */
    void setIntentsColors(List<List<Integer>> intents) throws PresentationException {
        if (intents.size() > sequenciaIntentList.size()+1)
            throw new SequenciaNoExistent();
        for (int i = 0; i < intents.size(); i++)
            if(i < sequenciaIntentList.size())
                sequenciaIntentList.get(i).setSequenciaColors(intents.get(i));
    }

    /**
     * Mètode per assignar els valors de les seqüències de feedbacks
     *
     * @param feedbacks Llista amb els valors dels colors per als feedbacks
     * @throws PresentationException si les mides dels feedbacks no són correctes
     */
    void setFeedbacksColors(List<List<Integer>> feedbacks) throws PresentationException {
        if (feedbacks.size() > sequenciaFeedbackList.size())
            throw new SequenciaNoExistent();
        for (int i = 0; i < feedbacks.size(); i++)
            sequenciaFeedbackList.get(i).setSequenciaColors(feedbacks.get(i));
    }

    /**
     * Mètode per assignar els valors de la seqüència solució
     *
     * @param solucio Llista amb els valors dels colors per la solució
     * @throws BolaNoExistent si la mida de la solució no és correcta
     */
    void setSolucioColors(List<Integer> solucio) throws BolaNoExistent {
        sequenciaSolucio.setSequenciaColors(solucio);
    }

    /**
     * Mètode per activar o desactivar la solució del taulell
     *
     * @param enabled Si es vol activar o desactivar
     */
    void setSolucioEnabled(Boolean enabled) {
        sequenciaSolucio.setEnabled(enabled);
    }

    /**
     * Mètode per activar o desactivar un intent del taulell
     *
     * @param index   Índex de l'intent a activar
     * @param enabled Si es vol activar o desactivar
     * @throws SequenciaNoExistent si l'índex no correspon a cap intent
     */
    void setIntentEnabled(Integer index, Boolean enabled) throws SequenciaNoExistent {
        if (index < 0 || index >= sequenciaIntentList.size())
            throw new SequenciaNoExistent();
        sequenciaIntentList.get(index).setEnabled(enabled);
    }

    /**
     * Mètode per adjuntar un observador a les boles dels intents i de la solució del taulell
     *
     * @param o Observador a adjuntar
     */
    void attachToBoles(Observer o) {
        for (SequenciaPanel intent : sequenciaIntentList)
            intent.attachToBoles(o);
        sequenciaSolucio.attachToBoles(o);
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
        panel1.setLayout(new GridBagLayout());
        panel.add(panel1, BorderLayout.CENTER);
        sequenciaFeedback11 = new SequenciaPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(sequenciaFeedback11.$$$getRootComponent$$$(), gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        sequenciaFeedback10 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(sequenciaFeedback10.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback9 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(sequenciaFeedback9.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback8 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(sequenciaFeedback8.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback7 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel1.add(sequenciaFeedback7.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback6 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel1.add(sequenciaFeedback6.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback5 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel1.add(sequenciaFeedback5.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback4 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel1.add(sequenciaFeedback4.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback3 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel1.add(sequenciaFeedback3.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback2 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel1.add(sequenciaFeedback2.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback1 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel1.add(sequenciaFeedback1.$$$getRootComponent$$$(), gbc);
        sequenciaFeedback0 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel1.add(sequenciaFeedback0.$$$getRootComponent$$$(), gbc);
        sequenciaIntent11 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel1.add(sequenciaIntent11.$$$getRootComponent$$$(), gbc);
        sequenciaIntent10 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel1.add(sequenciaIntent10.$$$getRootComponent$$$(), gbc);
        sequenciaIntent9 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel1.add(sequenciaIntent9.$$$getRootComponent$$$(), gbc);
        sequenciaIntent8 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel1.add(sequenciaIntent8.$$$getRootComponent$$$(), gbc);
        sequenciaIntent7 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel1.add(sequenciaIntent7.$$$getRootComponent$$$(), gbc);
        sequenciaIntent6 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel1.add(sequenciaIntent6.$$$getRootComponent$$$(), gbc);
        sequenciaIntent5 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel1.add(sequenciaIntent5.$$$getRootComponent$$$(), gbc);
        sequenciaIntent4 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        panel1.add(sequenciaIntent4.$$$getRootComponent$$$(), gbc);
        sequenciaIntent3 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        panel1.add(sequenciaIntent3.$$$getRootComponent$$$(), gbc);
        sequenciaIntent2 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        panel1.add(sequenciaIntent2.$$$getRootComponent$$$(), gbc);
        sequenciaIntent1 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        panel1.add(sequenciaIntent1.$$$getRootComponent$$$(), gbc);
        sequenciaIntent0 = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        panel1.add(sequenciaIntent0.$$$getRootComponent$$$(), gbc);
        sequenciaSolucio = new SequenciaPanel();
        panel.add(sequenciaSolucio.$$$getRootComponent$$$(), BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
