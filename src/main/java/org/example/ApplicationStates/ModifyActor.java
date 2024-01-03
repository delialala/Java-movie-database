package org.example.ApplicationStates;

import org.example.Actor;

import java.util.ArrayList;

public class ModifyActor extends ApplicationState{
    Actor actor;
    public ModifyActor(Application app, Actor actor){
        this.actor = actor;
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new ModifyActor(app, actor));
    }

    @Override
    void prev(Application app) {
        app.setState(new ManageDatabase(app));
    }
    public void executeInput(Application app){
        String input = acceptInput();
        boolean isValidInput = false;
        if(input.equals("0")){
            isValidInput = true;
            prev(app);
        }
        if(input.equals("1")){
            System.out.println("Please enter the new name.");
            actor.setname(acceptInput());

            isValidInput = true;
            next(app);
        }
        if(input.equals("2")){
            System.out.println("Please enter the new biography.");
            actor.setbiography(acceptInput());

            isValidInput = true;
            next(app);
        }
        if(input.equals("3")){
            System.out.println("Please enter the new performances. Press 0 to stop adding.");

            actor.setPerformances(new ArrayList<>());

            // copy-paste, sorry, should've made a function somewhere instead
            System.out.println("Title:");
            String title = acceptInput();
            while(!title.equals("0")){
                System.out.println("Type (Movie, Sitcom, Series):");
                boolean isOk = true;
                Actor.Type type = null;
                try{
                    type = Actor.Type.valueOf(acceptInput());
                } catch(Exception e){
                    System.out.println("Sorry, we don't know what this type is!");
                    isOk = false;
                }
                if(isOk){
                    actor.addPerformance(title, type);
                }
                System.out.println("Title: ");
                title = acceptInput();
            }

            isValidInput = true;
            next(app);
        }

        if(!isValidInput){
            System.out.println("Sorry, we don't know what that means!");
            next(app);
        }
    }
    @Override
    void printStatus() {
        System.out.println("You chose to modify the actor: " + actor.getname());
        System.out.println("""
                            Go back (0)
                            What would you like to change?
                            Name (1)
                            Biography (2)
                            Performances (3)
                            """);
    }
}
