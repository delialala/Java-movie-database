package org.example;


import java.util.ArrayList;
import java.util.List;

public class Rating implements Subject{
    String username;
    int nota;
    String comentarii;
    List<Observer<?>> users;

    public Rating() {
        this.users = new ArrayList<>();
    }

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
        return "new rating of " + nota;
    }
}
