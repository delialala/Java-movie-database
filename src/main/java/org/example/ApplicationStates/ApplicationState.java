package org.example.ApplicationStates;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class ApplicationState {
    abstract void next(Application app);
    abstract void prev(Application app);
    String acceptInput(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String decision = "";
        try{
            decision = reader.readLine();
        } catch(Exception e){
            e.printStackTrace();
        }
        return decision;
    }
    abstract void printStatus();
}
