package presentation;

import javax.swing.*;
import java.awt.*;

public class NormesView {
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
    private JEditorPane editorPaneNormes;
    private JScrollBar scrollBar1;

    /**
     * Constructor per defecte de la vista
     */
    NormesView() {
        controladorPresentacio = ControladorPresentacio.getInstance();
        $$$setupUI$$$();
        initComponents();
    }
    /**
     * Mètode per mostrar la vista
     */
    void show() {
        controladorPresentacio.setContent(panel);
        controladorPresentacio.setTitle("Normes del Joc");
    }
    /**
     * Mètode per inicialitzar els components de la vista
     */
    void initComponents() {
        buttonTorna.addActionListener(actionEvent -> controladorPresentacio.showInitialOrHomeView());
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
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, BorderLayout.CENTER);
        editorPaneNormes = new JEditorPane();
        editorPaneNormes.setContentType("text/html");
        editorPaneNormes.setEditable(false);
        editorPaneNormes.setText("<html>\n  <head>\n    \n  </head>\n  <body>\n    <h1>\n      Bonum integritas corporis: misera debilitas.\n    </h1>\n    <p>\n      Lorem ipsum dolor sit amet, consectetur adipiscing elit. <i>Nunc vides, \n      quid faciat.</i> Duo Reges: constructio interrete. Bork De illis, cum \n      volemus.\n    </p>\n    <p>\n      Ut id aliis narrare gestiant? Neutrum vero, inquit ille. Ratio quidem \n      vestra sic cogit. Nec vero alia sunt quaerenda contra Carneadeam illam \n      sententiam. Non laboro, inquit, de nomine.\n    </p>\n    <ol>\n      <li>\n        Itaque rursus eadem ratione, qua sum paulo ante usus, haerebitis.\n      </li>\n      <li>\n        Transfer idem ad modestiam vel temperantiam, quae est moderatio \n        cupiditatum rationi oboediens.\n      </li>\n      <li>\n        Quam illa ardentis amores excitaret sui! Cur tandem?\n      </li>\n      <li>\n        Ita fit beatae vitae domina fortuna, quam Epicurus ait exiguam \n        intervenire sapienti.\n      </li>\n      <li>\n        De malis autem et bonis ab iis animalibus, quae nondum depravata sint, \n        ait optime iudicari.\n      </li>\n    </ol>\n    <ul>\n      <li>\n        Atque ab isto capite fluere necesse est omnem rationem bonorum et \n        malorum.\n      </li>\n      <li>\n        Sed quid attinet de rebus tam apertis plura requirere?\n      </li>\n      <li>\n        Itaque hic ipse iam pridem est reiectus;\n      </li>\n      <li>\n        Deinde disputat, quod cuiusque generis animantium statui deceat \n        extremum.\n      </li>\n    </ul>\n    <p>\n      Si quicquam extra virtutem habeatur in bonis. Quam ob rem tandem, \n      inquit, non satisfacit? Tanta vis admonitionis inest in locis; <i>Sed \n      quid sentiat, non videtis.</i> Itaque fecimus. Age, inquies, ista parva \n      sunt.\n    </p>\n  </body>\n</html>\n");
        scrollPane1.setViewportView(editorPaneNormes);
        scrollBar1 = new JScrollBar();
        scrollBar1.setUnitIncrement(20);
        panel.add(scrollBar1, BorderLayout.EAST);
        scrollPane1.setVerticalScrollBar(scrollBar1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
