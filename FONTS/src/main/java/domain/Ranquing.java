package domain;

import exceptions.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Ranquing {

    private final List<List<List<String>>> ranquings;

    Ranquing(List<List<List<String>>> ranquings){
        this.ranquings = ranquings;
    }

    private int compare(List<String> a, List<String> b){
        int compIntents = a.get(1).compareTo(b.get(1));
        if (compIntents == 0) return a.get(2).compareTo(b.get(2));
        else return compIntents;
    }

    void acabarPartidaBreaker(String username, Integer dificultat, Integer intents, Long temps) throws DomainException {
        if (!NivellDificultat.isValid(dificultat)) throw new InvalidEnumValueException("NivellDificultat", dificultat.toString());
        if (intents < 0) throw new InvalidStatIntentsException(intents.toString());
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
            if (compare(compPartida,novaPartida) < 0) positionFound = true;
            else --indexNovaPartida;
        }
        ranquingDificultat.add(indexNovaPartida,novaPartida);
    }

    List<List<List<String>>> getRanquings(Integer max_rows){
        return ranquings;
    }

    List<List<List<String>>> getRanquings(){
        return ranquings;
    }

    void esborrarUserFromRanquing(String username){
        for (int i = 0; i < NivellDificultat.numDificultats(); ++i){
            List<List<String>> ranquingDificultat = ranquings.get(i);
            for (int j = 0; j < ranquingDificultat.size(); ++j){
                if (Objects.equals(ranquingDificultat.get(j).get(0), username)){
                    ranquingDificultat.remove(i);
                }
            }
        }
    }
}
