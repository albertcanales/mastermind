package presentation;

import domain.ControladorDomini;
import domain.exceptions.DomainException;

import javax.swing.*;

public class ControladorPresentacio {

    private final ControladorDomini controladorDomini;
    private final MainFrame mainFrame;

    ControladorPresentacio() {
        controladorDomini = new ControladorDomini();
        mainFrame = new MainFrame();
    }

    public Boolean isValidUser(String username, String name, String password) {
        return controladorDomini.isValidUser(username, name, password);
    }

    public Boolean existsUser(String username) {
        return controladorDomini.existsUser(username);
    }

    public void registerUser(String username, String name, String password) {
        try {
            controladorDomini.registerUser(username, name, password);
        } catch (DomainException e) {
            showErrorDialog("Could not register");
            e.printStackTrace();
        }
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

    void showWarningDialog(String title, String message) {
        mainFrame.showWarningDialog(title, message);
    }

    void showErrorDialog(String message) {
        mainFrame.showErrorDialog("Internal Error", message);
    }

}
