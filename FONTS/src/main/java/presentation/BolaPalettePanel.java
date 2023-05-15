package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BolaPalettePanel implements Observer {
    private JPanel panel;
    private BolaButton buttonNul;
    private BolaButton buttonBlanc;
    private BolaButton buttonNegre;
    private BolaButton buttonVermell;
    private BolaButton buttonBlau;
    private BolaButton buttonTaronja;
    private BolaButton buttonRosa;

    private BolaColor selectedColor;

    private final List<BolaButton> buttonBolaList;

    BolaPalettePanel() {
        $$$setupUI$$$();
        buttonBolaList = new ArrayList<>(List.of(buttonNul, buttonBlanc, buttonNegre, buttonVermell, buttonBlau, buttonTaronja, buttonRosa));
        initComponents();
    }

    private void initComponents() {
        buttonNul.setBolaColor(BolaColor.NUL);
        buttonBlanc.setBolaColor(BolaColor.BLANC);
        buttonNegre.setBolaColor(BolaColor.NEGRE);
        buttonVermell.setBolaColor(BolaColor.VERMELL);
        buttonBlau.setBolaColor(BolaColor.BLAU);
        buttonTaronja.setBolaColor(BolaColor.TARONJA);
        buttonRosa.setBolaColor(BolaColor.ROSA);

        for (int i = 0; i < buttonBolaList.size(); i++) {
            buttonBolaList.get(i).setID("P" + i);
            buttonBolaList.get(i).Attach(this);
            buttonBolaList.get(i).setSelected(false);
        }
    }

    Boolean isColorSelected() {
        return selectedColor != null;
    }

    BolaColor getSelectedColor() {
        return selectedColor;
    }

    void unselectAllColors() {
        for (BolaButton button : buttonBolaList)
            button.setSelected(false);
        selectedColor = null;
    }

    @Override
    public void Update(Subject s) {
        int id = Integer.parseInt(((BolaButton) s).getID().substring(1));
        unselectAllColors();
        buttonBolaList.get(id).setSelected(true);
        selectedColor = buttonBolaList.get(id).getBolaColor();
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
        buttonNul = new BolaButton();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(buttonNul.$$$getRootComponent$$$(), gbc);
        buttonBlanc = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(buttonBlanc.$$$getRootComponent$$$(), gbc);
        buttonNegre = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(buttonNegre.$$$getRootComponent$$$(), gbc);
        buttonVermell = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(buttonVermell.$$$getRootComponent$$$(), gbc);
        buttonBlau = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(buttonBlau.$$$getRootComponent$$$(), gbc);
        buttonTaronja = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(buttonTaronja.$$$getRootComponent$$$(), gbc);
        buttonRosa = new BolaButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(buttonRosa.$$$getRootComponent$$$(), gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
