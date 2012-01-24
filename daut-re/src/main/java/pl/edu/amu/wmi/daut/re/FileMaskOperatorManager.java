package pl.edu.amu.wmi.daut.re;
import java.util.Arrays;

/**
 * Zarzadca operatorow mask plików.
 */
public class FileMaskOperatorManager extends RegexpOperatorManager {


    private static final int HIGH_PRIORITY = 5;
    private static final int MEDIUM_PRIORITY = 3;
    private static final int LOW_PRIORITY = 1;

    /**
     * Konstruktor klasy.
     */

    public FileMaskOperatorManager() {
        addOperator("*", new AnyStringOperator.Factory(),
                    Arrays.<String>asList("", "*"), MEDIUM_PRIORITY);
        addOperator("?", new AnyCharOperator.Factory(),
                    Arrays.<String>asList("?"), MEDIUM_PRIORITY);
        addOperator("{}", new DoNothingOperator.Factory(),
                    Arrays.<String>asList("{", "}"), HIGH_PRIORITY);
        addOperator(",", new AlternativeOperator.Factory(),
                    Arrays.<String>asList("", ",", ""), LOW_PRIORITY);
        addOperator("\"\"", new RangeEscapeSignOperator.Factory(),
                    Arrays.<String>asList("\"", "\""), HIGH_PRIORITY);
        addOperator("\'\'", new RangeEscapeSignOperator.Factory(),
                     Arrays.<String>asList("\'", "\'"), HIGH_PRIORITY);
    }
}
