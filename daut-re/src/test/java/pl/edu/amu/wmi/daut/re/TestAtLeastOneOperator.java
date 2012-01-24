package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.re.AtLeastOneOperator.Factory;


/**
 *
 * Test klasy AtLeastOneOperator.
 */
public class TestAtLeastOneOperator extends TestCase {
    /**
     * Test metody createAutomatonFromOneAutomaton.
     */
    public final void testCreateAutomatonFromOneAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q1, q2, new CharTransitionLabel('b'));
        automaton.addLoop(q1, new CharTransitionLabel('a'));
        automaton.addLoop(q2, new CharTransitionLabel('b'));

        automaton.markAsInitial(q0);
        automaton.markAsFinal(q2);

        AtLeastOneOperator operator = new AtLeastOneOperator();
        NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(
                operator.createAutomatonFromOneAutomaton(automaton));

        assertTrue(result.accepts("aaaab"));
        assertTrue(result.accepts("abbbbb"));
        assertTrue(result.accepts("aaaaaaaaaaaabb"));
        assertTrue(result.accepts("abbbaaaab"));
        assertTrue(result.accepts("abababb"));

        assertFalse(automaton.acceptEmptyWord());
        assertFalse(result.accepts("baba"));
        assertFalse(result.accepts("baaaaaaaaa"));
        assertFalse(result.accepts("cojatestujem"));
        assertFalse(result.accepts("bartlomiejburczymucha"));
    }
    /**
     * Test fabryki.
     */
    public final void testFactory() {

        Factory factory = new Factory();
        ArrayList<String> params = new ArrayList<String>();
        assertEquals(factory.createOperator(params).getClass(),
            new AtLeastOneOperator().getClass());
    }
}
