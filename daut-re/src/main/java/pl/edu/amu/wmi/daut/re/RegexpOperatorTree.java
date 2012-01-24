package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca.
 */
public class RegexpOperatorTree {

    private static class ArityException extends RuntimeException {

        public ArityException() {
        }
    }
    private RegexpOperator root;
    private List<RegexpOperatorTree> subtrees;

    /**
     * Konstruuje drzewo z korzeniem operator i poddrzewami subtrees.
     *
     * Jeśli liczba poddrzew nie zgadza się z arnością operatora, powinien być wyrzucany wyjątek.
     */
    RegexpOperatorTree(RegexpOperator operator, List<RegexpOperatorTree> subtrees) {

        if (operator.arity() == subtrees.size()) {
            this.root = operator;
            this.subtrees = subtrees;
        } else {
            throw new ArityException();
        }
    }

    /**
     * Zwraca korzeń.
     */
    RegexpOperator getRoot() {
        return root;
    }

    /**
     * Zwraca listę poddrzew.
     */
    List<RegexpOperatorTree> getSubtrees() {
        return subtrees;
    }

    /**
     * Zwraca  drzewo w formie bardziej czytelnej,
     * np. dla wyrażenia (ab)*|c wypisze:
     * ALTERNATIVE
     * |_KLEENE_STAR
     * |  |_CONCATENATION
     * |     |_SINGLE_CHAR_a
     * |     |_SINGLE_CHAR_b
     * |_SINGLE_CHAR_c
     */
    String getHumanReadableFormat() {
        StringBuffer buffer = new StringBuffer();
        List<RegexpOperatorTree> sub = new ArrayList<RegexpOperatorTree>();

        buffer.append(this.getRoot().toString() + "\n");

        sub.addAll(getSubtrees());
        for (RegexpOperatorTree tree : sub) {
            doGetHumanReadableFormat(tree, 1, buffer);
        }

        return buffer.toString();
    }

    void doGetHumanReadableFormat(RegexpOperatorTree tree, int i, StringBuffer buffer) {

        if (i > 1) {
            buffer.append("|");
            for (int j = 1; j < i; j++) {
                buffer.append("  ");
            }
        }

        buffer.append("|_" + tree.getRoot().toString() + "\n");

        i++;

        for (int j = 0; j < tree.getRoot().arity(); j++) {
            doGetHumanReadableFormat(tree.getSubtrees().get(j), i, buffer);
        }

    }
}
