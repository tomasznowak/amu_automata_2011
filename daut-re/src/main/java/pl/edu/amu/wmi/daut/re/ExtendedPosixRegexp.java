package pl.edu.amu.wmi.daut.re;

import java.util.logging.Logger;
import java.util.logging.Level;

import pl.edu.amu.wmi.daut.base.Acceptor;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.DeterministicAutomaton;
import pl.edu.amu.wmi.daut.base.DeterministicAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EffectiveDeterministicAutomaton;

/**
 *
 * Klasa reprezentująca rozszerzone wyrażenia regularne POSIX.
 *
 */
public class ExtendedPosixRegexp implements Acceptor {

    private String inputRegex;
    private DeterministicAutomaton resultAutomaton;

    /**
     * Konstruktor klasy.
     * inputRegex to wyrazenie regularne zapisane w formie stringa
     */
    public ExtendedPosixRegexp(String inputRegex) {
        try {

            this.inputRegex = inputRegex;

            // ExtendedPosixRegexpOperatorManager manager = new ExtendedPosixRegexpOperatorManager();

            RegexpOperatorTree tree = new RegexpOperatorTree(null, null);
            //tree = RegexpOperatorTree.parse(inputRegex, manager);

            AutomatonSpecification automaton = RegexpUtilities.createAutomatonFromOperatorTree(
                tree);

            DeterministicAutomatonSpecification deterministicAutomaton =
                new EffectiveDeterministicAutomaton();

            AutomataOperations.determinize2(automaton, deterministicAutomaton);

            deterministicAutomaton.makeMinimal(deterministicAutomaton, "abcdefghijklmnopqrstuwyz");

            resultAutomaton = new DeterministicAutomaton(deterministicAutomaton);

        } catch (Exception ex) {
            Logger.getLogger(ExtendedPosixRegexp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda sprawdza czy automat utworzony na bazie wyrazenia akceptuje wejsciowe slowo.
     */
    @Override
    public boolean accepts(String text) {
        return resultAutomaton.accepts(text);
    }

}
