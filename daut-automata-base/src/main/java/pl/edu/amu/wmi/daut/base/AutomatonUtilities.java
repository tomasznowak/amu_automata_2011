package pl.edu.amu.wmi.daut.base;

import java.util.HashSet;
import java.util.Set;

/**
 * Pomocnicza klasa do automatow.
 */
public final class AutomatonUtilities {

    private AutomatonUtilities() {
    }

    /**
     * Ustala alfabet.
     */
    public static Set<Character> getAlphabet(
        AutomatonSpecification automaton, Set<Character> superset) {

        Set<Character> alphabet = new HashSet<Character>();

        for (State s : automaton.allStates()) {
            for (OutgoingTransition ot : automaton.allOutgoingTransitions(s)) {
                for (Character c : superset) {

                    boolean isAlreadyIn = false;
                    for (Character ch : alphabet) {
                        if (ch.equals(c)) {
                            isAlreadyIn = true;
                            break;
                        }
                    }

                    if (!isAlreadyIn && ot.getTransitionLabel().canAcceptCharacter(c)) {
                        alphabet.add(c);
                    }
                }
            }
        }

        return alphabet;

    }
}
