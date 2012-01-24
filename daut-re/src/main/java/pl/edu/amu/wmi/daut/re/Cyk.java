package pl.edu.amu.wmi.daut.re;

import java.util.List;

/**
 * Klasa reprezentująca algorytm CYK.
 */
public class Cyk {
    private Grammar grammar;

    /**
     * Konstruktor klasy.
     */
    public Cyk(Grammar grammar) {
        this.grammar = grammar;
    }

    /**
     * Sprawdza czy dane słowo można wyprowadzić za pomocą danej gramatyki.
     */
    public boolean accepts(List<GrammarTerminalSymbol> word) {
        int wordSize = word.size();
        int grammarSize = grammar.allRules().size();
        GrammarSymbol[][][] tab = new GrammarSymbol[wordSize][][];
        for (int i = 0; i < wordSize; i++) {
            tab[i] = new GrammarSymbol[wordSize][grammarSize];
        }
        for (int i = 0; i < wordSize; i++) {
            for (int j = 0; j < wordSize - i; j++) {
                if (i == 0) {
                    for (int k = 0; k < grammarSize; k++) {
                        String wordChar = word.get(j).toString();
                        String firstSymbol = grammar.allRules().get(k)
                                .getRhsFirstSymbol().toString();
                        if (wordChar.equals(firstSymbol)) {
                            tab[i][j][k] = grammar.allRules().get(k).getLhsSymbol();
                        }
                    }
                } else {
                    for (int k = 0; k < i; k++) {
                        for (int n = 0; n < grammarSize; n++) {
                            for (int m = 0; m < grammarSize; m++) {
                                GrammarSymbol first = tab[k][j][n];
                                GrammarSymbol secound = tab[i - k - 1][j + k + 1][m];
                                if (first != null && secound != null) {
                                    for (int l = 0; l < grammarSize; l++) {
                                        GrammarRule gr = grammar.allRules().get(l);
                                        String firstStr = first.toString();
                                        String firstGrStr = gr.getRhsFirstSymbol().toString();
                                        if (firstStr.equals(firstGrStr)) {
                                            String secoundStr = secound.toString();
                                            String secoundGrStr = gr.getRhsSecondSymbol()
                                                                    .toString();
                                            if (secoundStr.equals(secoundGrStr)) {
                                                tab[i][j][l] = gr.getLhsSymbol();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < grammarSize; i++) {
            if (tab[wordSize - 1][0][i] == grammar.getStartSymbol()) {
                return true;
            }
        }
        return false;
    }
}
