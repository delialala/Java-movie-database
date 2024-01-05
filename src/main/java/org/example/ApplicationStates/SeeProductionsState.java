package org.example.ApplicationStates;

import org.example.Genre;
import org.example.IMDB;
import org.example.Production;

import java.util.List;

public class SeeProductionsState extends ApplicationState{
    // had to make app a variable in this class because I need it in printStatus() too
    // and I don't want to rewrite the other classes to fit it
    // stupid solution but I can't think of anything else
    Application app;
    public SeeProductionsState(Application app){
        this.app = app;
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new SeeProductionsState(app));
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
            IMDB.getInstance().showProductions(IMDB.getInstance().getProductions());
            next(app);
        }
        if(option.equals("2"))
        {
            isValidInput = true;
            showFiltered();
            next(app);
        }
        if(option.equals("3")){
            System.out.println("Please choose the genre you'd like to search for by typing its name");
            System.out.println("Here are all the available genres:");
            System.out.println("Action, Adventure, Comedy, Drama, Horror, SF,\n" +
                    "Fantasy, Romance, Mystery, Thriller, Crime, Biography, War, Cooking");
            try{
                app.prodGenre = Genre.valueOf(acceptInput());
            }
            catch(Exception e){
                System.out.println("Sorry! That genre doesn't exist in our database!");
            }
            isValidInput = true;
            next(app);
        }
        if(option.equals("4")){
            System.out.println("Please type the minimum number of reviews you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.prodMinNumberReviews = 0;
            else
                try{
                    app.prodMinNumberReviews = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }
        if(option.equals("5")){
            System.out.println("Please type the maximum number of reviews you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.prodMaxNumberReviews = 0;
            else
                try{
                    app.prodMaxNumberReviews = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }
        if(option.equals("6")){
            System.out.println("Please type the minimum average rating you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.prodMinRating = 0;
            else
                try{
                    app.prodMinRating = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }
        if(option.equals("7")){
            System.out.println("Please type the maximum average rating you'd like the search results to have, or \"-\" to turn off the filter");
            String input = acceptInput();
            if(input.equals("-"))
                app.prodMaxRating = 0;
            else
                try{
                    app.prodMaxRating = Integer.parseInt(input);
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                }
            isValidInput = true;
            next(app);
        }
        if(option.equals("8")){
            System.out.println("Please type \"series\", \"movies\" or \"any\" to set your preference");
            String input = acceptInput();
            if(input.equals("series")){
                isValidInput = true;
                app.prodOnlyMoviesOrSeries = 2;
                next(app);
            }
            if(input.equals("movies")){
                isValidInput = true;
                app.prodOnlyMoviesOrSeries = 1;
                next(app);
            }
            if(input.equals("any")){
                isValidInput = true;
                app.prodOnlyMoviesOrSeries = 0;
                next(app);
            }
        }
        if(option.equals("9")){
            System.out.println("Yahoo, all your filters have been reset!");
            app.prodMinNumberReviews = 0;
            app.prodMaxNumberReviews = 0;
            app.prodMinRating = 0;
            app.prodMaxRating = 0;
            app.prodGenre = null;
            app.prodOnlyMoviesOrSeries = 0;
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
        // I want to kiss the forehead of whoever invented streams
        List<Production> filteredList = IMDB.getInstance().getProductions();
        if(app.prodGenre != null){
            filteredList = filteredList.
                    stream().filter(p -> p.isGenreIncluded(app.prodGenre)).toList();
        }
        if(app.prodOnlyMoviesOrSeries != 0){
            if(app.prodOnlyMoviesOrSeries == 1)
                filteredList = filteredList.
                        stream().filter(p -> p.isMovieOrSeries()).toList();
            else
                filteredList = filteredList.
                        stream().filter(p -> !(p.isMovieOrSeries())).toList();
        }
        if(app.prodMinNumberReviews != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getRatings().size() > app.prodMinNumberReviews).toList();
        }
        if(app.prodMaxNumberReviews != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getRatings().size() < app.prodMaxNumberReviews).toList();
        }
        if(app.prodMinRating != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getAverageRating() > app.prodMinRating).toList();
        }
        if(app.prodMaxRating != 0){
            filteredList = filteredList.
                    stream().filter(p -> p.getAverageRating() < app.prodMaxRating).toList();
        }
        IMDB.getInstance().showProductions(filteredList);
    }
    @Override
    void printStatus() {
        System.out.println(
                """
                        Welcome to the productions menu!
                        
                        What would you like to do?
                        Quit the application (q)
                        Go back to the main menu (0)
                        See all productions unfiltered (1)
                        See all productions with the applied filters (2)
                        
                        Here are your filters, type the character in the brackets to change them:"""
        );
        if(app.prodGenre == null)
            System.out.println("Genre filter: Not set (3)");
        else
            System.out.println("Genre filter: " + app.prodGenre + " (3)");

        if(app.prodMinNumberReviews == 0)
            System.out.println("Minimum number of reviews: Not set (4)");
        else
            System.out.println("Minimum number of reviews: " + app.prodMinNumberReviews +  " (4)");

        if(app.prodMaxNumberReviews == 0)
            System.out.println("Maximum number of reviews: Not set (5)");
        else
            System.out.println("Maximum number of reviews: " + app.prodMaxNumberReviews +  " (5)");

        if(app.prodMinRating == 0)
            System.out.println("Minimum average rating: Not set (6)");
        else
            System.out.println("Minimum average rating: " + app.prodMinRating +  " (6)");

        if(app.prodMaxRating == 0)
            System.out.println("Maximum average rating: Not set (7)");
        else
            System.out.println("Maximum average rating: " + app.prodMaxRating +  " (7)");

        if(app.prodOnlyMoviesOrSeries == 0)
            System.out.println("Only showing movies or series: Not set (8)");
        else
            if(app.prodOnlyMoviesOrSeries == 1)
                System.out.println("Only showing movies or series: Showing movies (8)");
            else
                System.out.println("Only showing movies or series: Showing series (8)");

        System.out.println("Reset all filters (9)");

    }
}
