package domain;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Classe per poder executar els tests en forma de JAR
 * @author Albert Canales Ros
 */
class TestRunner {

    /**
     * Punt d'entrada per executar els tests
     * @param myclass La classe de test que es vol provar
     */
    public static void runTestClass(Class<?> myclass) {
        System.out.printf("Testing %s ", myclass.getName());
        Result result = JUnitCore.runClasses(myclass);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("PASSED");
        } else {
            System.out.println("FAILED");
        }
    }
}