package pl.edu.amu.wmi.daut.base;

/**
 * @author KubaZ
 * Klasa przejścia, które odpowiada operatorowi \b z wyrażeń regularnych,
 * jeśli włączono tryb multiline.
 */
public class WordBoundaryTransitionLabel extends ZeroLengthConditionalTransitionLabel {


    @Override
    public boolean doCheckContext(String s, int position) {
        char a;
        if (s.length() < position || position < 0)
             throw new PositionOutOfStringBordersException();
        if (position == s.length()) {
            a = s.charAt(position - 1);
            if (isCharacter(a))
                return true;
            return false;
        }
        if (position == 0) {
            a = s.charAt(position);
            if (isCharacter(a))
                return true;
            return false;
        }
        a = s.charAt(position);
        if (isCharacter(a)) {
            a = s.charAt(position - 1);
            if (!isCharacter(a))
                return true;
        } else {
            a = s.charAt(position - 1);
            if (isCharacter(a))
                return true;
        }
        return false;
    };

    @Override
    public boolean canAcceptCharacter(char c) {
        return false;
    };

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "WordBoundary";
    }
}
