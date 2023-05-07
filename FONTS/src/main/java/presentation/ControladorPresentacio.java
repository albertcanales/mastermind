package presentation;

import domain.ControladorDomini;
import domain.exceptions.DomainException;
import domain.exceptions.NoGameSavedException;
import domain.exceptions.NotLoggedInException;
import persistance.exceptions.PersistanceException;

import javax.swing.*;

/**
 * Controlador de Presentació
 * @author Albert Canales
 */
public class ControladorPresentacio {

    private final ControladorDomini controladorDomini;
    private final MainFrame mainFrame;

    ControladorPresentacio() {
        ControladorDomini controladorDomini;
        mainFrame = new MainFrame();
        try {
            controladorDomini = new ControladorDomini();
        } catch (PersistanceException e) {
            controladorDomini = null;
        }
        this.controladorDomini = controladorDomini;
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
            showErrorDialog("No s'ha pogut registrar d'usuari");
            e.printStackTrace();
        }
    }

    public Boolean loginUser(String username, String password) {
        try {
            return controladorDomini.loginUser(username, password);
        } catch (DomainException e) {
            showErrorDialog("L'usuari no existeix");
            e.printStackTrace();
        }
        return false;
    }

    public void logoutUser() {
        try {
            controladorDomini.logoutUser();
        } catch (DomainException e) {
            showErrorDialog("No s'havia iniciat sessió");
            e.printStackTrace();
        }
    }

    public Boolean existsPartidaGuardada() {
        try {
            return controladorDomini.existsPartidaGuardada();
        } catch (DomainException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return false;
    }

    public void carregarPartida() {
        try {
            controladorDomini.carregarPartida();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut carregar la partida");
            e.printStackTrace();
        }
    }

    void run() {
        if(controladorDomini == null)
            showErrorDialog("No s'ha pogut iniciar la base de dades");
        else
            showHomeView();
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

    void showLoginView() {
        LoginView loginView = new LoginView(this);
        loginView.show();
    }

    void showHomeView() {
        HomeView homeView = new HomeView(this);
        homeView.show();
    }

    void showNormesView() {
         NormesView normesView = new NormesView(this);
         normesView.show();
    }

    void showRankingView() {
        // RankingView rankingView = new RankingView(this);
        // rankingView.show();
    }

    void showNovaPartidaView() {
        NovaPartidaView novaPartidaView = new NovaPartidaView(this);
        novaPartidaView.show();
    }

    void showWarningDialog(String title, String message) {
        mainFrame.showWarningDialog(title, message);
    }

    void showErrorDialog(String message) {
        mainFrame.showErrorDialog("Error Intern", message);
    }

}
