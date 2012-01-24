package pl.edu.amu.wmi.daut.re;

import java.util.List;

/**
 * @author dyskograf Klasa przechowująca narzędzia do obsługi gramatyk..
 */
public class GrammarUtils {

    /**
     * Metoda sprawdzająca, czy podana gramatyka jest w postaci normalnej
     * Chomsky'ego.
     */
    public boolean isChomsky(Grammar g) {

        List<GrammarRule> rules = g.allRules();

        for (GrammarRule rule : rules) {
            if (rule.getArity() > 2
                    || rule.getArity() == 0
                    || rule.getRhsSecondSymbol().isTerminalSymbol()
                    || (rule.getRhsFirstSymbol().isTerminalSymbol()
                            && !(rule.getRhsSecondSymbol().isTerminalSymbol()))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Główna metoda klasy.
     */
    public boolean isLinear(Grammar g) {
        for (GrammarRule rule : g.allRules()) {

            int terminalSymbols = 0;

            for (GrammarSymbol symbol : rule.getRhsSymbols())
                if (symbol.isTerminalSymbol())
                    terminalSymbols++;

            if (terminalSymbols > 1)
                return false;
        }
        return true;
    }

    /**
     * Metoda sprawdzająca, czy podana gramatyka jest w postaci normalnej
     * Greibach.
     */
    public boolean isGreibach(Grammar g) {
        List<GrammarRule> rules = g.allRules();
        for (GrammarRule rule : rules) {
            if (rule.getArity() == 0) {
                return false;
            }
            if (!(rule.getRhsFirstSymbol().isTerminalSymbol())) {
                return false;
            } else if (rule.getArity() >= 2) {
                List<GrammarSymbol> rhsSymbols = rule.getRhsSymbols();
                for (int i = 1; i < rhsSymbols.size(); i++) {
                    if (rhsSymbols.get(i).isTerminalSymbol()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

