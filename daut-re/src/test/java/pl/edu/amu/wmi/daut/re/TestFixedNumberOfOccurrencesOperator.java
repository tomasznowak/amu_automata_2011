package pl.edu.amu.wmi.daut.re;


import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

/**
 * Test klasy FixedNumberOfOccurrencesOperator.
 */
public class TestFixedNumberOfOccurrencesOperator extends TestCase {

    /**
    * Test 1. Automat z jednym przejściem na dwóch stanach.
    */
    public final void testTwoStatesOneTransitionLabel() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));

        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        FixedNumberOfOccurrencesOperator oper =
                new FixedNumberOfOccurrencesOperator(4);

        NondeterministicAutomatonByThompsonApproach result =
            new NondeterministicAutomatonByThompsonApproach(
            oper.createAutomatonFromOneAutomaton(automaton));


        assertTrue(result.accepts("aaaa"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("a"));
        assertFalse(result.accepts("aa"));
        assertFalse(result.accepts("aaa"));
        assertFalse(result.accepts("aaaaa"));
        assertFalse(result.accepts("ManchesterUnited"));
    }

    /**
    * Test 2. Automat akceptuje słowa a(b^n)a(d^n)b oraz (a^n)c(d^n)b.
    */
    public final void testExemplaryAutomaton() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        State q3 = automaton.addState();
        State q4 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q1, q2, new CharTransitionLabel('a'));
        automaton.addTransition(q0, q3, new CharTransitionLabel('a'));
        automaton.addTransition(q3, q2, new CharTransitionLabel('c'));
        automaton.addTransition(q2, q4, new CharTransitionLabel('b'));
        automaton.addLoop(q1, new CharTransitionLabel('b'));
        automaton.addLoop(q2, new CharTransitionLabel('d'));
        automaton.addLoop(q3, new CharTransitionLabel('a'));


        automaton.markAsInitial(q0);
        automaton.markAsFinal(q4);

        FixedNumberOfOccurrencesOperator oper =
                new FixedNumberOfOccurrencesOperator(2);

        NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));

        assertTrue(result.accepts("aacdbaacdb"));
        assertTrue(result.accepts("acbacb"));
        assertTrue(result.accepts("abbababbab"));
        assertFalse(result.accepts("aacbc"));
        assertFalse(result.accepts("aacdb"));
        assertFalse(result.accepts("acbacbacb"));
        assertFalse(result.accepts("false"));
        assertFalse(result.accepts("ManchesterUnited"));
    }


    /**
    * Test 3. Pusty automat.
    */
    public final void testEmptyAutomaton() {

          AutomatonSpecification automaton = new NaiveAutomatonSpecification();

          FixedNumberOfOccurrencesOperator oper =
                new FixedNumberOfOccurrencesOperator(666);

          NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));

          assertFalse(result.accepts("ManchesterUnited"));
     }

    /**
    * Test 4. Automat z trzema stanami. Od początkowego stanu q0 wychodzą dwa
    * przejścia do stanu do stanu q1 poprzez 'a' i do stanu q2 poprzez 'b'.
    * Stany q1 i q1 są stanami akceptowalnymi.
    */
    public final void testThreeStatesTwoFinalStates() {

        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        State q2 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('a'));
        automaton.addTransition(q0, q2, new CharTransitionLabel('b'));

        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);
        automaton.markAsFinal(q2);

        FixedNumberOfOccurrencesOperator oper =
                new FixedNumberOfOccurrencesOperator(3);

        NondeterministicAutomatonByThompsonApproach result =
        new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));


        assertTrue(result.accepts("aaa"));
        assertTrue(result.accepts("bba"));
        assertTrue(result.accepts("aab"));
        assertFalse(result.accepts("aabb"));
        assertFalse(result.accepts("ab"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts("LechPoznan"));
}

    /**
    * Test 5. Mnożnik n=0.
    */
    public final void testMultiplierEqualsZero() {
 
        AutomatonSpecification automaton = new NaiveAutomatonSpecification();

        State q0 = automaton.addState();
        State q1 = automaton.addState();
        automaton.addTransition(q0, q1, new CharTransitionLabel('x'));

        automaton.markAsInitial(q0);
        automaton.markAsFinal(q1);

        FixedNumberOfOccurrencesOperator oper =
                new FixedNumberOfOccurrencesOperator(0);

        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(
                oper.createAutomatonFromOneAutomaton(automaton));

        assertTrue(result.accepts(""));
        assertFalse(result.accepts("ManchesterUnited"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts("bbbb"));
     }

    /**
     * Test fabryki.
     */
    public void testFactory() {

        RegexpOperatorFactory factory = new FixedNumberOfOccurrencesOperator.Factory();
        List<String> params = new ArrayList();
        params.add("1");
        RegexpOperator operator2 = factory.createOperator(params);
        int arity = operator2.arity();
        assertEquals(arity, 1);
    }
}
