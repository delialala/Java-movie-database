package org.example.ApplicationStates;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChooseInterfaceState extends ApplicationState{
    @Override
    public void next(Application app) {
        app.setState(new EnterCredentialsState(app));
    }

    public ChooseInterfaceState(Application app) {
        printStatus();
        executeInput(app);
    }

    @Override
    public void prev(Application app) {
        System.out.println("Cant go any lower");
    }

    void executeInput(Application app){
        String decision = super.acceptInput();
        if(decision.equals("1"))
            next(app);
        if(decision.equals("2"))
            prev(app);
    }

    @Override
    public void printStatus() {
        System.out.println("Type 1 if you want to access the application in the command line or 2 if you want to access the application using the graphical interface");
    }
}
