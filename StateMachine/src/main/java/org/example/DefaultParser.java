package org.example;

import org.example.States.StartingState;

public class DefaultParser implements Parser {
    private StateHandler stateHandler;

    @Override
    public boolean parse(String word) {
        stateHandler = new StateHandler();

        for (char letter: word.toCharArray()) {
            stateHandler.nextState(letter);
            if (stateHandler.reachedFailure()) return false;
        }

        return stateHandler.isAcceptingState();
    }
}
