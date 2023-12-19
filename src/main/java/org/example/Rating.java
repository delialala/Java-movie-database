package org.example;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Rating implements Subject{
    @JsonProperty("username")
    String username;
    @JsonProperty("rating")
    int rating;
    @JsonProperty("comment")
    String comment;
    List<Observer> users;

    public Rating() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer user) {
        users.add(user);
    }

    @Override
    public void removeObserver(Observer user) {
        users.remove(user);
    }
    @Override
    public void notifyUsers() {
        for(Observer user : users){
            user.update(getUpdate());
        }
    }
    @Override
    public String getUpdate() {
        return "new rating of " + rating;
    }
}
