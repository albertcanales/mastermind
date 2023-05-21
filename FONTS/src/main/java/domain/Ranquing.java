package domain;

import exceptions.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa els ranquings de les partides guanyades com a CodeBreaker dels usuaris del Mastermind
 * @author Kamil Przybyszewski
 */
class Ranquing {

    /**
     * Llista dels ranquings, un per cada dificultat. Cada ranquing és una llista de les partides ordenades pel nombre d'intents, per cada partida es guarda el nom de l'user, els intents i el temps
     */
    private final List<List<List<String>>> ranquings;

    /**
     * Contructora a partir d'uns ranquings ja existent
     * @param ranquings llista que representa els ranquings amb els que s'ha d'inicialitzar la classe
     */
    Ranquing(List<List<List<String>>> ranquings) throws DomainException{
        Integer ranquingsSize = ranquings.size();
        if (ranquingsSize != NivellDificultat.numDificultats()) throw new InvalidNumRanquingsException(ranquingsSize.toString());

        this.ranquings = ranquings;
    }

    /**
     * Comparador de dues partides
     * @param a primera partida en la comparació
     * @param b segona partida en la comparacio
     * @return True si Partida a < Partida b, és a dir, si "a" s'ha guanyat amb menys intents, i en cas d'empat, si "a" s'ha conclòs en menys temps
     */
    private Boolean compare(List<String> a, List<String> b){
        int intentsA = Integer.parseInt(a.get(1));
        int intentsB = Integer.parseInt(b.get(1));
        if (intentsA == intentsB) {
            long tempsA = Long.parseLong(a.get(2));
            long tempsB = Long.parseLong(b.get(2));
            return (tempsA < tempsB);
        }
        else return (intentsA < intentsB);
    }

    /**
     * Mètode per actualitzar els ranquings quan es guanya una partida com a CodeBreaker, conservant l'ordre
     * @param username username de l'usuari de la partida
     * @param dificultat dificultat de la partida
     * @param intents intents amb els quals s'ha guanyat la partida (estrictament positiu)
     * @param temps duració de la partida (positiu o zero)
     * @throws DomainException Si cap dels paràmetres donats és invàlid
     */
    void acabarPartidaBreaker(String username, Integer dificultat, Integer intents, Long temps) throws DomainException {
        if (!NivellDificultat.isValid(dificultat)) throw new InvalidEnumValueException("NivellDificultat", dificultat.toString());
        if (intents <= 0) throw new InvalidStatIntentsException(intents.toString());
        if (temps < 0L) throw new InvalidStatTempsException(temps.toString());

        dificultat--;

        List<List<String>> ranquingDificultat = ranquings.get(dificultat);

        List<String> novaPartida = new ArrayList<>();
        novaPartida.add(username);
        novaPartida.add(intents.toString());
        novaPartida.add(temps.toString());

        int indexNovaPartida = ranquingDificultat.size();
        boolean positionFound = false;
        while (indexNovaPartida != 0 && !positionFound) {
            List<String> compPartida = ranquingDificultat.get(indexNovaPartida-1);
            if (compare(compPartida,novaPartida)) positionFound = true;
            else --indexNovaPartida;
        }
        ranquingDificultat.add(indexNovaPartida,novaPartida);
    }

    /**
     * Mètode per obtenir els rànquings de totes les dificultats. Cada dificultat té una llista que té elements
     * de la forma [username, intents, temps].ç
     * @param max_rows Nombre d'entrades màxim a obtenir per a cada dificultat
     */
    List<List<List<String>>> getRanquings(Integer max_rows){
        List<List<List<String>>> ranquingMaxRows = new ArrayList<>();
        for (int i = 0; i < NivellDificultat.numDificultats(); ++i){
            if (max_rows < ranquings.get(i).size()) ranquingMaxRows.add(ranquings.get(i).subList(0,max_rows));
            else ranquingMaxRows.add(ranquings.get(i));
        }

        return ranquingMaxRows;
    }

    /**
     * Mètode per obtenir els rànquings de totes les dificultats. Cada dificultat té una llista que té elements
     * de la forma [username, intents, temps].
     */
    List<List<List<String>>> getRanquings(){
        return ranquings;
    }

    /**
     * Mètode per esborrar les partides d'un usuari als ranquings
     * @param username username de l'usuari del qual s'esborraran les partides
     */
    void esborrarUserFromRanquings(String username){
        for (int i = 0; i < NivellDificultat.numDificultats(); ++i){
            List<List<String>> ranquingDificultat = ranquings.get(i);
            for (int j = 0; j < ranquingDificultat.size(); ++j){
                if (Objects.equals(ranquingDificultat.get(j).get(0), username)){
                    ranquingDificultat.remove(j);
                    --j;
                }
            }
        }
    }
}
