package presentation;

import domain.ControladorDomini;

public class ControladorPresentacio {

    private final ControladorDomini controladorDomini;

    ControladorPresentacio() {
        controladorDomini = new ControladorDomini();
    }

    void run() {
        showMainView();
    }

    void showMainView() {
        MainView mainView = new MainView(this);
        mainView.init();
    }

}
