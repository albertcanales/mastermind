package domain;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> Color_count = new ArrayList<>(NUMCOLORS);
        for (int i = 0; i < NUMCOLORS; i++) Color_count.add(0);
        for (Integer color_sol : solucio){
            Integer count = Color_count.get(color_sol); count++;
            Color_count.set(color_sol, count);
        }

        List<Integer> feedback = new ArrayList<>(NUMBOLES);
        int Negres = 0;
        for (int i = 0; i < NUMBOLES; ++i){
            Integer color = intent.get(i);
            Integer color_count = Color_count.get(color);
            if (color_count > 0) {
                if (solucio.get(i) == color) Negres++;
                Integer count = Color_count.get(color); count--;
                Color_count.set(color, count);
            }
        }
        for (int i = 0; i < Negres; ++i) feedback.add(BolaNegra);
        for (int i = Negres; i < NUMBOLES; ++i) feedback.add(BolaNula);

        return feedback;
    }

}