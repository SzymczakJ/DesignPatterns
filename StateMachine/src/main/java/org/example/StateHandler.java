package org.example;

import org.example.States.FailureState;
import org.example.States.StartingState;

public class StateHandler {
    private State currentState;

    public StateHandler() {
        currentState = new StartingState();
    }

    public void nextState(char letter) {
        currentState = currentState.nextState(letter);
    }

    public boolean reachedFailure() {
        return currentState instanceof FailureState;
    }

    public boolean isAcceptingState() {
        return currentState.isAcceptingState();
    }
}
