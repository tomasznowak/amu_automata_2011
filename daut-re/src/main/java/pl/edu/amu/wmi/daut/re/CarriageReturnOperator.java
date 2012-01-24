package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;



/**
* Klasa reprezentująca znak powrotu karetki
* (znak o kodzie 13, \r w wyrażeniach regularnych).
*/
public class CarriageReturnOperator extends RegexpOperator {

        @Override
        public int arity() {
        return 0;
    }

        @Override
        protected final AutomatonSpecification doCreateAutomaton(
        List<AutomatonSpecification> subautomata) {

        return createFixedAutomaton();
    }
 /**
  * Metoda budująca automat.
  */
        public AutomatonSpecification createFixedAutomaton() {
        return new
             NaiveAutomatonSpecification().makeOneTransitionAutomaton('\13');
    }

/**
* Fabryka operatora.
*/
public static class Factory extends RegexpOperatorFactory {

        @Override
        public int arity() {
            return 0;
        }

        @Override
        public int numberOfParams() {
            return 0;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
            return new CarriageReturnOperator();
        }
    }


/**
* Metoda toString().
*/
@Override
public String toString() {
        return "/r";
    }

}
