package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.CharClassTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 * Klasa, reprezentująca klasę znaków z wyrażeń regularnych.
 */
public class CharClassOperator extends NullaryRegexpOperator {
    private String charClass;

    /**
     * Konstruktor, pobiera pożądaną klasę znaków.
     */
    public CharClassOperator(String c) {
        this.charClass = c;
    }
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();
        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.addTransition(q0, q1,
            new CharClassTransitionLabel(charClass));
        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        return automaton;
    }

     /**
      * Fabryka operatora.
      */
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new CharClassOperator(params.get(0));
        }
     }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "CHAR_CLASS_" + charClass;
    }
}
