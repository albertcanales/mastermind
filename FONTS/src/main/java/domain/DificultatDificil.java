package domain;

import domain.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DificultatDificil extends Dificultat {

    /**
     * Getter del nivell de dificultat de dificultat Difícil
     * */
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.DIFICIL;
    }

    /**
     * Mètode per generar el feedback corresponent a un intent en una partida de dificultat difícil
     * @param solucio solució de la partida
     * @param intent intent del jugador
     * @return feedback de l'intent en funció de la solució: una bola negre per cada posició on hi ha el mateix color, la resta buides
     */
    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent) throws DomainException {
        if (solucio.size() != intent.size()) throw new SolIntentNotSameSizeException(solucio.size(),intent.size());
        if(!isPlena(intent)) throw new IntentNoCompletException();
        if(!isPlena(solucio)) throw new InvalidSolutionException();
        int numboles = solucio.size();

        List<Integer> feedback = new ArrayList<>();
        for (int i = 0; i < numboles; ++i){
            Integer color_sol = solucio.get(i);
            if (!Bola.isValid(color_sol)) throw new InvalidEnumValueException("Bola", color_sol.toString());
            Integer color_intent = intent.get(i);
            if (!Bola.isValid(color_intent)) throw new InvalidEnumValueException("Bola", color_intent.toString());

            if (Objects.equals(color_sol, color_intent)) feedback.add(Bola.NEGRE.number());
        }
        for (int i = feedback.size(); i < numboles; ++i) feedback.add(Bola.NUL.number());

        return feedback;
    }

}