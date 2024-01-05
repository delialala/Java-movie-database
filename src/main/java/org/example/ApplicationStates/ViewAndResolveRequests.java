package org.example.ApplicationStates;

import org.example.*;

public class ViewAndResolveRequests extends ApplicationState{
    public ViewAndResolveRequests(Application app) {
        printStatus();
        executeInput(app);
    }

    @Override
    void next(Application app) {
        app.setState(new ViewAndResolveRequests(app));
    }

    @Override
    void prev(Application app) {
        app.setState(new MainMenuState(app));
    }
    // in hindsight the way I approached this part is a little silly
    public void executeInput(Application app){
        String input = acceptInput();
        boolean isValidInput = false;
        if(input.equals("0")){
            isValidInput = true;
            prev(app);
        }
        if(input.equals("1")){
            for(Request request : ((Staff<?>)Application.user).getRequestList())
                request.displayInfo();
            isValidInput = true;
            next(app);
        }
        if(input.equals("2")){
            int cnt = 0;
            for(Request request : ((Staff<?>)Application.user).getRequestList()){
                request.displayInfo();
                System.out.println("(" + cnt + ")");
                cnt++;
            }

            System.out.println("Please type the number corresponding to the request you'd like to mark as resolved");
            int index;
            try{
                index = Integer.parseInt(acceptInput());
            }catch(Exception e){
                System.out.println("Please type a valid number");
                next(app);
                return;
            }

            if(index < 0 || index >= ((Staff<?>)Application.user).getRequestList().size()){
                System.out.println("Number out of bounds.");
                next(app);
                return;
            }

            Request request = ((Staff<?>)Application.user).getRequestList().get(index);
            System.out.println("Type ACCEPTED or REJECTED depending on your choice.");
            String status = acceptInput();
            if(status.equals("ACCEPTED")){
                ((Staff<?>) Application.user).resolveRequest(request);
                System.out.println("Request marked as resolved!");
            }
            if(status.equals("REJECTED")){
                IMDB.getInstance().getRequests().remove(index);
                System.out.println("Request rejected!");
            }
            if(!status.equals("ACCEPTED") && !status.equals("REJECTED"))
                System.out.println("Not a valid choice");
            isValidInput = true;
            next(app);
        }
        if(input.equals("3") && Application.user instanceof Admin<?>){
            for(Request request : Admin.RequestsHolder.adminRequests)
                request.displayInfo();
            isValidInput = true;
            next(app);
        }
        if(input.equals("4") && Application.user instanceof Admin<?>){
            int cnt = 0;
            for(Request request : Admin.RequestsHolder.adminRequests){
                request.displayInfo();
                System.out.println("(" + cnt + ")");
                cnt++;
            }

            System.out.println("Please type the number corresponding to the request you'd like to mark as resolved");
            int index;
            try{
                index = Integer.parseInt(acceptInput());
            }catch(Exception e){
                System.out.println("Please type a valid number");
                next(app);
                return;
            }

            if(index < 0 || index >= Admin.RequestsHolder.adminRequests.size()){
                System.out.println("Number out of bounds.");
                next(app);
                return;
            }

            Request request = Admin.RequestsHolder.adminRequests.get(index);
            Admin.RequestsHolder.adminRequests.remove(request);
            System.out.println("Request removed successfully!");

            isValidInput = true;
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
                What would you like to do?
                Go back (0)
                View your unresolved requests (1)
                Mark a request as resolved (2)""");
        if(Application.user instanceof Admin<?>)
            System.out.println("""
                    View unresolved admin requests (3)
                    Mark an admin request as resolved (4)""");
    }
}
