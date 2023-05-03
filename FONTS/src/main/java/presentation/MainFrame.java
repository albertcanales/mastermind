package presentation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    MainFrame() {
        super();
        setTitle("Mastermind");
        setSize(new Dimension(400, 600));
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void setContent(Container contentPane) {
        setContentPane(contentPane);
        revalidate();
    }
}
