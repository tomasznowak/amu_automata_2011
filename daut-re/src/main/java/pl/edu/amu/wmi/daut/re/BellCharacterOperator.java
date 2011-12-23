package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;

/**
 * Klasa reprezentująca znak "dźwiękowy" (znak o kodzie 7, \a w wyrażeniach regularnych).
 */
public class BellCharacterOperator extends NullaryRegexpOperator {

    private static final int NUMBER_SEVEN = 7;

    @Override
    public AutomatonSpecification createFixedAutomaton() {
        return new NaiveAutomatonSpecification().makeOneTransitionAutomaton(((char) NUMBER_SEVEN));
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 0;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new BellCharacterOperator();
        }
    }
}
