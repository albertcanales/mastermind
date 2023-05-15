package domain;

import exceptions.GeneralException;
import exceptions.domain.DomainException;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Driver del ControladorDomini
 * @author Albert Canales
 */
public class DominiDriver extends ControladorDriver {

    private static ControladorDomini cd;

    private static void showUsage() {
        System.out.println("Llista de comandes:");
        System.out.println("     0 | sortir");
        System.out.println("     1 | ajuda");
        System.out.println("     2 | constructor");
        System.out.println("     3 | userLoggedIn");
        System.out.println("     4 | partidaBeingPlayed");
        System.out.println("     5 | isValidUser");
        System.out.println("     6 | existsUser");
        System.out.println("     7 | loginUser");
        System.out.println("     8 | registerUser");
        System.out.println("     9 | logoutUser");
        System.out.println("    10 | novaPartidaMaker");
        System.out.println("    11 | novaPartidaBreaker");
        System.out.println("    12 | existsPartidaGuardada");
        System.out.println("    13 | carregarPartida");
        System.out.println("    14 | getRanquing");
        System.out.println("    15 | getPersonalRecord");
        System.out.println("    16 | getTimePlayed");
        System.out.println("    17 | getWonGames");
        System.out.println("    18 | getLostGames");
        System.out.println("    19 | getWinstreak");
        System.out.println("    20 | getAverageAsBreaker");
        System.out.println("    21 | getAverageAsMaker");
        System.out.println("    22 | isJugadorBreaker");
        System.out.println("    23 | getSolucio");
        System.out.println("    24 | getIntents");
        System.out.println("    25 | getFeedbacks");
        System.out.println("    26 | getTempsPartidaMillis");
        System.out.println("    27 | addTempsPartidaMillis");
        System.out.println("    28 | setBola");
        System.out.println("    29 | validarSequencia");
        System.out.println("    30 | botSolve");
        System.out.println("    31 | isPartidaGuanyada");
        System.out.println("    32 | isPartidaPerduda");
        System.out.println("    33 | isPartidaAcabada");
        System.out.println("    34 | isUltimIntentPle");
        System.out.println("    35 | getUserName");
        System.out.println("    36 | sortirPartida");
        System.out.println("    37 | esborrarUsuari");
        System.out.println("Cada comanda es pot executar pel seu nombre o pel seu nom.");
        System.out.println("La comanda 'ajuda (1)' mostra de nou aquesta informació");
        System.out.println("Hint: Com que la persistència està mockejada, ara només hi ha un usuari 'albert' amb contrasenya 'contrasenya' ");
    }

    private static void testConstructor() throws GeneralException {
        System.out.println("Testing constructor...");

        cd = new ControladorDomini();
    }

    private static void testUserLoggedIn() {
        System.out.println("Testing userLoggedIn...");

        if(cd.userLoggedIn())
            System.out.println("S'ha iniciat sessió");
        else
            System.out.println("No s'ha iniciat sessió");
    }

    private static void testIsPartidaBeingPlayed() {
        System.out.println("Testing isPartidaBeingPlayed...");

        if(cd.isPartidaBeingPlayed())
            System.out.println("Hi ha una partida en joc");
        else
            System.out.println("No hi ha una partida en joc");
    }

    private static void testIsValidUser() {
        System.out.println("Testing isValidUser...");

        System.out.print("Enter a username: ");
        String username = in.nextLine();
        System.out.print("Enter a name: ");
        String name = in.nextLine();
        System.out.print("Enter a password: ");
        String password = in.nextLine();

        if(cd.isValidUser(username, name, password))
            System.out.println("The given user data is valid");
        else
            System.out.println("The given user data is invalid");
    }

    private static void testExistsUser() throws GeneralException {
        System.out.println("Testing existsUser...");

        System.out.print("Enter un username: ");
        String username = in.nextLine();

        if (cd.existsUser(username))
            System.out.printf("User %s does exists%n", username);
        else
            System.out.printf("User %s does not exists%n", username);
    }

    private static void testLoginUser() throws GeneralException {
        System.out.println("Testing loginUser...");

        System.out.print("Enter un username: ");
        String username = in.nextLine();
        if (!cd.existsUser(username)) {
            System.out.println("Error: User does not exists");
            return;
        }

        System.out.print("Enter the password: ");
        String password = in.nextLine();

        if(cd.loginUser(username, password))
            System.out.println("Successfully logged in.");
        else
            System.out.println("Error: Wrong password");
    }

    private static void testRegisterUser() throws GeneralException {
        System.out.println("Testing registerUser...");

        System.out.print("Enter un username: ");
        String username = in.nextLine();
        if (cd.existsUser(username)) {
            System.out.println("Error: User already exists");
            return;
        }

        System.out.print("Enter a name: ");
        String name = in.nextLine();
        System.out.print("Enter a password: ");
        String password = in.nextLine();

        if(!cd.isValidUser(username, name, password)) {
            System.out.println("Error: Els paràmetres donats no són vàlids (no poden ser buits).");
            return;
        }

        cd.registerUser(username, name, password);
    }

    private static void testLogoutUser() throws DomainException {
        System.out.println("Testing logoutUser...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        cd.logoutUser();
    }

    private static void testNovaPartidaMaker() throws GeneralException {
        System.out.println("Testing novaPartidaMaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> solution = scanSolution();
        Integer algorisme = scanAlgorisme();
        cd.novaPartidaMaker(solution, algorisme);
    }

    private static void testNovaPartidaBreaker() throws GeneralException {
        System.out.println("Testing novaPartidaBreaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        Integer dificultat = scanNivellDificultat();
        cd.novaPartidaBreaker(dificultat);
    }

    private static void testExistsPartidaGuardada() throws GeneralException {
        System.out.println("Testing existsPartidaGuardada...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        if(cd.existsPartidaGuardada())
            System.out.println("Hi ha una partida guardada de l'usuari que ha iniciat sessió");
        else
            System.out.println("No hi ha una partida guardada de l'usuari que ha iniciat sessió");
    }

    private static void testCarregarPartida() throws GeneralException {
        System.out.println("Testing carregarPartida...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        if(!cd.existsPartidaGuardada()) {
            System.out.println("Error: No hi ha una partida guardada per carregar");
            return;
        }

        cd.carregarPartida();
    }

    private static void testGetRanquing() throws GeneralException {
        System.out.println("Testing getRanquing...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        Integer nivellDificultat = scanNivellDificultat();

        List<List<Object>> ranquing = cd.getRanquing(nivellDificultat);

        if(ranquing.size() == 0) {
            System.out.println("No hi ha cap partida en aquesta dificultat");
        }
        else {
            System.out.println("USUARI | INTENTS | TEMPS");
            for (List<Object> partida : ranquing) {
                System.out.printf("%s | %d | %d%n", partida.get(0), (Integer)partida.get(1), (Long)partida.get(2));
            }
        }
    }

    private static void testGetPersonalRecord() throws DomainException {
        System.out.println("Testing getPersonalRecord...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> personalRecords = cd.getPersonalRecord();
        for (int i = 0; i < personalRecords.size(); i++) {
            System.out.printf("El PR per la dificultat %d és %d%n", i+1, personalRecords.get(i));
        }
    }

    private static void testGetTimePlayed() throws DomainException {
        System.out.println("Testing getTimePlayed...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Long> timePlayed = cd.getTimePlayed();
        for (int i = 0; i < timePlayed.size(); i++) {
            System.out.printf("El temps jugat en la dificultat %d és %d%n", i+1, timePlayed.get(i));
        }
    }

    private static void testGetWonGames() throws DomainException {
        System.out.println("Testing getWonGames...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> wonGames = cd.getWonGames();
        for (int i = 0; i < wonGames.size(); i++) {
            System.out.printf("S'han guanyat %d partides com a breaker en la dificultat %d%n", wonGames.get(i), i+1);
        }
    }

    private static void testGetLostGames() throws DomainException {
        System.out.println("Testing getLostGames...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> lostGames = cd.getLostGames();
        for (int i = 0; i < lostGames.size(); i++) {
            System.out.printf("S'han perdut %d partides com a breaker en la dificultat %d%n", lostGames.get(i), i+1);
        }
    }


    private static void testGetWinstreak() throws DomainException {
        System.out.println("Testing getWinstreak...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Integer> winstreak = cd.getWinstreak();
        for (int i = 0; i < winstreak.size(); i++) {
            System.out.printf("La màxima ratxa de victòries és %d en la dificultat %d%n", winstreak.get(i), i+1);
        }
    }

    private static void testGetAverageAsBreaker() throws DomainException {
        System.out.println("Testing getAverageAsBreaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Double> averageAsBreaker = cd.getAverageAsBreaker();
        for (int i = 0; i < averageAsBreaker.size(); i++) {
            System.out.printf("La mitjana d'intents en la dificultat %d és de %.2f%n", i+1, averageAsBreaker.get(i));
        }
    }

    private static void testGetAverageAsMaker() throws DomainException {
        System.out.println("Testing getAverageAsMaker...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        List<Double> averageAsMaker = cd.getAverageAsMaker();
        for (int i = 0; i < averageAsMaker.size(); i++) {
            System.out.printf("La mitjana d'intents contra l'algorisme %d és de %.2f%n", i+1, averageAsMaker.get(i));
        }
    }

    public static void testIsJugadorBreaker() throws DomainException {
        System.out.println("Testing isJugadorBreaker...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }

        if (cd.isJugadorBreaker())
            System.out.println("El jugador fa de breaker en la partida actual");
        else
            System.out.println("El bot fa de breaker en la partida actual");
    }

    private static void testGetSolucio() throws DomainException {
        System.out.println("Testing getSolucio...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }

        System.out.print("La solució de la partida és: ");
        printSequence(cd.getSolucio());
    }

    private static void testGetIntents() throws DomainException {
        System.out.println("Testing getIntents...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }

        System.out.println("Els intents de la partida actual són: ");
        List<List<Integer>> intents = cd.getIntents();
        for (List<Integer> intent : intents)
            printSequence(intent);
    }

    private static void testGetFeedbacks() throws DomainException {
        System.out.println("Testing getFeedbacks...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }

        System.out.println("Els feedbacks de la partida actual són: ");
        List<List<Integer>> feedbacks = cd.getFeedbacks();
        for (List<Integer> feedback : feedbacks)
            printSequence(feedback);
    }

    private static void testGetTempsPartidaMillis() throws DomainException {
        System.out.println("Testing getTempsPartidaMillis...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }
        if(!cd.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el bot és el breaker");
            return;
        }

        System.out.printf("El temps transcorregut és de %d ms.%n", cd.getTempsPartidaMillis());
    }

    private static void testAddTempsPartidaMillis() throws DomainException {
        System.out.println("Testing addTempsPartdaMillis...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }
        if(!cd.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el bot és el breaker");
            return;
        }

        System.out.print("Enter a temps a afegir: ");
        Long afegit = scanLong();
        cd.addTempsPartidaMillis(afegit);
    }

    private static void testSetBola() throws DomainException {
        System.out.println("Testing setBola...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(cd.isPartidaAcabada()) {
            System.out.println("Error: La partida actual ja ha acabat");
            return;
        }
        if(!cd.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el bot és el breaker");
            return;
        }

        System.out.print("Enter un index per la bola: ");
        int index = scanInt();
        while(index < 0 || index >= ControladorPartida.getNumBoles()) {
            System.out.println("L'índex no es correcte");
            System.out.print("Enter un index per la bola: ");
            index = scanInt();
        }
        System.out.printf("L'índex de la bola llegit és %d%n", index);

        System.out.print("Enter un color per la bola: ");
        int bola = scanInt();
        while(!Bola.isValid(bola)) {
            System.out.println("El color donat no és vàlid");
            System.out.print("Enter un color per la bola: ");
            bola = scanInt();
        }
        System.out.printf("El color de bola llegit és %d%n", bola);

        cd.setBola(index, bola);
    }

    private static void testValidarSequencia() throws DomainException {
        System.out.println("Testing validarSequencia...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }
        if(!cd.isJugadorBreaker()) {
            System.out.println("Error: En la partida actual el bot és el breaker");
            return;
        }
        if(cd.isPartidaAcabada()) {
            System.out.println("Error: La partida ja està acabada");
            return;
        }
        if(!cd.isUltimIntentPle()) {
            System.out.println("Error: Encara hi ha boles per assignar a l'últim intent");
            return;
        }

        List<Integer> feedback = cd.validarSequencia();
        System.out.print("El feedback rebut en l'últim intent és: ");
        printSequence(feedback);
    }

    private static void testBotSolve() throws DomainException {
        System.out.println("Testing botSolve...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No hi ha una partida en joc");
            return;
        }
        if(cd.isJugadorBreaker()) {
            System.out.println("Error: En aquesta partida el jugador és el breaker");
            return;
        }

        cd.botSolve();
    }

    private static void testIsPartidaGuanyada() throws DomainException {
        System.out.println("Testing isPartidaGuanyada...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cd.isPartidaGuanyada())
            System.out.println("La partida actual està guanyada");
        else
            System.out.println("La partida actual no està guanyada");
    }

    private static void testIsPartidaPerduda() throws DomainException {
        System.out.println("Testing isPartidaPerduda...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cd.isPartidaPerduda())
            System.out.println("La partida actual està perduda");
        else
            System.out.println("La partida actual no està perduda");
    }

    private static void testIsPartidaAcabada() throws DomainException {
        System.out.println("Testing isPartidaAcabada...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cd.isPartidaAcabada())
            System.out.println("La partida actual està acabada");
        else
            System.out.println("La partida actual no està acabada");
    }

    private static void testIsUltimIntentPle() throws DomainException {
        System.out.println("Testing isUltimIntentPle...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        if(cd.isUltimIntentPle())
            System.out.println("L'últim intent està ple");
        else
            System.out.println("L'últim intent no està ple");
    }

    private static void testGetUserName() throws DomainException {
        System.out.println("Testing getUserName...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        System.out.printf("L'usuari que ha iniciat sessió es diu %s%n", cd.getUserName());
    }

    private static void testSortirPartida() throws DomainException {
        System.out.println("Testing sortirPartida...");

        if(!cd.isPartidaBeingPlayed()) {
            System.out.println("Error: No s'està jugant cap partida");
            return;
        }

        cd.sortirPartida();
    }

    private static void testEsborrarUsuari() throws GeneralException {
        System.out.println("Testing esborrarUsuari...");

        if(!cd.userLoggedIn()) {
            System.out.println("Error: No s'ha iniciat sessió");
            return;
        }

        cd.esborrarUsuari();
    }

    public static void main(String[] args) throws GeneralException {
        cd = new ControladorDomini();
        in = new Scanner(System.in);
        System.out.println("A continuació es mostren les comandes possibles:");
        showUsage();
        while(true) {
            boolean runTest = true;
            System.out.print("Escolleix una comanda: ");
            String cmd = in.nextLine();
            switch (cmd) {
                case "0":
                case "sortir":
                    exit(0);
                    break;
                case "1":
                case "ajuda":
                    showUsage();
                    runTest = false;
                    break;
                case "2":
                case "constructor":
                    testConstructor();
                    break;
                case "3":
                case "userLoggedIn":
                    testUserLoggedIn();
                    break;
                case "4":
                case "partidaBeingPlayed":
                    testIsPartidaBeingPlayed();
                    break;
                case "5":
                case "isValidUser":
                    testIsValidUser();
                    break;
                case "6":
                case "existsUser":
                    testExistsUser();
                    break;
                case "7":
                case "loginUser":
                    testLoginUser();
                    break;
                case "8":
                case "registerUser":
                    testRegisterUser();
                    break;
                case "9":
                case "logoutUser":
                    testLogoutUser();
                    break;
                case "10":
                case "novaPartidaMaker":
                    testNovaPartidaMaker();
                    break;
                case "11":
                case "novaPartidaBreaker":
                    testNovaPartidaBreaker();
                    break;
                case "12":
                case "existsPartidaGuardada":
                    testExistsPartidaGuardada();
                    break;
                case "13":
                case "carregarPartida":
                    testCarregarPartida();
                    break;
                case "14":
                case "getRanquing":
                    testGetRanquing();
                    break;
                case "15":
                case "getPersonalRecord":
                    testGetPersonalRecord();
                    break;
                case "16":
                case "getTimePlayed":
                    testGetTimePlayed();
                    break;
                case "17":
                case "getWonGames":
                    testGetWonGames();
                    break;
                case "18":
                case "getLostGames":
                    testGetLostGames();
                    break;
                case "19":
                case "getWinstreak":
                    testGetWinstreak();
                    break;
                case "20":
                case "getAverageAsBreaker":
                    testGetAverageAsBreaker();
                    break;
                case "21":
                case "getAverageAsMaker":
                    testGetAverageAsMaker();
                    break;
                case "22":
                case "isJugadorBreaker":
                    testIsJugadorBreaker();
                    break;
                case "23":
                case "getSolucio":
                    testGetSolucio();
                    break;
                case "24":
                case "getIntents":
                    testGetIntents();
                    break;
                case "25":
                case "getFeedbacks":
                    testGetFeedbacks();
                    break;
                case "26":
                case "getTempsPartidaMillis":
                    testGetTempsPartidaMillis();
                    break;
                case "27":
                case "addTempsPartidaMillis":
                    testAddTempsPartidaMillis();
                    break;
                case "28":
                case "setBola":
                    testSetBola();
                    break;
                case "29":
                case "validarSequencia":
                    testValidarSequencia();
                    break;
                case "30":
                case "botSolve":
                    testBotSolve();
                    break;
                case "31":
                case "isPartidaGuanyada":
                    testIsPartidaGuanyada();
                    break;
                case "32":
                case "isPartidaPerduda":
                    testIsPartidaPerduda();
                    break;
                case "33":
                case "isPartidaAcabada":
                    testIsPartidaAcabada();
                    break;
                case "34":
                case "isUltimIntentPle":
                    testIsUltimIntentPle();
                    break;
                case "35":
                case "getUserName":
                    testGetUserName();
                    break;
                case "36":
                case "sortirPartida":
                    testSortirPartida();
                    break;
                case "37":
                case "esborrarUsuari":
                    testEsborrarUsuari();
                    break;

                default:
                    runTest = false;
            }
            if(runTest)
                System.out.println("End of test");
        }
    }

}
