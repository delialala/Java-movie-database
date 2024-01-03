package org.example.ApplicationStates;

import org.example.IMDB;
import org.example.Production;

public class RatingState extends ApplicationState{
    public RatingState(Application app){
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new RatingState(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    public void executeInput(Application app){
        String productionName = acceptInput();
        Production production = null;
        boolean isValidInput = false;

        if(productionName.equals("0"))
        {
            isValidInput = true;
            prev(app);
        }
        for(Production prod : IMDB.getInstance().getProductions()){
            if(prod.title.equals(productionName))
            {
                production = prod;
                isValidInput = true;
            }
        }
        if(!isValidInput){
            System.out.println("Sorry, we couldn't find that production in our database! Please try again!");
            next(app);
        }
        else{
            app.setState(new ReviewState(app, production));
        }
    }

    @Override
    void printStatus() {
        System.out.println("Please enter the name of the production you'd like to add/delete a review for or type 0 to go back to the main menu:");
    }
}
