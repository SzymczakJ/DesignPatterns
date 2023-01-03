package org.example.States;

import org.example.State;

public class CState implements State {
    @Override
    public State nextState(char letter) {
        return switch (letter) {
            case 'a' -> this;
            case 'c' -> new BState();
            default -> new FailureState();
        };
    }

    @Override
    public boolean isAcceptingState() {
        return false;
    }
}
