package domain;

import exceptions.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Ranquing {

    private List<List<List<String>>> ranquing;
    private final int RANQUING_MAX_SIZE = 15;

    Ranquing(){
        ranquing = new ArrayList<>();
        for (int i = 0; i < NivellDificultat.numDificultats(); ++i){
            ranquing.add(new ArrayList<>());
        }
    }

    Ranquing(List<List<List<String>>> ranquing){
        // check InvalidRanquingSizeException
        this.ranquing = ranquing;
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

        List<List<String>> ranquingDificultat = ranquing.get(dificultat);

        List<String> novaPartida = new ArrayList<>();
        novaPartida.add(username);
        novaPartida.add(intents.toString());
        novaPartida.add(temps.toString());
        ranquingDificultat.add(novaPartida);

        int indexNovaPartida = ranquingDificultat.size()-1;
        while (indexNovaPartida != 0) {
            List<String> compPartida = ranquingDificultat.get(indexNovaPartida-1);
            if (compare(compPartida,novaPartida) >= 0) ranquingDificultat.set(indexNovaPartida,compPartida);
            --indexNovaPartida;
        }
        ranquingDificultat.set(indexNovaPartida,novaPartida);

        int ranquingSize = ranquingDificultat.size();
        if (ranquingSize >= RANQUING_MAX_SIZE) ranquingDificultat.remove(ranquingSize-1);
    }

    List<List<String>> getRanquing(Integer nivellDificultat){
        return ranquing.get(nivellDificultat-1);
    };

    List<List<List<String>>> getRanquing(){
        return ranquing;
    }
}
