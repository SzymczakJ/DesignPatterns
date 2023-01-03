package org.example.States;

import org.example.State;

public class BState implements State {

    @Override
    public State nextState(char letter) {
        return switch (letter) {
            case 'a' -> new BAState();
            case 'b' -> this;
            default -> new FailureState();
        };
    }

    @Override
    public boolean isAcceptingState() {
        return true;
    }
}
