package org.example.ApplicationStates;

import org.example.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ModifyProduction extends ApplicationState{
    Production production;
    public ModifyProduction(Application app, Production production){
        this.production = production;
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new ModifyProduction(app, production));
    }

    @Override
    void prev(Application app) {
        app.setState(new ManageDatabase(app));
    }
    public void executeInput(Application app){
        String input = super.acceptInput();
        boolean isValidInput = false;
        if(input.equals("0")){
            isValidInput = true;
            prev(app);
        }
        if(input.equals("1")){
            System.out.println("Please type the name.");
            production.setTitle(super.acceptInput());
            isValidInput = true;
            next(app);
        }
        if(input.equals("2")){
            production.setDirectors(new ArrayList<>());
            System.out.println("Please enter the directors. Type 0 to stop entering.");
            String director = super.acceptInput();
            while(!director.equals("0")){
                production.addDirector(director);
                director = super.acceptInput();
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("3")){
            production.setActors(new ArrayList<>());
            System.out.println("Please enter the actors. Type 0 to stop entering.");
            String actor = super.acceptInput();
            while(!actor.equals("0")){
                production.addActor(actor);
                actor = super.acceptInput();
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("4")){
            production.setGenres(new ArrayList<>());
            System.out.println("Please enter the genres. Type 0 to stop entering.");
            String genre = super.acceptInput();
            while(!genre.equals("0")){
                try{
                    production.addGenre(Genre.valueOf(genre));
                }catch(Exception e){
                    System.out.println("Not a valid genre.");
                }
                genre = super.acceptInput();
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("5")){
            System.out.println("Please enter the plot");
            production.setPlot(super.acceptInput());
            isValidInput = true;
            next(app);
        }
        if(input.equals("6") && production instanceof Movie){
            System.out.println("Please enter the duration");
            ((Movie) production).setDuration(super.acceptInput());
            isValidInput = true;
            next(app);
        }
        if(input.equals("6") && production instanceof Series){
            System.out.println("Please enter the release year");
            String releaseYear = super.acceptInput();
            try{
                ((Series) production).setReleaseYear(Integer.parseInt(releaseYear));
            }catch (Exception e){
                System.out.println("Not a valid year.");
                next(app);
                return;
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("7") && production instanceof Movie){
            System.out.println("Please enter the release year");
            String releaseYear = super.acceptInput();
            try{
                ((Movie) production).setReleaseYear(Integer.parseInt(releaseYear));
            }catch (Exception e){
                System.out.println("Not a valid year.");
                next(app);
                return;
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("7") && production instanceof Series){
            System.out.println("Please enter the number of seasons");
            String numSeasons = super.acceptInput();
            try{
                ((Series) production).setNumSeasons(Integer.parseInt(numSeasons));
            }catch (Exception e){
                System.out.println("Not a valid number.");
                next(app);
                return;
            }
            ((Series) production).setEpisodeDistribution(new HashMap<>());
            System.out.println("Enter a season name, type 0 to stop entering:");
            String seasonName = super.acceptInput();
            while(!seasonName.equals("0")){
                System.out.println("Enter an episode name, type 0 in the episode name field to stop entering:");
                System.out.println("Episode name:");
                ArrayList<Episode> episodes = new ArrayList<>();
                String epName = super.acceptInput();
                while(!epName.equals("0")){
                    System.out.println("Duration:");
                    Episode episode = new Episode(epName, super.acceptInput());
                    episodes.add(episode);
                    System.out.println("Episode name: ");
                    epName = super.acceptInput();
                }
                ((Series) production).addSeason(seasonName, episodes);
                System.out.println("Enter a season name, type 0 to stop entering:");
                seasonName = super.acceptInput();
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
        System.out.println("You chose to modify the production: " + production.title);
        System.out.println("""
                Go back (0)
                What would you like to change?
                Name (1)
                Directors (2)
                Actors (3)
                Genres (4)
                Plot (5)""");
        if (production instanceof Movie) {
            System.out.println("""
                    Duration (6)
                    Release year (7)""");
        }
        if (production instanceof Series) {
            System.out.println("""
                    Release year (6)
                    Episode distribution (7)""");
        }
    }

}

