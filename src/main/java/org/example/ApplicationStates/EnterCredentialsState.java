package org.example.ApplicationStates;
import org.example.*;
public class EnterCredentialsState extends ApplicationState {
    public EnterCredentialsState(Application app) {
        printStatus();
        executeInput(app);
    }

    @Override
    public void next(Application app) {

    }

    @Override
    public void prev(Application app) {

    }

    public void executeInput(Application app){
        System.out.println("Username:");
        String name = super.acceptInput();
        System.out.println("Password:");
        String password = super.acceptInput();

    }

    @Override
    public void printStatus() {
        System.out.println("Please enter your credentials!");
    }
}
