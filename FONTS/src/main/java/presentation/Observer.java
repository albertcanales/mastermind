package presentation;

interface Observer {
    //actualiza el estado del observador concreto de acuerdo con el del sujeto
    void Update(Subject s);
}