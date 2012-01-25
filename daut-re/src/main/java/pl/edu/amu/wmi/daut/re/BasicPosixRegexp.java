/*
package pl.edu.amu.wmi.daut.re;

import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.amu.wmi.daut.base.Acceptor;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.DeterministicAutomaton;
import pl.edu.amu.wmi.daut.base.DeterministicAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EffectiveDeterministicAutomaton;

public class BasicPosixRegexp implements Acceptor{

    private String Regexp;
    private DeterministicAutomaton finalAutomaton;

    /**
     * Konsruktor, wczytuje z klawiatury String reprezentujący wyrażenie regularne,
     * oraz tworzy automat akceptujący to wyrażenie.
     */
/*  public BasicPosixRegexp(String text) {

        try {
            Regexp = text;

            BasicPosixRegexpOperatorManager manager = new BasicPosixRegexpOperatorManager();
            RegexpOperatorTree Regexptree = RegexpOperatorTree.parse(Regexp, manager);

            AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(Regexptree);
            DeterministicAutomatonSpecification deterministicAutomaton =
                new EffectiveDeterministicAutomaton();
            AutomataOperations.determinize2(automaton, deterministicAutomaton);
            finalAutomaton = new DeterministicAutomaton(deterministicAutomaton);

        } catch (Exception ex) {
            Logger.getLogger(BasicPosixRegexp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public boolean accepts(String text) {
        return finalAutomaton.accepts(text);
    }
}*/
