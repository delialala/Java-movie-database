package org.example.ApplicationStates;
import org.example.*;

import java.util.Collection;

public class EnterCredentialsState extends ApplicationState {
    public EnterCredentialsState(Application app) {
        printStatus();
        executeInput(app);
    }

    @Override
    public void next(Application app) {
        app.setState(new MainMenuState(app));
    }

    @Override
    public void prev(Application app) {
        app.setState(new EnterCredentialsState(app));
    }

    public void executeInput(Application app){
        System.out.println("Email:");
        String email = acceptInput();
        if(email.equals("q")){
            System.out.println("See you next time!");
            return;
        }
        System.out.println("Password:");
        String password = acceptInput();
        if(password.equals("q")){
            System.out.println("See you next time!");
            return;
        }
        Collection<User<?>> users = IMDB.getInstance().getUsers();
        boolean userExists = false;
        for(User<?> user : users){
            if(email.equals(user.getInformation().getCredentials().getEmail())
                    && password.equals(user.getInformation().getCredentials().getPassword()))
            {
                userExists = true;
                Application.user = user;
                app.setUserRequests();
            }
        }
        if(userExists){
            next(app);
        }

        else{
            System.out.println("Your credentials are wrong, please try again.");
            prev(app);
        }
    }
    @Override
    public void printStatus() {
        System.out.println("Please enter your credentials, or type q to exit the application!");
    }
}
