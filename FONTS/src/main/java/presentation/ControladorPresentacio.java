package presentation;

import domain.ControladorDomini;

import javax.swing.*;

public class ControladorPresentacio {

    private final ControladorDomini controladorDomini;
    private final MainFrame mainFrame;

    ControladorPresentacio() {
        controladorDomini = new ControladorDomini();
        mainFrame = new MainFrame();
    }

    void run() {
        showInitialView();
    }

    void setContent(JPanel panel) {
        mainFrame.setContent(panel);
    }

    public void setTitle(String title) {
        mainFrame.setTitle(title);
    }

    void showInitialView() {
        InitialView initialView = new InitialView(this);
        initialView.show();
    }

    void showRegisterView() {
        RegisterView registerView = new RegisterView(this);
        registerView.show();
    }

}
