package presentation;

import domain.ControladorDomini;
import exceptions.GeneralException;
import exceptions.presentation.PresentationException;

import javax.swing.*;
import java.util.List;

/**
 * Controlador de Presentació
 * Segueix el patró de Singleton
 * @author Albert Canales
 */
class ControladorPresentacio {

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
     * @return L'única instància de ControladorPresentació
     */
    static ControladorPresentacio getInstance() {
        return instance;
    }

    /**
     * Mètode per saber si un usuari donat és vàlid (bon format)
     * @param username Username a comprovar
     * @param name Nom a comprovar
     * @param password La contrasenya a comprovar
     * @return Si l'usuari és vàlid
     */
    Boolean isValidUser(String username, String name, String password) {
        return controladorDomini.isValidUser(username, name, password);
    }

    /**
     * Mètode per saber si un usuari existeix. Es mostra un error si hi ha problemes d'accés a la DB.
     * @param username Username a comprovar
     * @return Si l'usuari existeix
     */
    Boolean existsUser(String username) {
        try {
            return controladorDomini.existsUser(username);
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha pogut comprovar si existeix l'usuari");
        }
        return false;
    }

    /**
     * Mètode per registrar un usuari. Es mostra un error si ja existeix.
     * @param username Username a registrar
     * @param name Nom a registrar
     * @param password Contrasenya a registrar
     */
    void registerUser(String username, String name, String password) {
        try {
            controladorDomini.registerUser(username, name, password);
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha pogut registrar l'usuari");
        }
    }

    /**
     * Mètode per iniciar sessió d'un usuari. Es mostra un error si no existeix.
     * @param username Username per l'inici de sessió
     * @param password Contrasenya per l'inici de sessió
     * @return Si s'ha pogut iniciar sessió correctament (o pel contrari, les dades no eren correctes)
     */
    Boolean loginUser(String username, String password) {
        try {
            return controladorDomini.loginUser(username, password);
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("L'usuari no existeix");
        }
        return false;
    }

    /**
     * Mètode per tancar una sessió. Es mostra un error si no estava iniciada.
     */
    void logoutUser() {
        try {
            controladorDomini.logoutUser();
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'havia iniciat sessió");
        }
    }

    /**
     * Mètode saber si hi ha una partida guardada. Es mostra un error si no s'ha iniciat sessió.
     * @return Si existeix la partida guardada. En cas d'error, es retorna fals.
     */
    Boolean existsPartidaGuardada() {
        try {
            return controladorDomini.existsPartidaGuardada();
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return false;
    }

    /**
     * Mètode per carregar la partida guardada. Es mostra un error si no existeix.
     */
    void carregarPartida() {
        try {
            controladorDomini.carregarPartida();
        } catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha pogut carregar la partida");
        }
    }

    /**
     * Mètode per iniciar una nova partida com a Maker. Es mostra un error en cas de no poder crear-la
     * @param solucio Solució per a la nova partida
     * @param algorisme ALgorisme per a la nova partida
     */
    void novaPartidaMaker(List<Integer> solucio, Integer algorisme){
        try {
            controladorDomini.novaPartidaMaker(solucio, algorisme);
        } catch (GeneralException e){
            e.printStackTrace();
            showErrorDialog("No s'ha pogut crear la partida");
        }
    }

    /**
     * Mètode per iniciar una nova partida com a Breaker. Es mostra un error en cas de no poder crear-la
     * @param nivellDificultat NivellDificultat per la nova partida
     */
    void novaPartidaBreaker(Integer nivellDificultat){
        try {
            controladorDomini.novaPartidaBreaker(nivellDificultat);
        } catch (GeneralException e){
            e.printStackTrace();
            showErrorDialog("No s'ha pogut crear la partida");
        }
    }

    /**
     * Getter del nom de l'usuari que ha iniciat sessió. Es mostra un error si no s'ha iniciat sessió
     * @return El nom de l'usuari que ha inciat sessió
     */
    String getUserName() {
        try {
            return controladorDomini.getUserName();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter del rècord personal (nombre mínim d'intents per guanyar) de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el rècord personal per a cada nivell de dificultat
     */
    List<Integer> getPersonalRecord() {
        try {
            return controladorDomini.getPersonalRecord();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter del temps jugat com a breaker de l'usuari actual.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el temps jugat com a breaker per a cada nivell de dificultat
     */
    List<Long> getTimePlayed()  {
        try {
            return controladorDomini.getTimePlayed();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter del nombre de victòries de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el nombre de partides guanyades per a cada nivell de dificultat
     */
    List<Integer> getWonGames() {
        try {
            return controladorDomini.getWonGames();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter del nombre de derrotes de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb el nombre de partides perdudes per a cada nivell de dificultat
     */
    List<Integer> getLostGames() {
        try {
            return controladorDomini.getLostGames();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter de ratxa de victòries de l'usuari que ha iniciat sessió.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la màxima ratxa de victòries per a cada nivell de dificultat
     */
    List<Integer> getWinstreak() {
        try {
            return controladorDomini.getWinstreak();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter de mitjana d'intents de l'usuari que ha iniciat sessió com a breaker.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la mitjana d'intents de l'usuari per a cada dificultat
     */
    List<Double> getAverageAsBreaker() {
        try {
            return controladorDomini.getAverageAsBreaker();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Getter de mitjana d'intents de l'usuari que ha iniciat sessió com a maker.
     * Es mostra un error si no hi ha cap usuari que ha iniciat sessió.
     * @return Una llista amb la mitjana d'intents que ha necessitat la màquina per a cada algorisme
     */
    List<Double> getAverageAsMaker() {
        try {
            return controladorDomini.getAverageAsMaker();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
        return null;
    }

    /**
     * Mètode per obtenir els rànquings de les millors partides per cada dificultat. Per a cada dificultat, hi ha
     * una llista amb entrades del tipus {username, intents, temps}
     * @param max_rows Nombre màxim d'entrades a rebre per cada rànquing
     * @return Rànquings per a cada dificultat
     */
    List<List<List<String>>> getRanquings(Integer max_rows){
        try {
            return controladorDomini.getRanquings(max_rows);
        } catch (GeneralException e) {
            showErrorDialog("No s'han pogut obtenir els rànquings");
        }
        return null;
    }
    /**
     * Mètode per esborrar l'usuari que ha iniciat sessió.
     * Es mostra un error si no estava iniciada.
     */
    void esborrarUsuari() {
        try {
            controladorDomini.esborrarUsuari();
        }
        catch (GeneralException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha iniciat sessió");
        }
    }

    /**
     * Mètode per obtenir tots els intents de la partida actual
     * @return Llista amb tots els intents de la partida actual
     */
    List<List<Integer>> getIntents() {
        try {
            return controladorDomini.getIntents();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut obtenir els intents");
        }
        return null;
    }

    /**
     * Mètode per obtenir tots els feedbacks de la partida actual
     * @return Llista amb tots els feedbacks de la partida actual
     */
    List<List<Integer>> getFeedbacks() {
        try {
            return controladorDomini.getFeedbacks();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut obtenir els feedbacks");
        }
        return null;
    }

    /**
     * Mètode per obtenir la solució de la partida actual
     * @return Seqüència solució de la partida actual
     */
    List<Integer> getSolucio() {
        try {
            return controladorDomini.getSolucio();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut obtenir la solució");
        }
        return null;
    }

    /**
     * Mètode per assignar una bola en la partida actual, de la seqüència actual
     * @param index Índex de la bola dins de la seqüència
     * @param bola Enter que representa el color de la bola desitjat
     */
    void setBola(Integer index, Integer bola) {
        try {
            controladorDomini.setBola(index, bola);
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut assignar la bola");
        }
    }

    /**
     * Mètode per validar l'últim intent de la partida que s'està jugant
     * @return La seqüència amb els feedbacks corresponents a l'intent aportat
     */
    List<Integer> validarSequencia() {
        try {
            return controladorDomini.validarSequencia();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut validar l'intent");
        }
        return null;
    }

    /**
     * Mètode per saber si el jugador fa de breaker en la partida actual
     * @return Si el jugador és breaker en la partida actual
     */
    Boolean isJugadorBreaker() {
        try {
            return controladorDomini.isJugadorBreaker();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut determinar el tipus (Breaker/Maker) de la partida guardada");
        }
        return null;
    }

    /**
     * Mètode per saber si la partida actual està guanyada
     * @return Si la partida actual està guanyada
     */
    Boolean isPartidaGuanyada() {
        try {
            return controladorDomini.isPartidaGuanyada();
        } catch (GeneralException e) {
            showErrorDialog("No s'està jugant una partida com a breaker");
        }
        return null;
    }

    /**
     * Mètode per saber si la partida actual està acabada
     * @return Si la partida actual està acabada (guanyada o perduda)
     */
    Boolean isPartidaAcabada() {
        try {
            return controladorDomini.isPartidaAcabada();
        } catch (GeneralException e) {
            showErrorDialog("No s'està jugant una partida com a breaker");
        }
        return null;
    }

    /**
     * Mètode per saber si l'últim intent de la partida actual està ple, és a dir, no té cap bola nul·la
     * @return Si l'últim intent està ple
     */
    Boolean isUltimIntentPle() {
        try {
            return controladorDomini.isUltimIntentPle();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut comprovar l'estat de l'últim intent");
        }
        return null;
    }

    /**
     * Mètode per indicar que l'usuari breaker vol veure la solució de la partida actual, perdent així la partida
     */
    void veureSolucio() {
        try {
            controladorDomini.veureSolucio();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut veure la solució");
        }
    }

    /**
     * Getter dels mil·lisegons transcorreguts en la partida actual
     * @return Mil·lisegons transcorreguts en la partida actual
     */
    Long getTempsPartidaMillis() {
        try {
            return controladorDomini.getTempsPartidaMillis();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut obtenir el temps de la partida");
        }
        return null;
    }

    /**
     * Mètode per afegir temps transcorregut a la partida actual
     * @param millis mi·lisegons a afegir
     */
    void addTempsPartidaMillis(Long millis) {
        try {
            controladorDomini.addTempsPartidaMillis(millis);
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut incrementar el temps de la partida");
        }
    }

    /**
     * Mètode perquè el bot jugui la partida.
     */
    void botSolve() {
        try {
            controladorDomini.botSolve();
        } catch (GeneralException e) {
            showErrorDialog("El bot no ha pogut jugar la partida");
        }
    }

    /**
     * Mètode per esborrar la partida carregada
     */
    void sortirPartida() {
        try {
            controladorDomini.sortirPartida();
        } catch (GeneralException e) {
            showErrorDialog("No s'ha pogut sortir de la partida");
        }
    }

    /**
     * Punt d'entrada per mostrar l'aplicació
     */
    void run() {
        if(controladorDomini == null)
            showErrorDialog("No s'ha pogut iniciar la base de dades");
        else {
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
    void setTitle(String title) {
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
    void showEstadistiquesView() {
        EstadistiquesView estadistiquesView = new EstadistiquesView();
        estadistiquesView.show();
    }

    /**
     * Mètode per mostrar la vista PartidaBreakerView
     */
    void showPartidaBreakerView() {
        try {
            PartidaBreakerView partidaBreakerView = new PartidaBreakerView();
            partidaBreakerView.show();
        } catch (PresentationException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha pogut mostrar la partida");
        }
    }

    /**
     * Mètode per mostrar la vista PartidaMakerView
     */
    void showPartidaMakerView() {
        try {
            PartidaMakerView partidaMakerView = new PartidaMakerView();
            partidaMakerView.show();
        } catch (PresentationException e) {
            e.printStackTrace();
            showErrorDialog("No s'ha pogut mostrar la partida");
        }
    }


    /**
     * Mètode per mostrar un diàleg amb si o no com a opcions
     * @param title Títol del diàleg
     * @param message Missatge del diàleg
     * @return La resposta de l'usuari
     */
    Boolean showYesNoDialog(String title, String message) {
        return mainFrame.showYesNoDialog(title, message);
    }

    /**
     * Mètode per mostrar un diàleg d'informació
     * @param title Títol del diàleg
     * @param message Missatge del diàleg
     */
    void showInformationDialog(String title, String message) {
        mainFrame.showInformationDialog(title, message);
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
