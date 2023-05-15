package presentation;

import domain.ControladorDomini;
import exceptions.GeneralException;
import exceptions.domain.DomainException;
import exceptions.presentation.PresentationException;

import javax.swing.*;
import java.util.List;

/**
 * Controlador de Presentació
 * Segueix el patró de Singleton
 * @author Albert Canales
 */
public class ControladorPresentacio {

    /**
     * Instància de la classe per la implementació del patró Singleton
     */
    private static final ControladorPresentacio instance = new ControladorPresentacio();

    /**
     * Instància del controlador de domini
     */
    private final ControladorDomini controladorDomini;

    /**
     * Frame de l'aplicació
     */
    private final MainFrame mainFrame;

    /**
     * Constructor del Controlador de Presentació, privat perquè és un Singleton
     */
    private ControladorPresentacio() {
        ControladorDomini controladorDomini;
        mainFrame = new MainFrame();
        try {
            controladorDomini = new ControladorDomini();
        } catch (GeneralException e) {
            controladorDomini = null;
        }
        this.controladorDomini = controladorDomini;
    }

    /**
     * Mètode per obtenir l'única instància del controlador de presentació
     */
    public static ControladorPresentacio getInstance() {
        return instance;
    }

    /**
     * Mètode per saber si un usuari donat és vàlid (bon format)
     * @param username Username a comprovar
     * @param name Nom a comprovar
     */
    public Boolean isValidUser(String username, String name, String password) {
        return controladorDomini.isValidUser(username, name, password);
    }

    /**
     * Mètode per saber si un usuari existeix. Es mostra un error si hi ha problemes d'accés a la DB.
     * @param username Username a comprovar
     */
    public Boolean existsUser(String username) {
        try {
            return controladorDomini.existsUser(username);
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut comprovar si existeix l'usuari");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mètode per registrar un usuari. Es mostra un error si ja existeix.
     * @param username Username a registrar
     * @param name Nom a registrar
     * @param password Contrasenya a registrar
     */
    public void registerUser(String username, String name, String password) {
        try {
            controladorDomini.registerUser(username, name, password);
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut registrar l'usuari");
            e.printStackTrace();
        }
    }

    /**
     * Mètode per iniciar sessió d'un usuari. Es mostra un error si no existeix.
     * @param username Username per l'inici de sessió
     * @param password Contrasenya per l'inici de sessió
     */
    public Boolean loginUser(String username, String password) {
        try {
            return controladorDomini.loginUser(username, password);
        } catch (GeneralException e) {
            showErrorDialog("L'usuari no existeix");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mètode per tancar una sessió. Es mostra un error si no estava iniciada.
     */
    public void logoutUser() {
        try {
            controladorDomini.logoutUser();
        } catch (DomainException e) {
            showErrorDialog("No s'havia iniciat sessió");
            e.printStackTrace();
        }
    }

    /**
     * Mètode saber si hi ha una partida guardada. Es mostra un error si no s'ha iniciat sessió.
     * @return Si existeix la partida guardada. En cas d'error, es retorna fals.
     */
    public Boolean existsPartidaGuardada() {
        try {
            return controladorDomini.existsPartidaGuardada();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Mètode per carregar la partida guardada. Es mostra un error si no existeix.
     */
    public void carregarPartida() {
        try {
            controladorDomini.carregarPartida();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut carregar la partida");
            e.printStackTrace();
        }
    }

    /**
     * Mètode per iniciar una nova partida com a Maker. Es mostra un error en cas de no poder crear-la
     * @param solucio Solució per a la nova partida
     * @param algorisme ALgorisme per a la nova partida
     */
    public void novaPartidaMaker(List<Integer> solucio, Integer algorisme){
        try {
            controladorDomini.novaPartidaMaker(solucio, algorisme);
        } catch (GeneralException e){
            showErrorDialog("No s'ha pogut crear la partida");
            e.printStackTrace();
        }
    }

    /**
     * Mètode per iniciar una nova partida com a Breaker. Es mostra un error en cas de no poder crear-la
     * @param nivellDificultat NivellDificultat per la nova partida
     */
    public void novaPartidaBreaker(Integer nivellDificultat){
        try {
            controladorDomini.novaPartidaBreaker(nivellDificultat);
        } catch (GeneralException e){
            showErrorDialog("No s'ha pogut crear la partida");
            e.printStackTrace();
        }
    }

    /**
     * Getter del nom de l'usuari que ha iniciat sessió. Es mostra un error si no s'ha iniciat sessió
     */
    public String getUserName() {
        try {
            return controladorDomini.getUserName();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     */
    public List<Integer> getPersonalRecord() {
        try {
            return controladorDomini.getPersonalRecord();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter del temps jugat com a breaker de l'usuari actual.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el temps jugat com a breaker per a cada nivell de dificultat
     */
    public List<Long> getTimePlayed()  {
        try {
            return controladorDomini.getTimePlayed();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter del nombre de victòries de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el nombre de partides guanyades per a cada nivell de dificultat
     */
    public List<Integer> getWonGames() {
        try {
            return controladorDomini.getWonGames();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter del nombre de derrotes de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el nombre de partides perdudes per a cada nivell de dificultat
     */
    public List<Integer> getLostGames() {
        try {
            return controladorDomini.getLostGames();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter de ratxa de victòries de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     */
    public List<Integer> getWinstreak() {
        try {
            return controladorDomini.getWinstreak();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter de mitjana d'intents de l'usuari que ha iniciat sessió com a breaker.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la mitjana d'intents de l'usuari per a cada dificultat
     */
    public List<Double> getAverageAsBreaker() {
        try {
            return controladorDomini.getAverageAsBreaker();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter de mitjana d'intents de l'usuari que ha iniciat sessió com a maker.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada algorisme
     */
    public List<Double> getAverageAsMaker() {
        try {
            return controladorDomini.getAverageAsMaker();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
        return null;
    }

    public List<List<List<String>>> getRanquing(){
        return controladorDomini.getRanquing();
    }
    /**
     * Mètode per esborrar l'usuari que ha iniciat sessió.
     * Es mostra un error si no estava iniciada.
     */
    public void esborrarUsuari() {
        try {
            controladorDomini.esborrarUsuari();
        }
        catch (GeneralException e) {
            showErrorDialog("No s'ha iniciat sessió");
            e.printStackTrace();
        }
    }

    List<List<Integer>> getIntents() {
        try {
            return controladorDomini.getIntents();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut obtenir els intents");
        }
        return null;
    }

    List<List<Integer>> getFeedbacks() {
        try {
            return controladorDomini.getFeedbacks();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut obtenir els feedbacks");
        }
        return null;
    }

    List<Integer> getSolucio() {
        try {
            return controladorDomini.getSolucio();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut obtenir la solució");
        }
        return null;
    }

    public void setBola(Integer index, Integer bola) {
        try {
            controladorDomini.setBola(index, bola);
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut assignar la bola");
        }
    }

    public List<Integer> validarSequencia() {
        try {
            return controladorDomini.validarSequencia();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut validar l'intent");
        }
        return null;
    }

    public Boolean isUltimIntentPle() {
        try {
            return controladorDomini.isUltimIntentPle();
        } catch (DomainException e) {
            showErrorDialog("No s'ha pogut comprovar l'estat de l'últim intent");
        }
        return null;
    }

    /**
     * Punt d'entrada per mostrar l'aplicació
     */
    void run() {
        if(controladorDomini == null)
            showErrorDialog("No s'ha pogut iniciar la base de dades");
        else {
            /*
            loginUser("albert", "albert");
            novaPartidaBreaker(2);
            showPartidaBreakerView();
            */
            showInitialView();
        }
    }

    /**
     * Mètode per assignar el contingut del frame de l'aplicació
     * @param panel Contingut a assignar
     */
    void setContent(JPanel panel) {
        mainFrame.setContent(panel);
    }

    /**
     * Mètode per assignar el títol del frame de l'aplicació
     * @param title Títol a assignar
     */
    public void setTitle(String title) {
        mainFrame.setTitle(title);
    }

    /**
     * Mètode per mostrar la vista InitialView
     */
    void showInitialView() {
        InitialView initialView = new InitialView();
        initialView.show();
    }

    /**
     * Mètode per mostrar la vista RegisterView
     */
    void showRegisterView() {
        RegisterView registerView = new RegisterView();
        registerView.show();
    }

    /**
     * Mètode per mostrar la vista LoginView
     */
    void showLoginView() {
        LoginView loginView = new LoginView();
        loginView.show();
    }

    /**
     * Mètode per mostrar la vista HomeView
     */
    void showHomeView() {
        HomeView homeView = new HomeView();
        homeView.show();
    }

    /**
     * Mètode per mostrar la vista NormesView
     */
    void showNormesView() {
         NormesView normesView = new NormesView();
         normesView.show();
    }

    /**
     * Mètode per mostrar la vista HomeView o InitialView,
     * en funció de si l'usuari ha iniciat sessió o no (respectivament)
     */
    void showInitialOrHomeView() {
        if(controladorDomini.userLoggedIn())
            showHomeView();
        else
            showInitialView();
    }

    /**
     * Mètode per mostrar la vista RankingView
     */
    void showRankingView() {
        RankingView rankingView = new RankingView();
        rankingView.show();
    }

    /**
     * Mètode per mostrar la vista NovaPartidaView
     */
    void showNovaPartidaView() {
        NovaPartidaView novaPartidaView = new NovaPartidaView();
        novaPartidaView.show();
    }

    /**
     * Mètode per mostrar la vista ZonaUsuariView
     */
    void showZonaUsuariView() {
        ZonaUsuariView zonaUsuariView = new ZonaUsuariView();
        zonaUsuariView.show();
    }

    /**
     * TESTING
     * Mètode per mostrar la vista PartidaView
     */
    void showPartidaBreakerView() {
        try {
            PartidaBreakerView partidaBreakerView = new PartidaBreakerView();
            partidaBreakerView.show();
        } catch (PresentationException e) {
            showErrorDialog("No s'ha pogut mostrar la partida");
        }
    }


    /**
     * Mètode per mostrar un diàleg de warning
     * @param title Títol del diàleg
     * @param message Missatge del diàleg
     */
    void showWarningDialog(String title, String message) {
        mainFrame.showWarningDialog(title, message);
    }

    /**
     * Mètode per mostrar un diàleg d'error
     * @param message Missatge del diàleg
     */
    void showErrorDialog(String message) {
        mainFrame.showErrorDialog(message);
    }

}
