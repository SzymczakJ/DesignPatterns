package org.example.States;

import org.example.State;

public class StartingState implements State {

    @Override
    public State nextState(char letter) {
        return switch (letter) {
            case 'a' -> new AState();
            case 'b' -> new CState();
            default -> new FailureState();
        };
    }

    @Override
    public boolean isAcceptingState() {
        return false;
    }
}
