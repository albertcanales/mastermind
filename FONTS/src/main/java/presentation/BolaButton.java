package presentation;

import exceptions.domain.DomainException;

import javax.swing.*;
import java.awt.*;

/**
 * Representació de les boles del joc en forma de botó en la interfície de Swing
 * @author Albert Canales Ros
 */
class BolaButton extends Subject {

    /**
     * Botó de la interfície
     */
    private JButton button;

    /**
     * Color del qual es pinta el botó
     */
    private BolaColor bolaColor;

    /**
     * Panell contenidor del botó
     */
    private JPanel panel;

    /**
     * Constructor per defecte, no assigna cap color a la bola
     */
    BolaButton() {
        $$$setupUI$$$();
        setBolaColor(BolaColor.NONE);
    }

    /**
     * Mètode per canviar el color de la bola
     */
    void setBolaColor(BolaColor bolaColor) {
        this.bolaColor = bolaColor;
        if (bolaColor.color != null)
            button.setIcon(new ColorIcon(16, bolaColor.color));
        else
            button.setIcon(null);
    }

    /**
     * Getter del color de la bola
     */
    BolaColor getBolaColor() {
        return bolaColor;
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
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        button = new JButton();
        button.setActionCommand("");
        button.setLabel("");
        button.setPreferredSize(new Dimension(25, 25));
        button.setText("");
        panel.add(button);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    /**
     * Classe per pintar un cercle com a icona d'un component de Swing
     * @author Albert Canales Ros
     */
    private static class ColorIcon implements Icon {

        private final int size;
        private final Color color;

        public ColorIcon(int size, Color color) {
            this.size = size;
            this.color = color;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.fillOval(x, y, size, size);
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }
}

/**
 * Enum per encapsular els colors amb els quals es pinten de les boles del joc
 * El valor NUL (null) es fa servir quan no s'ha de pintar cap color.
 * @author Albert Canales Ros
 */
enum BolaColor {

    NONE(null), RED(Color.RED), GREEN(Color.green), BLUE(Color.blue);
    public final Color color;

    BolaColor(Color color) {
        this.color = color;
    }
}