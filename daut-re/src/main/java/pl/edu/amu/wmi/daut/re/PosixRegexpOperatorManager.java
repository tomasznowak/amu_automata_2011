package pl.edu.amu.wmi.daut.re;

import java.util.Arrays;

/**
 * Zarzadca tych operatorow, ktore sa wspolne
 * dla podstawowych i rozszerzonych wyrazen
 * regularnych POSIX.
 */
class PosixRegexpOperatorManager extends RegexpOperatorManager {

    public static final int PRIORITY_4 = 4;
    public static final int PRIORITY_3 = 3;

    PosixRegexpOperatorManager() {
        addOperator("*", new KleeneStarOperator.Factory(),
                    Arrays.<String>asList("", "*"), PRIORITY_3);
        addOperator(".", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("."), PRIORITY_4);
    }
}
