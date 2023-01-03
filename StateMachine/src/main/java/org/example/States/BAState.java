package org.example.States;

import org.example.State;

public class BAState implements State {
    @Override
    public State nextState(char letter) {
        return new FailureState();
    }

    @Override
    public boolean isAcceptingState() {
        return true;
    }
}
