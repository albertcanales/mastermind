package domain;

import java.util.List

public class DificultatDificil extends Dificultat {
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.DIFICIL;
    }

    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){
        return null;
    }

}
