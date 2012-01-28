package pl.edu.amu.wmi.daut.base;

/**
 * Klasa, z której powinny dziedziczyć wszystkie przejścia
 * z kontekstowym warunkiem.
 */
abstract class ZeroLengthConditionalTransitionLabel extends TransitionLabel {

     /**
     * Metoda ta sprawdza czy char a jest z zakresu [a-zA-Z0-9_].
     */
    public boolean isCharacter(char a) {
        if (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z' || a >= '0' && a <= '9' || a == '_')
            return true;
        return false;
    }

    @Override
    public boolean canBeEpsilon() {
        return true;
    }

    @Override
    protected boolean doIsContextual() {
        return true;
    }

    protected abstract boolean doCheckContext(String s, int position);

    protected TransitionLabel intersectWith(TransitionLabel label) {
        if (label.canBeEpsilon()) {
            return this;
        } else {
            return new EmptyTransitionLabel();
        }
    };
}

