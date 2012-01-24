package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;

/**
 *
 * Klasa reprezentująca niejawny, dwuargumentowy operator koniunkcji.
 * Nie odpowiada to żadnemu operatorowi standardowych wyrażeń regularnych.
 */
public class ConjunctionOperator extends BinaryRegexpOperator {

    /**
     * Domyślny konstruktor.
     */
    public ConjunctionOperator() { }

    @Override
    public AutomatonSpecification createAutomatonFromTwoAutomata(
           AutomatonSpecification leftSubautomaton,
           AutomatonSpecification rightSubautomaton) {
        return AutomataOperations.intersection(leftSubautomaton, rightSubautomaton);
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "CONJUNCTION";
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends BinaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 0;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new ConjunctionOperator();
        }
    }
}
