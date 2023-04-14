package domain;

import java.util.List;

public interface Maquina {

// Given the solution code, the solve operation uses one of the proposed algorithms (either five guess or the genetic one) to create the list of codes that will lead to the solution. If the algorithm is unable to find the solution in less than maxSteps steps, the returned list will contain a list composed of maxSteps codes. The operation will throw an exception in case the secret code solution is not consistent with the parameters of the current game

    List<List<Integer>>  solve (List<Integer> solution) throws Exception;
}