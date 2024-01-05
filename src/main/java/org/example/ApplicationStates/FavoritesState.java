package org.example.ApplicationStates;

public class FavoritesState extends ApplicationState{
    public FavoritesState(Application app){
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new FavoritesState(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    public void executeInput(Application app) {
        String option = acceptInput();
        if(option.equals("q")){
            System.out.println("See you next time!");
            return;
        }
        boolean isValidInput = false;
        if(option.equals("0")){
            isValidInput = true;
            prev(app);
        }
        if(option.equals("1")){
            isValidInput = true;
            if(Application.user.getFavorites().isEmpty()){
                System.out.println("Nobody here but us chickens! Your favorites list is empty, try adding an entry!");
                System.out.println();
            }
            else{
                Application.user.showFavorites();
                System.out.println();
            }
            next(app);
        }
        if(option.equals("2")){
            if(Application.user.getFavorites().isEmpty()){
                System.out.println("Can't go any lower, your favorites list is already empty!");
                System.out.println();
            }
            else{
                isValidInput = true;

                System.out.println("Type the name of the actor or production you'd like to remove from your favorites list!");
                String nameToBeRemoved = acceptInput();

                // see if the name exists in favorites list first
                if(!Application.user.existsInFavorites(nameToBeRemoved)){
                    System.out.println("You don't have the entry in your favorites list or the entry doesn't exist in our database!");
                    System.out.println();
                    next(app);
                    return;
                }
                // if it exists, try to remove the entry
                if(!Application.user.removeFavorite(nameToBeRemoved)){
                    System.out.println("You don't have the entry in your favorites list or the entry doesn't exist in our database!");
                    System.out.println();
                }
                else{
                    System.out.println("Successfully removed the entry from your favorites list! Goodbye, " + nameToBeRemoved + "!");
                    System.out.println("Your favorites list now looks like this:");
                    Application.user.showFavorites();
                    System.out.println();
                }
            }
            next(app);
        }
        if(option.equals("3")){
            isValidInput = true;

            System.out.println("Type the name of the actor or production you'd like to add to your favorites list!");
            String nameToBeAdded = acceptInput();
            boolean wasFound = Application.user.addFavorite(nameToBeAdded);
            if(wasFound){
                System.out.println("Successfully added the entry in your favorites list! Welcome to the club, " + nameToBeAdded + "!");
                System.out.println("Your favorites list now looks like this:");
                Application.user.showFavorites();
                System.out.println();
            }
            else
                System.out.println("Sorry, we couldn't find that entry in our database! You can try making a request to have it added!");
            next(app);
        }

        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, or you don't have access to that feature! Please try another option!");
            next(app);
        }
    }
    @Override
    void printStatus() {
        System.out.println("""
                Welcome to the favorites list manager!
                What would you like to do?
                Quit the application (q)
                Go back to main menu (0)
                Show your favorites list (1)
                Delete an entry from your favorites list (2)
                Add an entry to your favorites list (3)""");
    }
}
