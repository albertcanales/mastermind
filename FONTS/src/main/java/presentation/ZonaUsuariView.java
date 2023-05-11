package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class ZonaUsuariView {

    private final ControladorPresentacio controladorPresentacio;
    private JPanel panel;
    private JButton buttonTorna;
    private JTabbedPane tabbedPaneBreaker;
    private JTabbedPane tabbedPaneMaker;
    private JLabel JLabelRecordPersonalFacil;
    private JLabel JLabelTempsFacil;
    private JLabel JLabelGuanyadesFacil;
    private JLabel JLabelPerdudesFacil;
    private JLabel JLabelRatxaVictoriesFacil;
    private JLabel JLabelMitjanaIntentsFacil;
    private JLabel JLabelRecordPersonalMig;
    private JLabel JLabelTempsMig;
    private JLabel JLabelGuanyadesMig;
    private JLabel JLabelPerdudesMig;
    private JLabel JLabelRatxaVictoriesMig;
    private JLabel JLabelMitjanaIntentsMig;
    private JLabel JLabelRecordPersonalDificil;
    private JLabel JLabelTempsDificil;
    private JLabel JLabelGuanyadesDificil;
    private JLabel JLabelPerdudesDificil;
    private JLabel JLabelRatxaVictoriesDificil;
    private JLabel JLabelMitjanaIntentsDificil;
    private JLabel JLabelRecordPersonalTotal;
    private JLabel JLabelTempsTotal;
    private JLabel JLabelGuanyadesTotal;
    private JLabel JLabelPerdudesTotal;
    private JLabel JLabelRatxaVictoriesTotal;
    private JLabel JLabelMitjanaIntentsBreakerTotal;
    private JLabel JLabelMitjanaIntentsFiveGuess;
    private JLabel JLabelMitjanaIntentsGenetic;
    private JLabel JLabelMitjanaIntentsMakerTotal;


    ZonaUsuariView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Zona Usuari");
    }

    private void initComponents() {
        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showHomeView());
        setRecordPersonal();
        setTemps();
        setGuanyades();
        setPerdudes();
        setRatxaVictories();
        setMitjanaIntentsBreaker();
        setMitjanaIntentsMaker();
    }

    private void setRecordPersonal() {
        List<Integer> record = controladorPresentacio.getPersonalRecord();
        JLabelRecordPersonalFacil.setText(Integer.toString(record.get(0)));
        JLabelRecordPersonalMig.setText(Integer.toString(record.get(1)));
        JLabelRecordPersonalDificil.setText(Integer.toString(record.get(2)));
        Integer total = min(record.get(0), min(record.get(1),record.get(2)));
        JLabelRecordPersonalTotal.setText(Integer.toString(total));
    }

    private void setTemps() {
        List<Long> temps = controladorPresentacio.getTimePlayed();
        JLabelTempsFacil.setText(Long.toString(temps.get(0)));
        JLabelTempsFacil.setText(Long.toString(temps.get(1)));
        JLabelTempsFacil.setText(Long.toString(temps.get(2)));
        Long total = temps.get(0) + temps.get(1) + temps.get(2);
        JLabelTempsFacil.setText(Long.toString(total));
    }

    private void setGuanyades() {
        List<Integer> guanyades = controladorPresentacio.getWonGames();
        JLabelGuanyadesFacil.setText(Integer.toString(guanyades.get(0)));
        JLabelGuanyadesMig.setText(Integer.toString(guanyades.get(1)));
        JLabelGuanyadesDificil.setText(Integer.toString(guanyades.get(2)));
        Integer total = guanyades.get(0) + guanyades.get(1) + guanyades.get(2);
        JLabelGuanyadesTotal.setText(Integer.toString(total));
    }

    private void setPerdudes() {
        List<Integer> perdudes = controladorPresentacio.getLostGames();
        JLabelPerdudesFacil.setText(Integer.toString(perdudes.get(0)));
        JLabelPerdudesMig.setText(Integer.toString(perdudes.get(1)));
        JLabelPerdudesDificil.setText(Integer.toString(perdudes.get(2)));
        Integer total = perdudes.get(0) + perdudes.get(1) + perdudes.get(2);
        JLabelPerdudesTotal.setText(Integer.toString(total));
    }

    private void setRatxaVictories() {
        List<Integer> winStreak = controladorPresentacio.getWinstreak();
        JLabelRatxaVictoriesFacil.setText(Integer.toString(winStreak.get(0)));
        JLabelRatxaVictoriesMig.setText(Integer.toString(winStreak.get(1)));
        JLabelRatxaVictoriesDificil.setText(Integer.toString(winStreak.get(2)));
        Integer total = max(winStreak.get(0), max(winStreak.get(1), winStreak.get(2)));
        JLabelRatxaVictoriesTotal.setText(Integer.toString(total));
    }

    private void setMitjanaIntentsBreaker() {
        List<Double> average = controladorPresentacio.getAverageAsBreaker();
        JLabelMitjanaIntentsFacil.setText(Double.toString(average.get(0)));
        JLabelMitjanaIntentsMig.setText(Double.toString(average.get(1)));
        JLabelMitjanaIntentsDificil.setText(Double.toString(average.get(2)));
        Double total = (average.get(0) + average.get(1) + average.get(2))/3;
        JLabelMitjanaIntentsBreakerTotal.setText(Double.toString(total));
    }

    private void setMitjanaIntentsMaker() {
        List<Double> average = controladorPresentacio.getAverageAsMaker();
        JLabelMitjanaIntentsFiveGuess.setText(Double.toString(average.get(0)));
        JLabelMitjanaIntentsGenetic.setText(Double.toString(average.get(1)));
        Double total = (average.get(0) + average.get(1))/2;
        JLabelMitjanaIntentsMakerTotal.setText(Double.toString(total));
    }


// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!

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
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(panel1, BorderLayout.NORTH);
        buttonTorna = new JButton();
        buttonTorna.setHorizontalAlignment(0);
        buttonTorna.setText("Torna");
        panel1.add(buttonTorna);
        tabbedPaneBreaker = new JTabbedPane();
        panel.add(tabbedPaneBreaker, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Fàcil", panel2);
        final JLabel label1 = new JLabel();
        label1.setText("Rècord personal:");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Temps:");
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Guanyades:");
        panel2.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Perdudes:");
        panel2.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Ratxa de victòries:");
        panel2.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Mitjana d'intents:");
        panel2.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalFacil = new JLabel();
        JLabelRecordPersonalFacil.setText("");
        panel2.add(JLabelRecordPersonalFacil, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsFacil = new JLabel();
        JLabelTempsFacil.setText("");
        panel2.add(JLabelTempsFacil, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesFacil = new JLabel();
        JLabelGuanyadesFacil.setText("");
        panel2.add(JLabelGuanyadesFacil, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesFacil = new JLabel();
        JLabelPerdudesFacil.setText("");
        panel2.add(JLabelPerdudesFacil, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesFacil = new JLabel();
        JLabelRatxaVictoriesFacil.setText("");
        panel2.add(JLabelRatxaVictoriesFacil, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsFacil = new JLabel();
        JLabelMitjanaIntentsFacil.setText("");
        panel2.add(JLabelMitjanaIntentsFacil, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Mig", panel3);
        final JLabel label7 = new JLabel();
        label7.setText("Rècord personal:");
        panel3.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Temps:");
        panel3.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Guanyades:");
        panel3.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Perdudes:");
        panel3.add(label10, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Ratxa de victòries:");
        panel3.add(label11, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Mitjana d'intents:");
        panel3.add(label12, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalMig = new JLabel();
        JLabelRecordPersonalMig.setText("");
        panel3.add(JLabelRecordPersonalMig, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsMig = new JLabel();
        JLabelTempsMig.setText("");
        panel3.add(JLabelTempsMig, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesMig = new JLabel();
        JLabelGuanyadesMig.setText("");
        panel3.add(JLabelGuanyadesMig, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesMig = new JLabel();
        JLabelPerdudesMig.setText("");
        panel3.add(JLabelPerdudesMig, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesMig = new JLabel();
        JLabelRatxaVictoriesMig.setText("");
        panel3.add(JLabelRatxaVictoriesMig, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsMig = new JLabel();
        JLabelMitjanaIntentsMig.setText("");
        panel3.add(JLabelMitjanaIntentsMig, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Difícil", panel4);
        final JLabel label13 = new JLabel();
        label13.setText("Rècord personal:");
        panel4.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Temps:");
        panel4.add(label14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Guanyades:");
        panel4.add(label15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Perdudes:");
        panel4.add(label16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Ratxa de victòries:");
        panel4.add(label17, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Mitjana d'intents:");
        panel4.add(label18, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalDificil = new JLabel();
        JLabelRecordPersonalDificil.setText("");
        panel4.add(JLabelRecordPersonalDificil, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsDificil = new JLabel();
        JLabelTempsDificil.setText("");
        panel4.add(JLabelTempsDificil, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesDificil = new JLabel();
        JLabelGuanyadesDificil.setText("");
        panel4.add(JLabelGuanyadesDificil, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesDificil = new JLabel();
        JLabelPerdudesDificil.setText("");
        panel4.add(JLabelPerdudesDificil, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesDificil = new JLabel();
        JLabelRatxaVictoriesDificil.setText("");
        panel4.add(JLabelRatxaVictoriesDificil, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsDificil = new JLabel();
        JLabelMitjanaIntentsDificil.setText("");
        panel4.add(JLabelMitjanaIntentsDificil, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Total", panel5);
        final JLabel label19 = new JLabel();
        label19.setText("Rècord personal:");
        panel5.add(label19, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Temps:");
        panel5.add(label20, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Guanyades:");
        panel5.add(label21, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Perdudes:");
        panel5.add(label22, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("Ratxa de victòries:");
        panel5.add(label23, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        label24.setText("Mitjana d'intents:");
        panel5.add(label24, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalTotal = new JLabel();
        JLabelRecordPersonalTotal.setText("");
        panel5.add(JLabelRecordPersonalTotal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsTotal = new JLabel();
        JLabelTempsTotal.setText("");
        panel5.add(JLabelTempsTotal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesTotal = new JLabel();
        JLabelGuanyadesTotal.setText("");
        panel5.add(JLabelGuanyadesTotal, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesTotal = new JLabel();
        JLabelPerdudesTotal.setText("");
        panel5.add(JLabelPerdudesTotal, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesTotal = new JLabel();
        JLabelRatxaVictoriesTotal.setText("");
        panel5.add(JLabelRatxaVictoriesTotal, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsBreakerTotal = new JLabel();
        JLabelMitjanaIntentsBreakerTotal.setText("");
        panel5.add(JLabelMitjanaIntentsBreakerTotal, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPaneMaker = new JTabbedPane();
        panel.add(tabbedPaneMaker, BorderLayout.SOUTH);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("FiveGuess", panel6);
        final JLabel label25 = new JLabel();
        label25.setText("Mitjana d'intents:");
        panel6.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsFiveGuess = new JLabel();
        JLabelMitjanaIntentsFiveGuess.setText("");
        panel6.add(JLabelMitjanaIntentsFiveGuess, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("Genetic", panel7);
        final JLabel label26 = new JLabel();
        label26.setText("Mitjana d'intents:");
        panel7.add(label26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsGenetic = new JLabel();
        JLabelMitjanaIntentsGenetic.setText("");
        panel7.add(JLabelMitjanaIntentsGenetic, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("Total", panel8);
        final JLabel label27 = new JLabel();
        label27.setText("Mitjana d'intents:");
        panel8.add(label27, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsMakerTotal = new JLabel();
        JLabelMitjanaIntentsMakerTotal.setText("");
        panel8.add(JLabelMitjanaIntentsMakerTotal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
