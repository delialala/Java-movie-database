package org.example.ApplicationStates;

import org.example.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ManageDatabase extends ApplicationState{
    public ManageDatabase(Application app){
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new ManageDatabase(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    public void executeInput(Application app){
        String input = acceptInput();
        boolean isValidInput = false;
        if(input.equals("0")){
            prev(app);
            isValidInput = true;
        }
        if(input.equals("1")){
            System.out.println("Please type ACTOR or PRODUCTION depending on what you'd like to add.");
            String option = acceptInput();
            if(option.equals("ACTOR")){
                Actor actor = new Actor();

                System.out.println("Please enter the actor's name");
                actor.setname(acceptInput());

                System.out.println("Please enter the actor's biography");
                actor.setbiography(acceptInput());

                System.out.println("Please enter the actor's performances. Type 0 in the title field to stop adding.");
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
                ((Staff<?>)Application.user).addActorSystem(actor);
            }
            if(option.equals("PRODUCTION")){
                System.out.println("Please type MOVIE or SERIES depending on what you'd like to add.");
                String choice = acceptInput();
                Production production;
                if(choice.equals("MOVIE")){
                    production = new Movie();
                    enterProduction(production);
                    System.out.println("Please enter the duration");
                    ((Movie) production).setDuration(acceptInput());
                    System.out.println("Please enter the release year");
                    String releaseYear = acceptInput();
                    try{
                        ((Movie) production).setReleaseYear(Integer.parseInt(releaseYear));
                    }catch (Exception e){
                        System.out.println("Not a valid year.");
                        next(app);
                        return;
                    }

                    IMDB.getInstance().getProductions().add(production);
                    production.addObserver(Application.user);
                    System.out.println("Hooray, added the movie to the database!");
                }
                if(choice.equals("SERIES")){
                    production = new Series();
                    enterProduction(production);
                    System.out.println("Please enter the release year");
                    String releaseYear = acceptInput();
                    try{
                        ((Series) production).setReleaseYear(Integer.parseInt(releaseYear));
                    }catch (Exception e){
                        System.out.println("Not a valid year.");
                        next(app);
                        return;
                    }
                    System.out.println("Please enter the number of seasons");
                    String numSeasons = acceptInput();
                    try{
                        ((Series) production).setNumSeasons(Integer.parseInt(numSeasons));
                    }catch (Exception e){
                        System.out.println("Not a valid number.");
                        next(app);
                        return;
                    }
                    ((Series) production).setEpisodeDistribution(new HashMap<>());
                    System.out.println("Enter a season name, type 0 to stop entering:");
                    String seasonName = acceptInput();
                    while(!seasonName.equals("0")){
                        System.out.println("Enter an episode name, type 0 in the episode name field to stop entering:");
                        System.out.println("Episode name:");
                        ArrayList<Episode> episodes = new ArrayList<>();
                        String epName = acceptInput();
                        while(!epName.equals("0")){
                            System.out.println("Duration:");
                            Episode episode = new Episode(epName, acceptInput());
                            episodes.add(episode);
                            System.out.println("Episode name: ");
                            epName = acceptInput();
                        }
                        System.out.println("Enter a season name, type 0 to stop entering:");
                        ((Series) production).addSeason(seasonName, episodes);
                        seasonName = acceptInput();
                    }
                    IMDB.getInstance().getProductions().add(production);
                    production.addObserver(Application.user);
                    System.out.println("Hooray, added the series to the database!");
                }
                if(!choice.equals("MOVIE") && !choice.equals("SERIES")){
                    System.out.println("Sorry, we don't know what that means!");
                    next(app);
                    return;
                }
            }
            if(!option.equals("ACTOR") && !option.equals("PRODUCTION")){
                System.out.println("Sorry, we don't know what that means!");
                next(app);
                return;
            }

            isValidInput = true;
            next(app);

        }
        if(input.equals("2")){
            System.out.println("Type the name of the actor or production you'd like to delete");
            String name = acceptInput();
            if(IMDB.getInstance().actorExists(name) || IMDB.getInstance().productionExists(name)){
                if(IMDB.getInstance().actorExists(name)){
                    if(((Staff<?>)Application. user).actorExistsInContributions(name))
                        ((Staff<?>) Application.user).removeActorSystem(name);
                    else{
                        System.out.println("Actor exists in our database, but you don't have access to it.");
                        next(app);
                        return;
                    }
                }

                if(IMDB.getInstance().productionExists(name)){
                    if(((Staff<?>)Application.user).productionExistsInContributions(name))
                        ((Staff<?>) Application.user).removeProductionSystem(name);
                    else{
                        System.out.println("Production exists in our database, but you don't have access to it.");
                        next(app);
                        return;
                    }
                }
                System.out.println("Entry deleted successfully!");
            }
            else{
                System.out.println("Actor or production doesn't exist in our database.");
                next(app);
                return;
            }
            isValidInput = true;
            next(app);
        }
        if(input.equals("3")){
            ((Staff<?>)Application.user).showActorsContributions();
            ((Staff<?>)Application.user).showProductionContributions();
            System.out.println();
            isValidInput = true;
            next(app);
        }
        if(input.equals("4")){
            System.out.println("Type the name of the production or actor you'd like to modify");
            String name = acceptInput();
            if(IMDB.getInstance().actorExists(name) || IMDB.getInstance().productionExists(name)){
                if(IMDB.getInstance().actorExists(name)){
                    if(((Staff<?>)Application. user).actorExistsInContributions(name)){
                        app.setState(new ModifyActor(app, IMDB.getInstance().getActor(name)));
                        return;
                    }
                    else{
                        System.out.println("Actor exists in our database, but you don't have access to it.");
                        next(app);
                    }
                    return;
                }
                if(IMDB.getInstance().productionExists(name)){
                    if(((Staff<?>)Application.user).productionExistsInContributions(name)){
                        app.setState(new ModifyProduction(app, IMDB.getInstance().getProduction(name)));
                    }
                    else{
                        System.out.println("Production exists in our database, but you don't have access to it.");
                        next(app);
                        return;
                    }
                }
            }
            else{
                System.out.println("Actor or production doesn't exist in our database.");
                next(app);
                return;
            }
            System.out.println();
            isValidInput = true;
            next(app);
        }
        if(input.equals("5") && Application.user instanceof Admin<?>){
            User<?> user;
            System.out.println("Enter the type of user you'd like to create (Admin, Contributor, Regular)");
            AccountType choice;
            try{
                choice = AccountType.valueOf(acceptInput());
            }catch (Exception e){
                System.out.println("Not a valid account type!");
                next(app);
                return;
            }
            user = UserFactory.factory(choice);

            System.out.println("Enter the mail");
            String mail = null;
            boolean isValid;
            isValid = false;
            while(!isValid){
                boolean matches = false;
                mail = acceptInput();
                for(User<?> user1 : IMDB.getInstance().getUsers())
                    if (user1.getInformation().getCredentials().getEmail().equals(mail)) {
                        matches = true;
                        break;
                    }
                if(!matches)
                    isValid = true;
                if(!isValid)
                    System.out.println("Mail already taken, pleasy try again!");
            }
            System.out.println("Enter the password");
            Credentials credentials = new Credentials(mail, acceptInput());

            System.out.println("Enter the name");
            String name = acceptInput();

            System.out.println("Enter the country");
            String country = acceptInput();

            System.out.println("Enter the age");
            int age = 0;
            isValid = false;
            while(!isValid)
                try{
                    age = Integer.parseInt(acceptInput());
                    isValid = true;
                }catch(Exception e){
                    System.out.println("Please type a valid number!");
                }
            System.out.println("Please enter the date in the format yyyy-MM-dd");
            Date birthDate = null;
            isValid = false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            while(!isValid)
                try{
                    birthDate = Date.from((LocalDate.parse(acceptInput(), formatter).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    isValid = true;
                }catch(Exception e){
                    System.out.println("Please type a valid date!");
                }
            System.out.println("Please type the gender!");
            String gender = acceptInput();

            try{
                if(credentials.getPassword().isEmpty() || credentials.getEmail().isEmpty() || name.isEmpty())
                    throw new NoCredentials();
            } catch(NoCredentials e){
                System.out.println("Can't create a user with no credentials!");
                next(app);
                return;
            }
            User.Information information = new User.Information.InformationBuilder()
                    .credentials(credentials)
                    .name(name)
                    .country(country)
                    .age(age)
                    .birthDate(birthDate)
                    .gender(gender)
                    .build();
            System.out.println("Enter the username");
            isValid = false;
            String username = null;
            while(!isValid){
                boolean matches = false;
                username = acceptInput();
                for(User<?> user1 : IMDB.getInstance().getUsers())
                    if (user1.getUsername().equals(username)) {
                        matches = true;
                        break;
                    }
                if(!matches)
                    isValid = true;
                else
                    System.out.println("Username already taken, pleasy try again!");
            }
            assert user != null;
            user.setUsername(username);

            user.setUserType(choice);
            user.setInformation(information);

            IMDB.getInstance().getUsers().add(user);
            System.out.println("You have added the user: ");
            user.displayInfo();
            next(app);
            isValidInput = true;
        }
        if(input.equals("6") && Application.user instanceof Admin<?>){
            System.out.println("Type the username of the user you'd like to delete");
            User<?> user = IMDB.getInstance().getUser(acceptInput());
            if(user == null){
                System.out.println("Sorry, that user doesn't exist!");
                next(app);
                return;
            }
            IMDB.getInstance().getUsers().remove(user);
            System.out.println("User removed successfully!");
            isValidInput = true;
            next(app);
        }
        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, or you don't have access to that feature!" + "\n" +
                    "Please try another option!");
            System.out.println();
            next(app);
        }

    }
    public void enterProduction(Production production){
        System.out.println("Please type the name.");
        production.setTitle(acceptInput());
        System.out.println("Please enter the directors. Type 0 to stop entering.");
        String director = acceptInput();
        while(!director.equals("0")){
            production.addDirector(director);
            director = acceptInput();
        }
        System.out.println("Please enter the actors. Type 0 to stop entering.");
        String actor = acceptInput();
        while(!actor.equals("0")){
            production.addActor(actor);
            actor = acceptInput();
        }
        System.out.println("Please enter the genres. Type 0 to stop entering.");
        String genre = acceptInput();
        while(!genre.equals("0")){
            try{
                production.addGenre(Genre.valueOf(genre));
            }catch(Exception e){
                System.out.println("Not a valid genre.");
            }
            genre = acceptInput();
        }
        System.out.println("Please enter the plot");
        production.setPlot(acceptInput());
    }
    @Override
    void printStatus() {
        System.out.println("""
                What would you like to do?
                Go back to the main menu(0)
                Add a production/actor (1)
                Delete a production/actor (2)
                Show your productions and actors contributions(3)
                Manage a production/actor (4)
                """);
        if(Application.user instanceof Admin<?>)
            System.out.println("""
                    Add a user (5)
                    Delete a user (6)""");
    }
}
class NoCredentials extends Exception{
    public NoCredentials(){
    }
}
