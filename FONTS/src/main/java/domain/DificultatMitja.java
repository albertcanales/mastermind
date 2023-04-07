package domain;

import java.util.List;

class DificultatMitja extends Dificultat {
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.MITJA;
    }

    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){
        return null;
    }

}
