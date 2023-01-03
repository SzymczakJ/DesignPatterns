package org.example;

public interface State {
    public State nextState(char letter);
    public boolean isAcceptingState();
}
