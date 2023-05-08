package presentation;

import java.util.ArrayList;
import java.util.List;

abstract class Subject {
    private final List<Observer> observersList = new ArrayList<>();
    void Attach(Observer o) {
        observersList.add(o);
    }
    void Detach(Observer o) {
        observersList.remove(o);
    }
    void Notify() {
        for (Observer obs : observersList) {
            obs.Update(this);
        }
    }
}