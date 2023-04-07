package domain;

import java.util.List;

class DificultatFacil extends Dificultat {
    @Override
    NivellDificultat getNivellDificultat(){
        return NivellDificultat.FACIL;
    }

    @Override
    List<Integer> validarSequencia(List<Integer> solucio, List<Integer> intent){
        return null;
    }

}