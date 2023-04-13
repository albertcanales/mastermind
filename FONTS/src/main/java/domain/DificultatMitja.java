package domain;

import domain.exceptions.DomainException;
import domain.exceptions.IntentNoCompletException;
import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.SolIntentNotSameSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DificultatMitja extends Dificultat {
    /**
     * Getter del nivell de dificultat de dificultat Mitja
     * */
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.MITJA;
    }

    /**
     * Mètode per generar el feedback corresponent a un intent en una partida de dificultat mitja
     * @param solucio solució de la partida
     * @param intent intent del jugador
     * @return feedback de l'intent en funció de la solució: una bola negre per cada posició on hi ha el mateix color, una blanca per on el color es a una altra posició, i la resta buides
     */
    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent) throws DomainException {
        if (solucio.size() != intent.size()) throw new SolIntentNotSameSizeException(solucio.size(),intent.size());
        if(isPlena(intent)) throw new IntentNoCompletException();
        int numboles = solucio.size();

        List<Integer> Color_count = countColorsBoles(solucio);

        List<Integer> feedback = new ArrayList<>(numboles);
        int negres = 0; int blanques = 0;
        for (int i = 0; i < numboles; ++i){
            Integer color_sol = solucio.get(i);
            if (!Bola.isValid(color_sol)) throw new InvalidEnumValueException("Bola", color_sol.toString());
            Integer color_intent = intent.get(i);
            if (!Bola.isValid(color_intent)) throw new InvalidEnumValueException("Bola", color_intent.toString());

            Integer color_count = Color_count.get(color_intent);
            if (color_count > 0) {
                if (Objects.equals(solucio.get(i), color_intent)) negres++;
                else blanques++;
                Integer count = Color_count.get(color_intent); count--;
                Color_count.set(color_intent, count);
            }
        }
        for (int i = 0; i < negres; ++i) feedback.add(Bola.NEGRE.number());
        for (int i = 0; i < blanques; ++i) feedback.add(Bola.BLANC.number());
        for (int i = negres+blanques; i < numboles; ++i) feedback.add(Bola.NUL.number());

        return feedback;
    }

}
