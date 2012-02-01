package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Test klasy NondeterministicAutomatonByThompsonApproach.
 */
public class TestNondeterministicAutomatonByThompsonApproach extends TestCase {

    /**
     * Pierwszy test (przykładowy prosty automat).
     */
    public final void testSimpleAutomaton() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();
        State q2a = spec.addState();
        State q3a = spec.addState();
        State q4a = spec.addState();
        State q5a = spec.addState();

        spec.addTransition(q0a, q1a, new CharTransitionLabel('a'));
        spec.addTransition(q0a, q2a, new CharTransitionLabel('b'));
        spec.addTransition(q0a, q5a, new CharTransitionLabel('b'));
        spec.addTransition(q1a, q2a, new CharTransitionLabel('c'));
        spec.addTransition(q1a, q3a, new CharTransitionLabel('c'));
        spec.addTransition(q1a, q4a, new CharTransitionLabel('b'));
        spec.addTransition(q2a, q4a, new CharTransitionLabel('a'));
        spec.addTransition(q2a, q5a, new CharTransitionLabel('a'));
        spec.addTransition(q3a, q5a, new CharTransitionLabel('b'));
        spec.addTransition(q4a, q3a, new CharTransitionLabel('c'));
        spec.addTransition(q4a, q5a, new CharTransitionLabel('a'));
        spec.addTransition(q5a, q0a, new CharTransitionLabel('a'));
        spec.addLoop(q5a, new CharTransitionLabel('a'));

        spec.markAsInitial(q0a);
        spec.markAsFinal(q3a);
        spec.markAsFinal(q4a);
        spec.markAsFinal(q5a);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts("abc"));
        assertTrue(automaton.accepts("ba"));
        assertTrue(automaton.accepts("ac"));
        assertTrue(automaton.accepts("baaaaaaaaa"));
        assertTrue(automaton.accepts("b"));
        assertFalse(automaton.accepts("cccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaa"));
        assertFalse(automaton.accepts("bcccccc"));
        assertFalse(automaton.accepts("z"));
    }

    /**
     * Drugi test (tylko epsilon-przejścia).
     */
    public final void testOnlyEpsilonTransitionLabel() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();
        State q2a = spec.addState();
        State q3a = spec.addState();

        spec.addTransition(q0a, q1a, new EpsilonTransitionLabel());
        spec.addTransition(q0a, q2a, new EpsilonTransitionLabel());
        spec.addTransition(q1a, q3a, new EpsilonTransitionLabel());
        spec.addTransition(q2a, q3a, new EpsilonTransitionLabel());

        spec.markAsInitial(q0a);
        spec.markAsFinal(q3a);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("uam"));
    }

    /**
     * Trzeci test (pusty automat).
     */
    public final void testEmptyAutomaton() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertFalse(automaton.accepts("cccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaa"));
        assertFalse(automaton.accepts("bcccccc"));
        assertFalse(automaton.accepts("z"));
    }

    /**
     * Czwarty test (Od stanu początkowego odchodzą 3 epsilon-przejścia do
     * trzech różnych stanów. Dopiero od owych trzech stanów występują
     * "normalne" przejścia - tj. po znaku).
     */
    public final void testOnlyEpsilonTransitionLabelFromInitialState() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();
        State q2a = spec.addState();
        State q3a = spec.addState();
        State q4a = spec.addState();
        State q5a = spec.addState();
        State q6a = spec.addState();
        State q7a = spec.addState();

        spec.addTransition(q0a, q1a, new EpsilonTransitionLabel());
        spec.addTransition(q0a, q2a, new EpsilonTransitionLabel());
        spec.addTransition(q0a, q3a, new EpsilonTransitionLabel());
        spec.addTransition(q1a, q4a, new CharTransitionLabel('a'));
        spec.addTransition(q1a, q5a, new CharTransitionLabel('a'));
        spec.addTransition(q2a, q5a, new CharTransitionLabel('b'));
        spec.addTransition(q3a, q5a, new CharTransitionLabel('c'));
        spec.addTransition(q3a, q6a, new CharTransitionLabel('a'));
        spec.addTransition(q3a, q6a, new CharTransitionLabel('b'));
        spec.addTransition(q4a, q5a, new CharTransitionLabel('b'));
        spec.addTransition(q4a, q7a, new CharTransitionLabel('b'));
        spec.addTransition(q5a, q7a, new CharTransitionLabel('a'));
        spec.addTransition(q5a, q6a, new CharTransitionLabel('a'));
        spec.addTransition(q6a, q7a, new CharTransitionLabel('c'));
        spec.addTransition(q6a, q5a, new CharTransitionLabel('b'));
        spec.addLoop(q3a, new CharTransitionLabel('c'));
        spec.addLoop(q6a, new CharTransitionLabel('c'));
        spec.addLoop(q7a, new CharTransitionLabel('a'));
        spec.addLoop(q7a, new CharTransitionLabel('b'));
        spec.addLoop(q7a, new CharTransitionLabel('c'));

        spec.markAsInitial(q0a);
        spec.markAsFinal(q7a);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts("abc"));
        assertTrue(automaton.accepts("ac"));
        assertTrue(automaton.accepts("baaaaaaa"));
        assertTrue(automaton.accepts("ccccccca"));
        assertFalse(automaton.accepts("ccc"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("uam"));
    }

    /**
     * Piąty test (Do stanu końcowego prowadzą tylko epsilon przejścia.
     * Występują pętle epsilon).
     */
    public final void testOnlyEpsilonTransitionLabelToFinalState() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();
        State q2a = spec.addState();
        State q3a = spec.addState();
        State q4a = spec.addState();

        spec.addTransition(q1a, q4a, new EpsilonTransitionLabel());
        spec.addTransition(q2a, q4a, new EpsilonTransitionLabel());
        spec.addTransition(q3a, q4a, new EpsilonTransitionLabel());
        spec.addTransition(q0a, q1a, new CharTransitionLabel('a'));
        spec.addTransition(q0a, q2a, new CharTransitionLabel('b'));
        spec.addTransition(q0a, q3a, new CharTransitionLabel('b'));
        spec.addTransition(q1a, q2a, new CharTransitionLabel('a'));
        spec.addTransition(q2a, q0a, new CharTransitionLabel('b'));
        spec.addTransition(q3a, q2a, new CharTransitionLabel('c'));
        spec.addLoop(q1a, new CharTransitionLabel('c'));
        spec.addLoop(q3a, new CharTransitionLabel('a'));
        spec.addLoop(q2a, new CharTransitionLabel('b'));
        spec.addLoop(q1a, new EpsilonTransitionLabel());
        spec.addLoop(q3a, new EpsilonTransitionLabel());

        spec.markAsInitial(q0a);
        spec.markAsFinal(q4a);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts("b"));
        assertFalse(automaton.accepts("uam"));
    }
    /**
     * Szósty test (tylko jeden stan, brak przejść).
     */
    public final void testOneState() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        spec.markAsInitial(q0a);
        spec.markAsFinal(q0a);

        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("cccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaa"));
        assertFalse(automaton.accepts("bcccccc"));
        assertFalse(automaton.accepts("z"));
    }

    /**
     * Siódmy test (dwa stany, epsilon-przejście pomiędzy nimi).
     */

    public final void testTwoStatesOneEpsilonTrasitionLabel() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0a = spec.addState();
        State q1a = spec.addState();

        spec.addTransition(q0a, q1a, new EpsilonTransitionLabel());

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        spec.markAsInitial(q0a);
        spec.markAsFinal(q1a);

        assertTrue(automaton.accepts(""));
        assertFalse(automaton.accepts("cccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaa"));
        assertFalse(automaton.accepts("bcccccc"));
        assertFalse(automaton.accepts("z"));
    }

    /**
     * Ósmy test (automat z przynajmniej jednym epsilon-przejściem
     * akceptujący wszystkie napisy złożone z napisu "abcd"
     * powielonego dowolną liczbę razy przynajmniej raz).
     */
    public final void testNondeterministicAutomatonWithEpsilonTransition() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();

        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        State q3 = spec.addState();
        State q4 = spec.addState();

        spec.addTransition(q0, q1, new CharTransitionLabel('a'));
        spec.addTransition(q1, q2, new CharTransitionLabel('b'));
        spec.addTransition(q2, q3, new CharTransitionLabel('c'));
        spec.addTransition(q3, q4, new CharTransitionLabel('d'));
        spec.addTransition(q4, q0, new EpsilonTransitionLabel());
        spec.addTransition(q4, q1, new CharTransitionLabel('a'));

        spec.markAsInitial(q0);
        spec.markAsFinal(q4);

        final NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec);

        assertTrue(automaton.accepts("abcd"));
        assertTrue(automaton.accepts("abcdabcd"));
        assertTrue(automaton.accepts("abcdabcdabcd"));
        assertTrue(automaton.accepts("abcdabcdabcdabcd"));
        assertTrue(automaton.accepts("abcdabcdabcdabcdabcd"));

        assertFalse(automaton.accepts("aabcd"));
        assertFalse(automaton.accepts("abbcd"));
        assertFalse(automaton.accepts("abccd"));
        assertFalse(automaton.accepts("abcdd"));
        assertFalse(automaton.accepts("aabbccdd"));
        assertFalse(automaton.accepts(" "));
        assertFalse(automaton.accepts("abc"));
        assertFalse(automaton.accepts("+"));
        assertFalse(automaton.accepts("1234"));
        assertFalse(automaton.accepts("fghi"));
        assertFalse(automaton.accepts("dddd"));
        assertFalse(automaton.accepts("a123"));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts("c"));
        assertFalse(automaton.accepts("d"));
    }
}
