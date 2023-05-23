package presentation;

import exceptions.presentation.BolaNoExistentException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Panell amb les opcions per crear una nova partida com a maker
 * @author Kamil Przybyszewski
 */
class NovaPartidaMakerPanel extends JPanel implements Observer {

    /**
     * Panell contenidor
     */
    private JPanel panel;
    /**
     * Botó d'opció per seleccionar FiveGuess com l'algorisme del bot per a la nova partida
     */
    private JRadioButton radioButtonFiveGuess;
    /**
     * Botó d'opció per seleccionar Genetic com l'algorisme del bot per a la nova partida
     */
    private JRadioButton radioButtonGenetic;
    /**
     * Component amb els botons de selecció de la seqüència solució per a la nova partida
     */
    private SequenciaPanel solucioPanel;

    /**
     * Llista amb la seqüència solució per a la nova partida
     */
    private List<Integer> solucioList;

    /**
     * Constructor per defecte del panell
     */
    NovaPartidaMakerPanel() {
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per inicialitzar els components del panell
     */
    private void initComponents() {
        solucioList = new ArrayList<>();
        for (int i = 0; i < SequenciaPanel.SEQUENCIA_SIZE; i++) {
            solucioPanel.setBolaID(i, "S" + i);
            solucioList.add(BolaColor.NUL.getNumber());
        }
        solucioPanel.attachToBoles(this);
    }

    /**
     * Mètode per retornar l'algorisme del bot seleccionat per a la nova partida
     * @return algorisme tal que el seu botó d'opció està seleccionat
     */
    Integer getAlgorisme() {
        if (radioButtonFiveGuess.isSelected()) return 1;
        else return 2; //radioButtonGenetic.isSelected()
    }

    /**
     * Mètode per retornar la seqüència solució seleccionada per a la nova partida
     * @return seqüència configurada mitjançant la component amb els botons de selecció de la solució
     */
    List<Integer> getSolucio() {
        return solucioPanel.getSequenciaColors();
    }

    /**
     * Mètode perquè per cada actualització dels botons de selecció de la solució, adoptin el color seleccionat
     * @param s Botó de selecció del qual prové la notificació
     */
    @Override
    public void Update(Subject s) {
        int index = Integer.parseInt(((BolaButton) s).getID().substring(1));
        try {
            int newNumberColor = (solucioList.get(index) + 1) % BolaColor.getNumColors();
            if (newNumberColor == 0) newNumberColor = 1;
            solucioList.set(index, newNumberColor);
            solucioPanel.setBolaColor(index, BolaColor.findByNumber(newNumberColor));
        } catch (BolaNoExistentException e) {
            // TODO wtf puc fer aquí
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
        panel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setText("Seqüència Solució");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label1, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(panel1, gbc);
        radioButtonFiveGuess = new JRadioButton();
        radioButtonFiveGuess.setSelected(true);
        radioButtonFiveGuess.setText("FiveGuess");
        panel1.add(radioButtonFiveGuess);
        radioButtonGenetic = new JRadioButton();
        radioButtonGenetic.setSelected(false);
        radioButtonGenetic.setText("Genetic");
        panel1.add(radioButtonGenetic);
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Algorisme");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label2, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(spacer1, gbc);
        solucioPanel = new SequenciaPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(solucioPanel.$$$getRootComponent$$$(), gbc);
        final JLabel label3 = new JLabel();
        label3.setEnabled(false);
        Font label3Font = this.$$$getFont$$$(null, -1, 12, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setHorizontalAlignment(0);
        label3.setText("Clica els botons per canviar el color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label3, gbc);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonFiveGuess);
        buttonGroup.add(radioButtonGenetic);
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
