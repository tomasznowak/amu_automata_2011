package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;


/**
*
* Klasa RangeEscapeSignOperator reprezentująca zakres znaków,
* w którym operatory traktujemy jak normalne znaki.
*/
public class RangeEscapeSignOperator extends NullaryRegexpOperator {

    private String s;

    @Override
    public AutomatonSpecification createFixedAutomaton() {
      AutomatonSpecification automaton = new NaiveAutomatonSpecification();
      automaton.markAsInitial(automaton.addState());
      automaton.markAsFinal(automaton.addTransitionSequence(automaton.getInitialState(), this.s));
      return automaton;
    }

/**
* Konstruktor klasy.
* @param s - napis, w którym operatory nie mają być interpretowane
*/
    public RangeEscapeSignOperator(String s) {
        this.setString(s);
    }

    private void setString(String str) {
        this.s = str;
    }

/**
* Fabryka operatora.
*/
    public static class Factory extends NullaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
          return 1;
        }

        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
          return new RangeEscapeSignOperator(params.get(0));
        }
    }
}
