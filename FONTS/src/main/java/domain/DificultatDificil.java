package domain;

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
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){

        int numboles = solucio.size();
        List<Integer> Color_count = countColorsBoles(solucio);

        List<Integer> feedback = new ArrayList<>(numboles);
        int negres = 0;
        for (int i = 0; i < numboles; ++i){
            Integer color = intent.get(i);
            Integer color_count = Color_count.get(color);
            if (color_count > 0) {
                if (Objects.equals(solucio.get(i), color)) negres++;
                Integer count = Color_count.get(color); count--;
                Color_count.set(color, count);
            }
        }
        for (int i = 0; i < negres; ++i) feedback.add(Bola.NEGRE.number());
        for (int i = negres; i < numboles; ++i) feedback.add(Bola.NUL.number());

        return feedback;
    }

}