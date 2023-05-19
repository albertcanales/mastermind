package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Vista de la Zona de l'usuari
 *
 * @author Mar Gonzàlez Català
 */
public class EstadistiquesView {

    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;

    /**
     * Panell contenidor
     */
    private JPanel panel;

    /**
     * Botó per tornar a la vista anterior
     */
    private JButton buttonTorna;

    /**
     * Panell contenidor de les estadístiques en rol Breaker
     */
    private JTabbedPane tabbedPaneBreaker;

    /**
     * Panell contenidor de les estadístiques en rol Maker
     */
    private JTabbedPane tabbedPaneMaker;

    /**
     * Etiqueta que mostra el rècord personal en mode de joc Fàcil
     */
    private JLabel JLabelRecordPersonalFacil;

    /**
     * Etiqueta que mostra el temps jugat en mode de joc Fàcil
     */
    private JLabel JLabelTempsFacil;

    /**
     * Etiqueta que mostra el nombre de partides guanyades en mode de joc Fàcil
     */
    private JLabel JLabelGuanyadesFacil;

    /**
     * Etiqueta que mostra el nombre de partides perdudes en mode de joc Fàcil
     */
    private JLabel JLabelPerdudesFacil;

    /**
     * Etiqueta que mostra la ratxa de victòries en mode de joc Fàcil
     */
    private JLabel JLabelRatxaVictoriesFacil;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Breaker i mode de joc Fàcil
     */
    private JLabel JLabelMitjanaIntentsFacil;

    /**
     * Etiqueta que mostra el rècord personal en mode de joc Mig
     */
    private JLabel JLabelRecordPersonalMig;

    /**
     * Etiqueta que mostra el temps jugat en mode de joc Mig
     */
    private JLabel JLabelTempsMig;

    /**
     * Etiqueta que mostra el nombre de partides guanyades en mode de joc Mig
     */
    private JLabel JLabelGuanyadesMig;

    /**
     * Etiqueta que mostra el nombre de partides perdudes en mode de joc Mig
     */
    private JLabel JLabelPerdudesMig;

    /**
     * Etiqueta que mostra la ratxa de victòries en mode de joc Mig
     */
    private JLabel JLabelRatxaVictoriesMig;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Breaker i mode de joc Mig
     */
    private JLabel JLabelMitjanaIntentsMig;

    /**
     * Etiqueta que mostra el rècord personal en mode de joc Difícil
     */
    private JLabel JLabelRecordPersonalDificil;

    /**
     * Etiqueta que mostra el temps jugat en mode de joc Difícil
     */
    private JLabel JLabelTempsDificil;

    /**
     * Etiqueta que mostra el nombre de partides guanyades en mode de joc Difícil
     */
    private JLabel JLabelGuanyadesDificil;

    /**
     * Etiqueta que mostra el nombre de partides perdudes en mode de joc Difícil
     */
    private JLabel JLabelPerdudesDificil;

    /**
     * Etiqueta que mostra la ratxa de victòries en mode de joc Difícil
     */
    private JLabel JLabelRatxaVictoriesDificil;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Breaker i mode de joc Difícil
     */
    private JLabel JLabelMitjanaIntentsDificil;

    /**
     * Etiqueta que mostra el rècord personal
     */
    private JLabel JLabelRecordPersonalTotal;

    /**
     * Etiqueta que mostra el temps jugat
     */
    private JLabel JLabelTempsTotal;

    /**
     * Etiqueta que mostra el nombre de partides perdudes
     */
    private JLabel JLabelGuanyadesTotal;

    /**
     * Etiqueta que mostra el nombre de partides perdudes
     */
    private JLabel JLabelPerdudesTotal;

    /**
     * Etiqueta que mostra la ratxa de victòries
     */
    private JLabel JLabelRatxaVictoriesTotal;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Breaker
     */
    private JLabel JLabelMitjanaIntentsBreakerTotal;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Maker i amb l'algorisme FiveGuess
     */
    private JLabel JLabelMitjanaIntentsFiveGuess;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Maker i amb l'algorisme Genetic
     */
    private JLabel JLabelMitjanaIntentsGenetic;

    /**
     * Etiqueta que mostra la mitjana d'intents en rol Maker
     */
    private JLabel JLabelMitjanaIntentsMakerTotal;
    private JButton buttonEsborraUsuari;
    private JLabel labelUserName;
    private JTextArea textAreaUserName;

    /**
     * Constructor per defecte de la vista
     */
    EstadistiquesView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }

    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Estadístiques");
    }

    /**
     * Mètode per inicialitzar els components de la vista
     */
    private void initComponents() {
        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showHomeView());

        textAreaUserName.setText("Aquí tens les teves dades, " + controladorPresentacio.getUserName());

        setRecordPersonal();
        setTemps();
        setGuanyades();
        setPerdudes();
        setRatxaVictories();
        setMitjanaIntentsBreaker();
        setMitjanaIntentsMaker();
        buttonEsborraUsuari.addActionListener(actionEvent -> buttonEsborraUsuariClick());
    }

    /**
     * Mètode que escriu el rècord personal de l'usuari
     */
    private void setRecordPersonal() {
        List<Integer> record = controladorPresentacio.getPersonalRecord();
        JLabelRecordPersonalFacil.setText(Integer.toString(record.get(0)));
        JLabelRecordPersonalMig.setText(Integer.toString(record.get(1)));
        JLabelRecordPersonalDificil.setText(Integer.toString(record.get(2)));
        JLabelRecordPersonalTotal.setText(Integer.toString(record.get(3)));
    }

    /**
     * Mètode que escriu el temps jugat de l'usuari
     */
    private void setTemps() {
        List<Long> temps = controladorPresentacio.getTimePlayed();
        JLabelTempsFacil.setText(Long.toString(temps.get(0)));
        JLabelTempsMig.setText(Long.toString(temps.get(1)));
        JLabelTempsDificil.setText(Long.toString(temps.get(2)));
        JLabelTempsTotal.setText(Long.toString(temps.get(3)));
    }

    /**
     * Mètode que escriu el nombre de partides guanyades de l'usuari
     */
    private void setGuanyades() {
        List<Integer> guanyades = controladorPresentacio.getWonGames();
        JLabelGuanyadesFacil.setText(Integer.toString(guanyades.get(0)));
        JLabelGuanyadesMig.setText(Integer.toString(guanyades.get(1)));
        JLabelGuanyadesDificil.setText(Integer.toString(guanyades.get(2)));
        JLabelGuanyadesTotal.setText(Integer.toString(guanyades.get(3)));
    }

    /**
     * Mètode que escriu el nombre de partides perdudes de l'usuari
     */
    private void setPerdudes() {
        List<Integer> perdudes = controladorPresentacio.getLostGames();
        JLabelPerdudesFacil.setText(Integer.toString(perdudes.get(0)));
        JLabelPerdudesMig.setText(Integer.toString(perdudes.get(1)));
        JLabelPerdudesDificil.setText(Integer.toString(perdudes.get(2)));
        JLabelPerdudesTotal.setText(Integer.toString(perdudes.get(3)));
    }

    /**
     * Mètode que escriu la ratxa de victòries de l'usuari
     */
    private void setRatxaVictories() {
        List<Integer> winStreak = controladorPresentacio.getWinstreak();
        JLabelRatxaVictoriesFacil.setText(Integer.toString(winStreak.get(0)));
        JLabelRatxaVictoriesMig.setText(Integer.toString(winStreak.get(1)));
        JLabelRatxaVictoriesDificil.setText(Integer.toString(winStreak.get(2)));
        JLabelRatxaVictoriesTotal.setText(Integer.toString(winStreak.get(3)));
    }

    /**
     * Mètode que escriu la mitjana d'intents en rol Breaker de l'usuari
     */
    private void setMitjanaIntentsBreaker() {
        List<Double> average = controladorPresentacio.getAverageAsBreaker();
        JLabelMitjanaIntentsFacil.setText(Double.toString(average.get(0)));
        JLabelMitjanaIntentsMig.setText(Double.toString(average.get(1)));
        JLabelMitjanaIntentsDificil.setText(Double.toString(average.get(2)));
        JLabelMitjanaIntentsBreakerTotal.setText(Double.toString(average.get(3)));
    }

    /**
     * Mètode que escriu la mitjana d'intents en rol Maker de l'usuari
     */
    private void setMitjanaIntentsMaker() {
        List<Double> average = controladorPresentacio.getAverageAsMaker();
        JLabelMitjanaIntentsFiveGuess.setText(Double.toString(average.get(0)));
        JLabelMitjanaIntentsGenetic.setText(Double.toString(average.get(1)));
        JLabelMitjanaIntentsMakerTotal.setText(Double.toString(average.get(2)));
    }

    /**
     * Mètode que elimina l'usuari actual i torna a la vista inicial
     */
    void buttonEsborraUsuariClick() {
        if (controladorPresentacio.showYesNoDialog("Esborrar usuari", "Segur que vols esborrar el teu usuari?" +
                " També esapareixerà dels rànquings! Aquesta acció és irreversible.")) {
            controladorPresentacio.esborrarUsuari();
            controladorPresentacio.showInitialView();
        }
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 10, 0, 10), -1, -1));
        panel.add(panel2, BorderLayout.CENTER);
        tabbedPaneBreaker = new JTabbedPane();
        panel2.add(tabbedPaneBreaker, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Fàcil", panel3);
        final JLabel label1 = new JLabel();
        label1.setText("Rècord personal:");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Temps:");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Guanyades:");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Perdudes:");
        panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Ratxa de victòries:");
        panel3.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Mitjana d'intents:");
        panel3.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalFacil = new JLabel();
        JLabelRecordPersonalFacil.setText("");
        panel3.add(JLabelRecordPersonalFacil, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsFacil = new JLabel();
        JLabelTempsFacil.setText("");
        panel3.add(JLabelTempsFacil, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesFacil = new JLabel();
        JLabelGuanyadesFacil.setText("");
        panel3.add(JLabelGuanyadesFacil, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesFacil = new JLabel();
        JLabelPerdudesFacil.setText("");
        panel3.add(JLabelPerdudesFacil, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesFacil = new JLabel();
        JLabelRatxaVictoriesFacil.setText("");
        panel3.add(JLabelRatxaVictoriesFacil, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsFacil = new JLabel();
        JLabelMitjanaIntentsFacil.setText("");
        panel3.add(JLabelMitjanaIntentsFacil, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Mig", panel4);
        final JLabel label7 = new JLabel();
        label7.setText("Rècord personal:");
        panel4.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Temps:");
        panel4.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Guanyades:");
        panel4.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Perdudes:");
        panel4.add(label10, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Ratxa de victòries:");
        panel4.add(label11, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Mitjana d'intents:");
        panel4.add(label12, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalMig = new JLabel();
        JLabelRecordPersonalMig.setText("");
        panel4.add(JLabelRecordPersonalMig, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsMig = new JLabel();
        JLabelTempsMig.setText("");
        panel4.add(JLabelTempsMig, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesMig = new JLabel();
        JLabelGuanyadesMig.setText("");
        panel4.add(JLabelGuanyadesMig, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesMig = new JLabel();
        JLabelPerdudesMig.setText("");
        panel4.add(JLabelPerdudesMig, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesMig = new JLabel();
        JLabelRatxaVictoriesMig.setText("");
        panel4.add(JLabelRatxaVictoriesMig, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsMig = new JLabel();
        JLabelMitjanaIntentsMig.setText("");
        panel4.add(JLabelMitjanaIntentsMig, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Difícil", panel5);
        final JLabel label13 = new JLabel();
        label13.setText("Rècord personal:");
        panel5.add(label13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Temps:");
        panel5.add(label14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Guanyades:");
        panel5.add(label15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Perdudes:");
        panel5.add(label16, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Ratxa de victòries:");
        panel5.add(label17, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Mitjana d'intents:");
        panel5.add(label18, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalDificil = new JLabel();
        JLabelRecordPersonalDificil.setText("");
        panel5.add(JLabelRecordPersonalDificil, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsDificil = new JLabel();
        JLabelTempsDificil.setText("");
        panel5.add(JLabelTempsDificil, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesDificil = new JLabel();
        JLabelGuanyadesDificil.setText("");
        panel5.add(JLabelGuanyadesDificil, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesDificil = new JLabel();
        JLabelPerdudesDificil.setText("");
        panel5.add(JLabelPerdudesDificil, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesDificil = new JLabel();
        JLabelRatxaVictoriesDificil.setText("");
        panel5.add(JLabelRatxaVictoriesDificil, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsDificil = new JLabel();
        JLabelMitjanaIntentsDificil.setText("");
        panel5.add(JLabelMitjanaIntentsDificil, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneBreaker.addTab("Total", panel6);
        final JLabel label19 = new JLabel();
        label19.setText("Rècord personal:");
        panel6.add(label19, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Temps:");
        panel6.add(label20, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("Guanyades:");
        panel6.add(label21, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        label22.setText("Perdudes:");
        panel6.add(label22, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        label23.setText("Ratxa de victòries:");
        panel6.add(label23, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        label24.setText("Mitjana d'intents:");
        panel6.add(label24, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRecordPersonalTotal = new JLabel();
        JLabelRecordPersonalTotal.setText("");
        panel6.add(JLabelRecordPersonalTotal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelTempsTotal = new JLabel();
        JLabelTempsTotal.setText("");
        panel6.add(JLabelTempsTotal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelGuanyadesTotal = new JLabel();
        JLabelGuanyadesTotal.setText("");
        panel6.add(JLabelGuanyadesTotal, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelPerdudesTotal = new JLabel();
        JLabelPerdudesTotal.setText("");
        panel6.add(JLabelPerdudesTotal, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelRatxaVictoriesTotal = new JLabel();
        JLabelRatxaVictoriesTotal.setText("");
        panel6.add(JLabelRatxaVictoriesTotal, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsBreakerTotal = new JLabel();
        JLabelMitjanaIntentsBreakerTotal.setText("");
        panel6.add(JLabelMitjanaIntentsBreakerTotal, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPaneMaker = new JTabbedPane();
        panel2.add(tabbedPaneMaker, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("FiveGuess", panel7);
        final JLabel label25 = new JLabel();
        label25.setText("Mitjana d'intents:");
        panel7.add(label25, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsFiveGuess = new JLabel();
        JLabelMitjanaIntentsFiveGuess.setText("");
        panel7.add(JLabelMitjanaIntentsFiveGuess, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("Genetic", panel8);
        final JLabel label26 = new JLabel();
        label26.setText("Mitjana d'intents:");
        panel8.add(label26, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsGenetic = new JLabel();
        JLabelMitjanaIntentsGenetic.setText("");
        panel8.add(JLabelMitjanaIntentsGenetic, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPaneMaker.addTab("Total", panel9);
        final JLabel label27 = new JLabel();
        label27.setText("Mitjana d'intents:");
        panel9.add(label27, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        JLabelMitjanaIntentsMakerTotal = new JLabel();
        JLabelMitjanaIntentsMakerTotal.setText("");
        panel9.add(JLabelMitjanaIntentsMakerTotal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAreaUserName = new JTextArea();
        textAreaUserName.setEditable(false);
        textAreaUserName.setLineWrap(true);
        textAreaUserName.setOpaque(false);
        panel2.add(textAreaUserName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(150, 20), null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel.add(panel10, BorderLayout.SOUTH);
        buttonEsborraUsuari = new JButton();
        buttonEsborraUsuari.setText("Esborra usuari");
        panel10.add(buttonEsborraUsuari);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
