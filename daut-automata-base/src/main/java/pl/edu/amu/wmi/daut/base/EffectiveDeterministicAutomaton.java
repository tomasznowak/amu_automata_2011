package pl.edu.amu.wmi.daut.base;
import java.util.HashSet;
import java.util.Vector;
import java.util.List;


/**
 * Szybka, ale dość pamięciożerna, implementacja automatu deterministycznego.
 */
public class EffectiveDeterministicAutomaton extends DeterministicAutomatonSpecification
{
    /**
     * Klasa reprezentująca stan.
     */
    private static class MyState implements State {
        private static final int DEFAULT_ARRAY_LENGTH = 256;
        private MyState[] mCharacterTargetState;
        private int mCharacterTargetStateLength;
        private MyState mEpsilonTargetState;
        private boolean mHasCharacterTransition;
        private boolean mIsFinal;
        private Vector<OutgoingTransition> mOutgoingTransitions;
        private EffectiveDeterministicAutomaton mOwner;


        /**
         * Konstruktor.
         */
        public MyState(EffectiveDeterministicAutomaton owner) {
            mCharacterTargetStateLength = DEFAULT_ARRAY_LENGTH;
            mCharacterTargetState = new MyState[mCharacterTargetStateLength];
            for (int i = 0; i < mCharacterTargetStateLength; ++i) {
                mCharacterTargetState[i] = null;
            }
            mEpsilonTargetState = null;
            mHasCharacterTransition = false;
            mIsFinal = false;
            mOutgoingTransitions = new Vector<OutgoingTransition>();
            mOwner = owner;
        }


        /**
         * Dodaje przejście wychodzące z tego stanu.
         */
        private void addOutgoingTransition(OutgoingTransition transition) {
            mOutgoingTransitions.addElement(transition);
        }


        /**
         * Zwraca automat, do którego ten stan należy.
         */
        public EffectiveDeterministicAutomaton getOwner() {
            return mOwner;
        }


        /**
         * Zwraca docelowy stan po epsilonie (lub null, jeśli nie ma przejścia
         * po epsilonie).
         */
        public MyState getEpsilonTargetState() {
            return mEpsilonTargetState;
        }


        /**
         * Zwraca listę wszystkich wychodzących przejść.
         */
        public Vector<OutgoingTransition> getOutgoingTransitions() {
            return mOutgoingTransitions;
        }


        /**
         * Zwraca stan, do którego można przejść po podanym znaku (lub null
         * jeśli przejścia po takim znaku nie ma).
         */
        public MyState getTargetState(char c) {
            return (c < mCharacterTargetStateLength ? mCharacterTargetState[c] : null);
        }


        /**
         * Zwraca true, jeśli istnieje przynajmniej jedno przejście do innego
         * stanu po znaku.
         */
        public boolean hasCharacterTransition() {
            return mHasCharacterTransition;
        }


        /**
         * Zwraca true, jeśli istnieje przejście do innego stanu po epsilonie.
         */
        public boolean hasEpsilonTransition() {
            return (mEpsilonTargetState != null);
        }


        /**
         * Zwraca true, jeśli stan jest akceptujący.
         */
        public boolean isFinal() {
            return mIsFinal;
        }


        /**
         * Ustawia stan docelowy dla przejścia po epsilonie. W przypadku wykrycia
         * niedeterminizu, wyrzuca UnsupportedOperationException.
         */
        public void setEpsilonTargetState(MyState state) {
            if (mHasCharacterTransition
               || (mEpsilonTargetState != null && mEpsilonTargetState != state))
                throw new UnsupportedOperationException();
            mEpsilonTargetState = state;
        }


        /**
         * Zmienia stan na akceptujący lub nieakceptujący.
         */
        public void setFinal(boolean value) {
            mIsFinal = value;
        }


        /**
         * Ustawia stan docelowy dla przejścia po danym znaku. W przypadku
         * wykrycia niedeterminizmu wyrzuca UnsupportedOperationException.
         */
        public void setTargetState(char c, MyState state) {
            if (mEpsilonTargetState != null)
                throw new UnsupportedOperationException();

            if (c >= mCharacterTargetStateLength) {
                MyState[] oldArray = mCharacterTargetState;
                int oldArrayLength = mCharacterTargetStateLength;
                if (mCharacterTargetStateLength == 0)
                    mCharacterTargetStateLength = c + 1;
                else while (true) {
                    mCharacterTargetStateLength *= 2;
                    if (mCharacterTargetStateLength >= c + 1) {
                        if (mCharacterTargetStateLength > Character.MAX_VALUE)
                            mCharacterTargetStateLength = Character.MAX_VALUE + 1;
                        break;
                    }
                }
                mCharacterTargetState = new MyState[mCharacterTargetStateLength];
                for (int j = 0; j < oldArrayLength; ++j)
                    mCharacterTargetState[j] = oldArray[j];
                for (int j = oldArrayLength; j < mCharacterTargetStateLength; ++j)
                    mCharacterTargetState[j] = null;
            }

            if (mCharacterTargetState[c] != null && mCharacterTargetState[c] != state)
                throw new UnsupportedOperationException();

            mCharacterTargetState[c] = state;
            mHasCharacterTransition = true;
        }
    }


    private MyState mInitialState;
    private Vector<State> mStates;


    @Override
    public State addState() {
        MyState myState = new MyState(this);
        mStates.addElement(myState);
        return myState;
    }


    @Override
    public void addTransition(State from, State to, TransitionLabel label) {
        if (label.isEmpty())
            return;

        MyState myFrom = assertStateValid(from);
        MyState myTo = assertStateValid(to);

        if (label instanceof CharTransitionLabel) {
            CharTransitionLabel l = (CharTransitionLabel) label;
            myFrom.setTargetState(l.getChar(), myTo);
        } else if (label instanceof CharRangeTransitionLabel) {
            CharRangeTransitionLabel l = (CharRangeTransitionLabel) label;
            for (int i = l.getSecondChar(); i >= l.getFirstChar(); --i)
                myFrom.setTargetState((char) i, myTo);
        } else if (label instanceof CharSetTransitionLabel) {
            CharSetTransitionLabel l = (CharSetTransitionLabel) label;
            HashSet<Character> characters = l.getCharSet();
            for (Character c : characters)
                myFrom.setTargetState(c, myTo);
        } else for (int i = 0; i <= Character.MAX_VALUE; ++i) {
            char c = (char) i;
            if (label.canAcceptCharacter(c))
                    myFrom.setTargetState(c, myTo);
        }

        if (label.canBeEpsilon())
            myFrom.setEpsilonTargetState(myTo);

        myFrom.addOutgoingTransition(new OutgoingTransition(label, to));
    }


    @Override
    public List<OutgoingTransition> allOutgoingTransitions(State state) {
        MyState myState = assertStateValid(state);
        return myState.getOutgoingTransitions();
    }


    @Override
    public List<State> allStates() {
        return mStates;
    }


    /**
     * Sprawdza, czy podany jako argument stan jest poprawny, to znaczy,
     * czy jest różny od null, czy jest instancją klasy MyState oraz czy
     * należy do tego automatu. Jeśli pierwszy z warunków nie jest spełniony,
     * wyrzuca NullPointerException, jeśli nie jest spełniony warunek drugi
     * lub trzeci, wyrzuca IllegalArgumentException.
     */
    private MyState assertStateValid(State state) {
        if (state != null) {
            if (state instanceof MyState) {
                MyState myState = (MyState) state;
                if (myState.getOwner() == this)
                    return myState;
            }
            throw new IllegalArgumentException();
        }
        throw new NullPointerException();
    }


    /**
     * Konstruktor.
     */
    public EffectiveDeterministicAutomaton() {
        mInitialState = null;
        mStates = new Vector<State>();
    }


    @Override
    public State getInitialState() {
        return mInitialState;
    }


    @Override
    public boolean isFinal(State state) {
        MyState myState = assertStateValid(state);
        return myState.isFinal();
    }


    @Override
    public void markAsInitial(State state) {
        mInitialState = assertStateValid(state);
    }


    @Override
    public void markAsFinal(State state) {
        MyState myState = assertStateValid(state);
        myState.setFinal(true);
    }

    @Override
    public void unmarkAsFinalState(State state) {
        MyState myState = assertStateValid(state);
        myState.setFinal(false);
    }

    @Override
    public State targetState(State from, char c) {
        MyState myFrom = assertStateValid(from);
        return myFrom.getTargetState(c);
    }
}

