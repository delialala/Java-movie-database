package org.example;

import java.util.LinkedList;

public class Admin<T> extends Staff{
    public static void removeUser(User<?> user){
        for(Request request : IMDB.getInstance().getRequests())
            if(request.getUsernameProblem().equals(user.getUsername()))
                IMDB.getInstance().getRequests().remove(request);
        for(Production production : IMDB.getInstance().getProductions())
            for(Rating rating : production.getRatings())
                if(rating.getUsername().equals(user.getUsername())){
                    production.getRatings().remove(rating);
                    break;
                }
        IMDB.getInstance().getUsers().remove(user);
    }
    public static class RequestsHolder{
        public static LinkedList<Request> adminRequests = new LinkedList<>();
    }
}
