package org.example.States;

import org.example.State;

public class FailureState implements State {

    @Override
    public State nextState(char letter) {
        return this;
    }

    @Override
    public boolean isAcceptingState() {
        return false;
    }
}
