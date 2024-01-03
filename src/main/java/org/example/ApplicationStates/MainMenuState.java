package org.example.ApplicationStates;

import org.example.*;

public class MainMenuState extends ApplicationState{
    public MainMenuState(Application app) {
        printStatus();
        executeInput(app);
    }

    @Override
    void next(Application app) {
        app.setState(new MainMenuState(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new EnterCredentialsState(app));
    }

    public void executeInput(Application app){
        String option = acceptInput();
        boolean isValidInput = false;

        if(option.equals("q")){
            System.out.println("See you next time!");
            return;
        }
        if(option.equals("0")) {
            System.out.println("Goodbye, see you next time!");
            app.prodMinNumberReviews = 0;
            app.prodMaxNumberReviews = 0;
            app.prodMinRating = 0;
            app.prodMaxRating = 0;
            app.prodGenre = null;
            app.prodOnlyMoviesOrSeries = 0;
            isValidInput = true;
            prev(app);
        }

        // here we branch out in more options
        // we can handle most of them right here instead of creating a new state
        if(option.equals("1")){
            isValidInput = true;
            app.setState(new SeeProductionsState(app));
        }
        if(option.equals("2")){
            isValidInput = true;
            app.setState(new SeeActorsState(app));
        }
        if(option.equals("3")){
            isValidInput = true;
            if(Application.user.getNotifications() != null)
                for(String notification : Application.user.getNotifications()){
                    System.out.println(notification);
                }
            else System.out.println("You have no notifications!");
            System.out.println();
            next(app);
        }
        if(option.equals("4")){
            isValidInput = true;
            System.out.println("Type the name of an actor or production you'd like to search for!");
            String name = acceptInput();
            boolean wasFound = false;
            for(Actor actor : IMDB.getInstance().getActors()){
                if(name.equals(actor.getname()))
                {
                    System.out.println("We found a match in our database!");
                    actor.displayInfo();
                    wasFound = true;
                }
            }
            for(Production production : IMDB.getInstance().getProductions()){
                if(name.equals(production.title))
                {
                    System.out.println("We found a match in our database!");
                    production.displayInfo();
                    wasFound = true;
                }
            }
            if(!wasFound)
                System.out.println("Unfortunately, we couldn't find what you searched for!");
            System.out.println();
            next(app);
        }
        if(option.equals("5")){
            isValidInput = true;
            app.setState(new FavoritesState(app));
        }
        if(option.equals("6") && Application.user instanceof Regular<?>){
            isValidInput = true;
            app.setState(new RatingState(app));
        }
        if(option.equals("7") && (Application.user instanceof Regular<?> || Application.user instanceof Contributor<?>)){
            isValidInput = true;
            app.setState(new AddDeleteRequestState(app));
        }
        if(option.equals("8") && (Application.user instanceof Contributor<?> || Application.user instanceof Admin<?>)) {
            isValidInput = true;
            app.setState(new ManageDatabase(app));

        }
        if(option.equals("9") && (Application.user instanceof Contributor<?> || Application.user instanceof Admin<?>)) {
            isValidInput = true;
            app.setState(new ViewAndResolveRequests(app));

        }
        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, or you don't have access to that feature!" + "\n" +
                    "Please try another option!");
            System.out.println();
            next(app);
        }

    }

    @Override
    void printStatus() {
        System.out.println("Username: " + Application.user.getUsername());
        if(!(Application.user instanceof Admin<?>))
            System.out.println("Experience: " + Application.user.getExperience());
        System.out.println();
        System.out.println(
                """
                        Welcome back to the main menu!
                        Please choose what you'd like to do next by typing the character in the brackets next to the option!
                        Quit the application (q)
                        Log out (0)
                        Show details about productions (1)
                        Show details about actors (2)\s
                        Show current notifications (3)
                        Search the system for a production or actor (4)\s
                        Manage your favorites list (5)"""
        );
        if(Application.user instanceof Regular<?>){
            System.out.println("Add or delete a review (6)");
        }
        if(Application.user instanceof Regular<?> || Application.user instanceof Contributor<?>){
            System.out.println("Create/delete a request (7)");
        }
        if(Application.user instanceof Contributor<?> || Application.user instanceof Admin<?>){
            System.out.println("Manage the database (8)");
        }
        if(Application.user instanceof Contributor<?> || Application.user instanceof Admin<?>){
            System.out.println("View and resolve requests (9)");
        }
    }
}
