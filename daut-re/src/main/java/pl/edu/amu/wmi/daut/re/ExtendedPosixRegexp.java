package pl.edu.amu.wmi.daut.re;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.amu.wmi.daut.base.Acceptor;
import pl.edu.amu.wmi.daut.base.AutomataOperations;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.DeterministicAutomaton;
import pl.edu.amu.wmi.daut.base.DeterministicAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveDeterministicAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.AutomatonUtilities;
import pl.edu.amu.wmi.daut.base.EffectiveDeterministicAutomaton;
import pl.edu.amu.wmi.daut.re.RegexpOperator;
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
    public ExtendedPosixRegexp (String inputRegex)
    {
        try {

            ExtendedPosixRegexpOperatorManager manager = new ExtendedPosixRegexpOperatorManager();

            RegexpOperatorTree tree = new RegexpOperatorTree(null, null);
            //tree = RegexpOperatorTree.parse(inputRegex, manager);

            AutomatonSpecification automaton = RegexpUtilities.createAutomatonFromOperatorTree(tree);

            DeterministicAutomatonSpecification deterministicAutomaton = new EffectiveDeterministicAutomaton();
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
