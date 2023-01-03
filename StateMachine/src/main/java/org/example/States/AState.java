package org.example.States;

import org.example.State;

public class AState implements State {
    @Override
    public State nextState(char letter) {
        return switch (letter) {
            case 'a' -> this;
            case 'b' -> new BState();
            default -> new FailureState();
        };
    }

    @Override
    public boolean isAcceptingState() {
        return true;
    }
}
