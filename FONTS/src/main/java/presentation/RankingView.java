package presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class RankingView {
    /**
     * Controlador de presentació
     */
    private final ControladorPresentacio controladorPresentacio;
    /**
     * Panell contenidor
     */
    private JPanel panel;
    /**
     * Botó per tornar a la pantalla d'inici o home, segons si un usuari ha iniciat sessió
     */
    private JButton buttonTorna;
    private JTabbedPane tabbedPane;

    private ArrayList<JTable> rankingTableList;

    private final int NUM_RANKINGS = 3;
    private final int MAX_ROWS = 20;

    /**
     * Constructor per defecte de la vista
     */
    RankingView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }
    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Rànquing");
    }
    /**
     * Mètode per inicialitzar els components de la vista
     */
    private void initComponents() {
        initRankingTableList();
        initRanking();
        initTabbedPanel();

        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showInitialOrHomeView());
    }

    private void initRankingTableList() {
        rankingTableList = new ArrayList<>();
        for (int i = 0; i < NUM_RANKINGS; ++i) rankingTableList.add(new JTable());
    }

    private void initTabbedPanel() {
        tabbedPane.addTab("Fàcil", new JScrollPane(rankingTableList.get(0)));
        tabbedPane.addTab("Mig", new JScrollPane(rankingTableList.get(1)));
        tabbedPane.addTab("Difícil", new JScrollPane(rankingTableList.get(2)));
    }

    private void initRanking() {
        List<List<List<String>>> ranking = controladorPresentacio.getRanquings(MAX_ROWS);

        for (int i = 0; i < NUM_RANKINGS; ++i) fillRanking(i, ranking.get(i));
    }

    private void fillRanking(int index, List<List<String>> ranking) {
        String[] columnNames = {"#", "Username", "Intents", "Temps"};
        int rankingSize = ranking.size();
        String[][] rankingArray = new String[rankingSize][];
        for (int i = 0; i < rankingSize; ++i) {
            List<String> rankingWithPositon = new ArrayList<>(ranking.get(i));
            int position = i + 1;
            rankingWithPositon.add(0, Integer.toString(position));
            rankingArray[i] = rankingWithPositon.toArray(new String[0]);
        }
        JTable rankingTable = new JTable(rankingArray, columnNames);
        rankingTable.setDefaultEditor(Object.class, null);

        rankingTableList.set(index, rankingTable);
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
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(panel1, BorderLayout.NORTH);
        buttonTorna = new JButton();
        buttonTorna.setText("Torna");
        panel1.add(buttonTorna);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 10, 0, 10), -1, -1));
        panel.add(panel2, BorderLayout.CENTER);
        tabbedPane = new JTabbedPane();
        panel2.add(tabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
