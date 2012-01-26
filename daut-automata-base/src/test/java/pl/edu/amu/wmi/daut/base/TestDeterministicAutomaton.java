package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;


/*
 * Test Automatu: automat deterministyczny akceptujÄ…cy wszystkie napisy nad 
 * alfabetem {a,b,c} skĹ‚adajÄ…ce siÄ™ dowolnej (w tym zerowej) liczby znakĂłw 'a', 
 * po ktĂłrej wystÄ™pujÄ™ dowolna (ale nie zerowa) 
 * liczba znakĂłw 'b', po ktĂłrej wystÄ™puje dowolna (w tym zerowa) 
 * liczba znakĂłw 'c'.

/**
 * @author kasia1404
 */
public class TestDeterministicAutomaton extends TestCase {
    
    public final void testAutomatonABC (){
        
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();
        
        State q0 = spec.addState();
        State q1 = spec.addState();
        State q2 = spec.addState();
        
        spec.addTransition(q0, q0, new CharTransitionLabel('a'));
        spec.addTransition(q0, q1, new CharTransitionLabel('b'));
        spec.addTransition(q1, q1, new CharTransitionLabel('b'));
        spec.addTransition(q1, q2, new CharTransitionLabel('c'));
        spec.addTransition(q2, q2, new CharTransitionLabel('c'));
        
        spec.markAsInitial(q0);
        spec.markAsFinal(q1);
        spec.markAsFinal(q1);
        
        final TestDeterministicAutomaton automaton = new TestDeterministicAutomaton (spec);
        
        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts("bcc"));
        assertTrue(automaton.accepts("abc"));
        assertTrue(automaton.accepts("aaaab"));
        assertTrue(automaton.accepts("abc"));
        assertTrue(automaton.accepts("aaaabcc"));
        assertTrue(automaton.accepts("aaaaaaaaaabbc"));
        assertTrue(automaton.accepts("aabbcc"));
        assertTrue(automaton.accepts("aaaabccccccccccccccccccccccc"));
        assertTrue(automaton.accepts("aaaabbbbbbcc"));
        assertFalse(automaton.accepts("ccccccccabbbbbbc"));
        assertFalse(automaton.accepts("aaaaaaaaaaaaaaaaaaa"));
        assertFalse(automaton.accepts("c"));
        assertFalse(automaton.accepts("cccccccccac"));
        assertFalse(automaton.accepts("abcabc"));
        assertFalse(automaton.accepts("123"));
        assertFalse(automaton.accepts("c34"));
        assertFalse(automaton.accepts("#$"));
        assertFalse(automaton.accepts("c**@"));
        assertFalse(automaton.accepts("z"));
        assertFalse(automaton.accepts("999"));
        assertFalse(automaton.accepts("?"));
        assertFalse(automaton.accepts("~!~"));
        assertFalse(automaton.accepts("fabian"));
        assertFalse(automaton.accepts("o2"));
                       
    }
    
}
