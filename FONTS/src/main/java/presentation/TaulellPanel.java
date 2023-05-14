package presentation;

import exceptions.presentation.BolaNoExistent;
import exceptions.presentation.PresentationException;
import exceptions.presentation.SequenciaNoExistent;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;

public class TaulellPanel {

    private final int SEQUENCIA_SIZE = 4;

    private final ControladorPresentacio controladorPresentacio;
    private JPanel panel;
    private JButton buttonOK1;
    private JButton buttonOK0;
    private JButton buttonOK2;
    private JButton buttonOK3;
    private JButton buttonOK4;
    private JButton buttonOK5;
    private JButton buttonOK6;
    private JButton buttonOK7;
    private JButton buttonOK8;
    private JButton buttonOK9;
    private JButton buttonOK10;
    private JButton buttonOK11;
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
    private SequenciaPanel sequenciaSolucio;
    private ArrayList<SequenciaPanel> sequenciaIntentList;
    private ArrayList<SequenciaPanel> sequenciaFeedbackList;
    private ArrayList<JButton> buttonOKList;

    TaulellPanel() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Partida");
    }

    private void initComponents() {
        initSolucio();
        initFeedbacks();
        initIntents();
        initOKButtons();
    }

    private void initSolucio() {
        sequenciaSolucio.setEnabled(false);
        for (int bola = 0; bola < SEQUENCIA_SIZE; bola++) {
            sequenciaSolucio.setBolaID(bola, "S" + bola);
        }
    }

    private void initFeedbacks() {
        sequenciaFeedbackList = new ArrayList<>(List.of(sequenciaFeedback0, sequenciaFeedback1, sequenciaFeedback2,
                sequenciaFeedback3, sequenciaFeedback4, sequenciaFeedback5, sequenciaFeedback6, sequenciaFeedback7,
                sequenciaFeedback8, sequenciaFeedback9, sequenciaFeedback10, sequenciaFeedback11));

        int id = 0;
        for (SequenciaPanel feedback : sequenciaFeedbackList) {
            feedback.setEnabled(false);
            for (int bola = 0; bola < SEQUENCIA_SIZE; bola++) {
                feedback.setBolaID(bola, "F" + id);
                id++;
            }
        }
    }

    private void initIntents() {
        sequenciaIntentList = new ArrayList<>(List.of(sequenciaIntent0, sequenciaIntent1, sequenciaIntent2,
                sequenciaIntent3, sequenciaIntent4, sequenciaIntent5, sequenciaIntent6, sequenciaIntent7,
                sequenciaIntent8, sequenciaIntent9, sequenciaIntent10, sequenciaIntent11));

        int id = 0;
        for (int seq = 1; seq < sequenciaIntentList.size(); seq++) {
            sequenciaIntentList.get(seq).setEnabled(false);
            for (int bola = 0; bola < SEQUENCIA_SIZE; bola++) {
                sequenciaIntentList.get(seq).setBolaID(bola, "I" + id);
                id++;
            }
        }
    }

    private void initOKButtons() {
        buttonOKList = new ArrayList<>(List.of(buttonOK0, buttonOK1, buttonOK2, buttonOK3, buttonOK4, buttonOK5,
                buttonOK6, buttonOK7, buttonOK8, buttonOK9, buttonOK10, buttonOK11));

        for (JButton button : buttonOKList) {
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setEnabled(false);
            // button.addActionListener(actionEvent -> validarIntentClick());
            // TODO fer tractament del OK
        }
    }

    void setOKButtonVisible(Boolean visible) {
        for (JButton buttonOK : buttonOKList) {
            buttonOK.setVisible(visible);
        }
    }

    void setFeedbackColors(Integer index, List<Integer> feedback) throws PresentationException {
        if (index < 0 || index >= sequenciaFeedbackList.size())
            throw new SequenciaNoExistent();
        sequenciaIntentList.get(index).setSequenciaColors(feedback);
    }

    void setSequenciesColors(List<List<Integer>> intents, List<List<Integer>> feedbacks, List<Integer> solucio) throws PresentationException {
        if (intents.size() > sequenciaIntentList.size() || feedbacks.size() > sequenciaFeedbackList.size())
            throw new SequenciaNoExistent();
        for (int i = 0; i < intents.size(); i++)
            sequenciaIntentList.get(i).setSequenciaColors(intents.get(i));
        for (int i = 0; i < feedbacks.size(); i++)
            sequenciaFeedbackList.get(i).setSequenciaColors(feedbacks.get(i));
        sequenciaSolucio.setSequenciaColors(solucio);
    }

    void setIntentEnabled(Integer index, Boolean enabled) throws SequenciaNoExistent {
        if (index < 0 || index >= sequenciaIntentList.size())
            throw new SequenciaNoExistent();
        sequenciaIntentList.get(index).setEnabled(enabled);
    }

    void attachToBoles(Observer o) {
        for (SequenciaPanel intent : sequenciaIntentList)
            intent.attachToBoles(o);
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
        buttonOK1 = new JButton();
        buttonOK1.setBorderPainted(true);
        Font buttonOK1Font = this.$$$getFont$$$(null, -1, 20, buttonOK1.getFont());
        if (buttonOK1Font != null) buttonOK1.setFont(buttonOK1Font);
        buttonOK1.setHorizontalAlignment(0);
        buttonOK1.setHorizontalTextPosition(0);
        buttonOK1.setIconTextGap(0);
        buttonOK1.setPreferredSize(new Dimension(30, 30));
        buttonOK1.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK1, gbc);
        buttonOK0 = new JButton();
        buttonOK0.setBorderPainted(true);
        Font buttonOK0Font = this.$$$getFont$$$(null, -1, 20, buttonOK0.getFont());
        if (buttonOK0Font != null) buttonOK0.setFont(buttonOK0Font);
        buttonOK0.setHorizontalAlignment(0);
        buttonOK0.setHorizontalTextPosition(0);
        buttonOK0.setIconTextGap(0);
        buttonOK0.setPreferredSize(new Dimension(30, 30));
        buttonOK0.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK0, gbc);
        buttonOK2 = new JButton();
        buttonOK2.setBorderPainted(true);
        Font buttonOK2Font = this.$$$getFont$$$(null, -1, 20, buttonOK2.getFont());
        if (buttonOK2Font != null) buttonOK2.setFont(buttonOK2Font);
        buttonOK2.setHorizontalAlignment(0);
        buttonOK2.setHorizontalTextPosition(0);
        buttonOK2.setIconTextGap(0);
        buttonOK2.setPreferredSize(new Dimension(30, 30));
        buttonOK2.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK2, gbc);
        buttonOK3 = new JButton();
        buttonOK3.setBorderPainted(true);
        Font buttonOK3Font = this.$$$getFont$$$(null, -1, 20, buttonOK3.getFont());
        if (buttonOK3Font != null) buttonOK3.setFont(buttonOK3Font);
        buttonOK3.setHorizontalAlignment(0);
        buttonOK3.setHorizontalTextPosition(0);
        buttonOK3.setIconTextGap(0);
        buttonOK3.setPreferredSize(new Dimension(30, 30));
        buttonOK3.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK3, gbc);
        buttonOK4 = new JButton();
        buttonOK4.setBorderPainted(true);
        Font buttonOK4Font = this.$$$getFont$$$(null, -1, 20, buttonOK4.getFont());
        if (buttonOK4Font != null) buttonOK4.setFont(buttonOK4Font);
        buttonOK4.setHorizontalAlignment(0);
        buttonOK4.setHorizontalTextPosition(0);
        buttonOK4.setIconTextGap(0);
        buttonOK4.setPreferredSize(new Dimension(30, 30));
        buttonOK4.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK4, gbc);
        buttonOK5 = new JButton();
        buttonOK5.setBorderPainted(true);
        Font buttonOK5Font = this.$$$getFont$$$(null, -1, 20, buttonOK5.getFont());
        if (buttonOK5Font != null) buttonOK5.setFont(buttonOK5Font);
        buttonOK5.setHorizontalAlignment(0);
        buttonOK5.setHorizontalTextPosition(0);
        buttonOK5.setIconTextGap(0);
        buttonOK5.setPreferredSize(new Dimension(30, 30));
        buttonOK5.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK5, gbc);
        buttonOK6 = new JButton();
        buttonOK6.setBorderPainted(true);
        Font buttonOK6Font = this.$$$getFont$$$(null, -1, 20, buttonOK6.getFont());
        if (buttonOK6Font != null) buttonOK6.setFont(buttonOK6Font);
        buttonOK6.setHorizontalAlignment(0);
        buttonOK6.setHorizontalTextPosition(0);
        buttonOK6.setIconTextGap(0);
        buttonOK6.setPreferredSize(new Dimension(30, 30));
        buttonOK6.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK6, gbc);
        buttonOK7 = new JButton();
        buttonOK7.setBorderPainted(true);
        Font buttonOK7Font = this.$$$getFont$$$(null, -1, 20, buttonOK7.getFont());
        if (buttonOK7Font != null) buttonOK7.setFont(buttonOK7Font);
        buttonOK7.setHorizontalAlignment(0);
        buttonOK7.setHorizontalTextPosition(0);
        buttonOK7.setIconTextGap(0);
        buttonOK7.setPreferredSize(new Dimension(30, 30));
        buttonOK7.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK7, gbc);
        buttonOK8 = new JButton();
        buttonOK8.setBorderPainted(true);
        Font buttonOK8Font = this.$$$getFont$$$(null, -1, 20, buttonOK8.getFont());
        if (buttonOK8Font != null) buttonOK8.setFont(buttonOK8Font);
        buttonOK8.setHorizontalAlignment(0);
        buttonOK8.setHorizontalTextPosition(0);
        buttonOK8.setIconTextGap(0);
        buttonOK8.setPreferredSize(new Dimension(30, 30));
        buttonOK8.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK8, gbc);
        buttonOK9 = new JButton();
        buttonOK9.setBorderPainted(true);
        Font buttonOK9Font = this.$$$getFont$$$(null, -1, 20, buttonOK9.getFont());
        if (buttonOK9Font != null) buttonOK9.setFont(buttonOK9Font);
        buttonOK9.setHorizontalAlignment(0);
        buttonOK9.setHorizontalTextPosition(0);
        buttonOK9.setIconTextGap(0);
        buttonOK9.setPreferredSize(new Dimension(30, 30));
        buttonOK9.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK9, gbc);
        buttonOK10 = new JButton();
        buttonOK10.setBorderPainted(true);
        Font buttonOK10Font = this.$$$getFont$$$(null, -1, 20, buttonOK10.getFont());
        if (buttonOK10Font != null) buttonOK10.setFont(buttonOK10Font);
        buttonOK10.setHorizontalAlignment(0);
        buttonOK10.setHorizontalTextPosition(0);
        buttonOK10.setIconTextGap(0);
        buttonOK10.setPreferredSize(new Dimension(30, 30));
        buttonOK10.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK10, gbc);
        buttonOK11 = new JButton();
        buttonOK11.setBorderPainted(true);
        Font buttonOK11Font = this.$$$getFont$$$(null, -1, 20, buttonOK11.getFont());
        if (buttonOK11Font != null) buttonOK11.setFont(buttonOK11Font);
        buttonOK11.setHorizontalAlignment(0);
        buttonOK11.setHorizontalTextPosition(0);
        buttonOK11.setIconTextGap(0);
        buttonOK11.setPreferredSize(new Dimension(30, 30));
        buttonOK11.setText("✓");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK11, gbc);
        sequenciaSolucio = new SequenciaPanel();
        panel.add(sequenciaSolucio.$$$getRootComponent$$$(), BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
