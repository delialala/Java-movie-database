package org.example.ApplicationStates;

public class Application {
    ApplicationState state = new ChooseInterfaceState(this);

    public ApplicationState getState() {
        return state;
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }
    public void previousState(){
        state.prev(this);
    }
    public void nextState(){
        state.next(this);
    }
}
