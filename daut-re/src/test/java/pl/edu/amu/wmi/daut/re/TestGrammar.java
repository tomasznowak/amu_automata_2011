package pl.edu.amu.wmi.daut.re;

import java.util.Vector;
import java.util.List;
import junit.framework.TestCase;

/**
 * Test klasy Grammar.
 */
public class TestGrammar extends TestCase {

    /**
     * Test sprawdzający działanie wszystkich metod klasy.
     */
    public final void testGrammar() {

        GrammarNonterminalSymbol nonterminal = new GrammarNonterminalSymbol('S');
        GrammarSymbol symbol = new GrammarTerminalSymbol('a');
        GrammarRule rule = new GrammarRule(nonterminal, symbol, nonterminal);
        GrammarRule rule1 = new GrammarRule(nonterminal, symbol);
        List<GrammarRule> rules = new Vector<GrammarRule>();
        rules.add(rule);
        rules.add(rule1);
        Grammar gr = new Grammar(nonterminal);
        Grammar gr1 = new Grammar(rules, nonterminal);
        gr.addRule(rule);
        gr.addRule(rule1);

        assertFalse(gr1.allRules().isEmpty());
        assertTrue(gr.allRules().containsAll(rules));
        assertTrue(gr1.allRules().containsAll(gr.allRules()));
        assertEquals(2, gr.allRules().size());
        assertEquals(gr.getStartSymbol(), gr1.getStartSymbol());
        assertEquals(nonterminal, gr.getStartSymbol());
    }

}
