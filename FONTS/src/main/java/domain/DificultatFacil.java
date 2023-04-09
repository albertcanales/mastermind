package domain;

import java.util.ArrayList;
import java.util.List;

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

        List<Integer> Color_count = new ArrayList<>(NUMCOLORS);
        for (int i = 0; i < NUMCOLORS; i++) Color_count.add(0);
        for (Integer color_sol : solucio){
            Integer count = Color_count.get(color_sol); count++;
            Color_count.set(color_sol, count);
        }

        List<Integer> feedback = new ArrayList<>(NUMBOLES);
        for (int i = 0; i < NUMBOLES; i++){
            Integer color = intent.get(i);
            Integer color_count = Color_count.get(color);
            if (color_count > 0) {
                if (solucio.get(i) == color) feedback.set(i, BolaNegra);
                else feedback.set(i, BolaBlanca);
                Integer count = Color_count.get(color); count--;
                Color_count.set(color, count);
            } else feedback.set(i, BolaNula);
        }

        return feedback;
    }

}
