package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Request implements Subject {
    private RequestType requestType;
    private LocalDateTime date;
    String name;
    String description;
    String usernameProblem;
    String usernameResolved;
    List<Observer<?>> users;
    @Override
    public void addObserver(Observer<?> user) {
            users.add(user);
    }

    @Override
    public void removeObserver(Observer<?> user) {
        users.remove(user);
    }

    @Override
    public void notifyUsers() {
        for(Observer<?> user : users){
            user.update(getUpdate());
        }
    }

    @Override
    public String getUpdate() {
        return name + " " + description;
    }

}
