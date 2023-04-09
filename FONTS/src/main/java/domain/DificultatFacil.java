package domain;

import java.util.ArrayList;
import java.util.List;

class DificultatFacil extends Dificultat {
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.FACIL;
    }

    /**
     * Mètode per generar el feedback correponent a un intent en una partida facil
     * @param solucio solucio de la partida
     * @param intent intent del jugador
     * @return feedback de l'intent en funcio de la solucio: a cada posició bola negre si hi ha el mateix color, blanca si el color es a una altra posició, i altrament buida
     */
    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){
        Integer NUMBOLES = solucio.size();
        Integer NUMCOLORS = Bola.numColors(); //Provisional

        List<Integer> Color_count = new ArrayList<>(NUMBOLES);
        for (int i = 0; i < NUMBOLES; i++) Color_count.add(0);
        for (Integer color_sol : solucio){
            Integer count = Color_count.get(color_sol); count++;
            Color_count.set(color_sol, count);
        }

        List<Integer> feedback = new ArrayList<>(NUMBOLES);
        for (int i = 0; i < NUMBOLES; i++){
            Integer color = intent.get(i);
            Integer color_count = Color_count.get(color);
            if (color_count > 0) {
                if (solucio.get(i) == color) feedback.set(i, Bola.NEGRE.number());
                else feedback.set(i, Bola.BLANC.number());
                Integer count = Color_count.get(color); count--;
                Color_count.set(color, count);
            }
        }

        return feedback;
    }

}
