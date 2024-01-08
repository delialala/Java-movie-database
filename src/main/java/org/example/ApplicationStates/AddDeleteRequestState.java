package org.example.ApplicationStates;

import org.example.*;

public class AddDeleteRequestState extends ApplicationState{
    public AddDeleteRequestState(Application app){
        printStatus();
        executeInput(app);
    }
    @Override
    void next(Application app) {
        app.setState(new AddDeleteRequestState(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    public void executeInput(Application app){
        String option = acceptInput();
        boolean isValidInput = false;
        if(option.equals("0")){
            prev(app);
            isValidInput = true;
        }
        if(option.equals("1")){
            if(Application.userRequests.isEmpty()){
                System.out.println("You don't have any requests to delete!");
            }
            else
            {
                System.out.println("Please type the number underneath your request to select the one you'd like to delete");
                int number;
                try{
                    number = Integer.parseInt(acceptInput());
                }
                catch(Exception e){
                    System.out.println("Please type a valid number");
                    next(app);
                    return;
                }

                if(number > Application.userRequests.size() || number <= 0){
                    System.out.println("Please type a valid number");
                    next(app);
                    return;
                }
                number--;

                Request toBeDeleted = Application.userRequests.get(number);
                IMDB.getInstance().getRequests().remove(toBeDeleted);
                app.setUserRequests();
            }
            next(app);
            isValidInput = true;
        }
        if(option.equals("2")){
            Request request = new Request();
            System.out.println("""
                    Please type the type of your request you'd like to make.
                    The available types are: DELETE_ACCOUNT, ACTOR_ISSUE, MOVIE_ISSUE, OTHERS""");

            RequestType type;
            try{
                type = RequestType.valueOf(acceptInput());
            } catch(Exception e){
                System.out.println("Sorry, we don't know what that means!");
                next(app);
                return;
            }
            System.out.println("Please type a description for your request!");
            String description = acceptInput();

            if(type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS){
                Admin.RequestsHolder.adminRequests.add(request);
                request.setUsernameResolved("ADMIN");
                // add the entire team of admins as observers
                for(User<?> user : IMDB.getInstance().getUsers()){
                    if(user instanceof Admin<?>)
                        request.addObserver(user);
                }
            }
            else {
                User<?> userResolve = null;
                if (type == RequestType.ACTOR_ISSUE) {
                    System.out.println("Type the name of the actor");
                    String name = acceptInput();
                    userResolve = IMDB.getInstance().getWhoAdded(name);
                        if (userResolve != null) {
                            request.setActorName(name);
                        } else {
                            System.out.println("Sorry, we couldn't find the actor in our database!");
                            next(app);
                            return;
                        }
                    }

                if (type == RequestType.MOVIE_ISSUE) {
                    System.out.println("Type the name of the production");
                    String name = acceptInput();
                    userResolve = IMDB.getInstance().getWhoAdded(name);
                    if (userResolve != null) {
                        request.setActorName(name);
                    } else {
                        System.out.println("Sorry, we couldn't find the production in our database!");
                        next(app);
                        return;
                    }
                }
                ((Staff<?>) userResolve).getRequestList().add(request);

                request.setUsernameResolved(userResolve.getUsername());
                request.addObserver(userResolve);

            }

            request.setType(type);
            request.setDescription(description);
            request.setUsernameProblem(Application.user.getUsername());

            IMDB.getInstance().getRequests().add(request);
            app.setUserRequests();

            request.notifyUsers("You have a new request from the user: " + Application.user.getUsername());


            isValidInput = true;
            next(app);
        }
        if(!isValidInput){
            System.out.println("Sorry! We don't know what that means, please try again!");
            next(app);
        }
    }

    @Override
    void printStatus() {
        System.out.println("==================================");
        int cnt = 1;
        if(Application.userRequests.isEmpty()){
            System.out.println("No requests here!");
        }
        else{
            for(Request request : Application.userRequests){
                request.displayInfo();
                System.out.println("(" + cnt + ")");
                cnt++;
            }
            System.out.println("These are the requests you've made.");
        }
        System.out.println("""
                
                What would you like to do?
                Go back to the main menu (0)
                Delete a request (1)
                Add a request (2)""");
    }

}
