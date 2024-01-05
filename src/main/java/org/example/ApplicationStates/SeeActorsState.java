package org.example.ApplicationStates;

import org.example.Actor;
import org.example.IMDB;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SeeActorsState extends ApplicationState{
    Application app;
    public SeeActorsState(Application app){
        this.app = app;
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new SeeActorsState(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }

    void executeInput(Application app){
        String option = acceptInput();
        boolean isValidInput = false;

        if(option.equals("q")){
            System.out.println("See you next time!");
            return;
        }
        if(option.equals("0")) {
            isValidInput = true;
            prev(app);
        }
        if(option.equals("1")){
            isValidInput = true;
            // show all productions
            IMDB.getInstance().showActors(IMDB.getInstance().getActors());
            next(app);
        }
        if(option.equals("2"))
        {
            isValidInput = true;
            showFiltered();
            next(app);
        }
        if(option.equals("3")){
            if(!app.actorSortAlphabetically)
            {
                System.out.println("Actors will now be sorted alphabetically");
                System.out.println();
                app.actorSortAlphabetically = true;
            }
            else {
                System.out.println("Actors will not be sorted alphabetically");
                System.out.println();
                app.actorSortAlphabetically = false;
            }
            isValidInput = true;
            next(app);
        }
        if(option.equals("4")){
            System.out.println("Please type the minimum number of performances you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.actorMinNumberPerformances = 0;
            else
                try{
                    app.actorMinNumberPerformances = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }
        if(option.equals("5")){
            System.out.println("Please type the maximum number of performances you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.actorMaxNumberPerformances = 0;
            else
                try{
                    app.actorMaxNumberPerformances = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }


        if(option.equals("6")){
            System.out.println("Yahoo, all your filters have been reset!");
            app.actorMaxNumberPerformances = 0;
            app.actorMinNumberPerformances = 0;
            app.actorSortAlphabetically = false;
            next(app);
            isValidInput = true;
        }
        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, or you don't have access to that feature!" + "\n" +
                    "Please try another option!");
            System.out.println();
            next(app);
        }

    }

    void showFiltered(){
        List<Actor> filteredList = IMDB.getInstance().getActors();
        if(app.actorMinNumberPerformances != 0){
            filteredList = filteredList.
                    stream().filter(a -> a.getPerformances().size() > app.actorMinNumberPerformances).toList();
        }
        if(app.actorMaxNumberPerformances != 0){
            filteredList = filteredList.
                    stream().filter(a -> a.getPerformances().size()  < app.actorMaxNumberPerformances).toList();
        }
        TreeSet<Actor> temp;
        if(app.actorSortAlphabetically){
            temp = new TreeSet<>(filteredList);
            filteredList = new ArrayList<>(temp);
        }
        IMDB.getInstance().showActors(filteredList);
    }
    @Override
    void printStatus() {
        System.out.println(
                """
                        Welcome to the actors menu!
                        
                        What would you like to do?
                        Quit the application (q)
                        Go back to the main menu (0)
                        See all actors unfiltered (1)
                        See all actors with the applied filters (2)
                        
                        Here are your filters, type the character in the brackets to change them:"""
        );
        if(!app.actorSortAlphabetically)
            System.out.println("Sort actors alphabetically: Not set (3)");
        else
            System.out.println("Sort actors alphabetically: On (3)");

        if(app.actorMinNumberPerformances == 0)
            System.out.println("Minimum number of perfomances: Not set (4)");
        else
            System.out.println("Minimum number of performances: " + app.actorMinNumberPerformances + "(4)");
        if(app.actorMaxNumberPerformances == 0)
            System.out.println("Maximum number of perfomances: Not set (5)");
        else
            System.out.println("Maximum number of performances: " + app.actorMaxNumberPerformances + "(5)");

        System.out.println("Reset all filters (6)");

    }
}
