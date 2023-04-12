package domain;

import domain.exceptions.DomainException;
import domain.exceptions.IntentNoComplet;
import domain.exceptions.InvalidEnumValueException;
import domain.exceptions.SolIntentNotSameSizeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DificultatFacil extends Dificultat {

    /**
     * Getter del nivell de dificultat de dificultat Fàcil
     * */
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.FACIL;
    }

    /**
     * Mètode per generar el feedback corresponent a un intent en una partida de dificultat fàcil
     * @param solucio solució de la partida
     * @param intent intent del jugador
     * @return feedback de l'intent en funció de la solució: a cada posició bola negre si hi ha el mateix color, blanca si el color es a una altra posició, i altrament buida
     */
    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent) throws DomainException {
        if (solucio.size() != intent.size()) throw new SolIntentNotSameSizeException(solucio.size(),intent.size());
        if(isPlena(intent)) throw new IntentNoComplet();
        int numboles = solucio.size();

        List<Integer> Color_count = countColorsBoles(solucio);

        List<Integer> feedback = new ArrayList<>(numboles);
        for (int i = 0; i < numboles; i++){
            Integer color_sol = solucio.get(i);
            if (!Bola.isValid(color_sol)) throw new InvalidEnumValueException("Bola", color_sol.toString());
            Integer color_intent = intent.get(i);
            if (!Bola.isValid(color_intent)) throw new InvalidEnumValueException("Bola", color_intent.toString());

            Integer color_count = Color_count.get(color_intent);
            if (color_count > 0) {
                if (Objects.equals(color_sol, color_intent)) feedback.set(i, Bola.NEGRE.number());
                else feedback.set(i, Bola.BLANC.number());
                Integer count = Color_count.get(color_intent) - 1;
                Color_count.set(color_intent, count);
            } else feedback.set(i, Bola.NUL.number());
        }

        return feedback;
    }

}
