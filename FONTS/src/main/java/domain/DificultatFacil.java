package domain;

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
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){
        // TODO S'ha de validar solucio.length == intent.length

        int numboles = solucio.size();
        List<Integer> Color_count = countColorsBoles(solucio);

        List<Integer> feedback = new ArrayList<>(numboles);
        for (int i = 0; i < numboles; i++){
            Integer color = intent.get(i);
            Integer color_count = Color_count.get(color);
            if (color_count > 0) {
                if (Objects.equals(solucio.get(i), color)) feedback.set(i, Bola.NEGRE.number());
                else feedback.set(i, Bola.BLANC.number());
                Integer count = Color_count.get(color); count--;
                Color_count.set(color, count);
            } else feedback.set(i, Bola.NUL.number());
        }

        return feedback;
    }

}
