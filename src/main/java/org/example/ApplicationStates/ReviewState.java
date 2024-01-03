package org.example.ApplicationStates;

import org.example.Production;
import org.example.Rating;

public class ReviewState extends ApplicationState{
    Production production;
    public ReviewState(Application app, Production production){
        this.production = production;
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new ReviewState(app, production));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    public void executeInput(Application app){
        String decision = acceptInput();
        boolean isValidInput = false;
        if(decision.equals("0")){
            isValidInput = true;
            prev(app);
        }
        if(decision.equals("1")){
            isValidInput = true;
            // check if the user has already left a review
            for(Rating rating : production.getRatings()){
                if(rating.getUsername().equals(Application.user.getUsername())){
                    System.out.println("""
                            Sorry, you have already left a review on this production!
                            You can try deleting your review and adding another one!""");
                    System.out.println();
                    next(app);
                    return;
                }
            }
            System.out.println("Please enter your rating for this production!");
            int rating;
            try{
                rating = Integer.parseInt(acceptInput());
            }catch(Exception e){
                System.out.println("Please enter a valid number.");
                next(app);
                return;
            }
            if(rating < 1 || rating > 10)
            {
                System.out.println("Please enter a number between 1 and 10");
                next(app);
                return;
            }
            System.out.println("Please enter a comment to go with your rating!");
            String comment = acceptInput();

            Rating newRating = new Rating();
            newRating.setUsername(Application.user.getUsername());
            newRating.setRating(rating);
            newRating.setComment(comment);

            production.notifyUsers(production.getUpdate(newRating));
            production.addRating(newRating);

            System.out.println("Rating added! Going back to the main menu!");
            System.out.println();
            prev(app);
        }
        if(decision.equals("2")){
            isValidInput = true;
            Rating ratingLeft = null;
            // check if the user has left a review
            for(Rating rating : production.getRatings()){
                if (rating.getUsername().equals(Application.user.getUsername())) {
                    ratingLeft = rating;
                    break;
                }
            }
            if(ratingLeft == null){
                System.out.println("Sorry, you haven't left a review on this production yet!");
                System.out.println();
                next(app);
            }
            else{
                System.out.println("Removed your rating successfully!");
                System.out.println();
                production.removeRating(ratingLeft);
                prev(app);
            }
        }
        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, please try again!");
            next(app);
        }
    }

    @Override
    void printStatus() {
        System.out.println("We found that movie in the database! Here is the information we have on it:");
        production.displayInfo();
        System.out.println("""
                    What would you like to do?
                    Go back to the main menu (0)
                    Add a review (1)
                    Delete a review (2)""");
    }
}
