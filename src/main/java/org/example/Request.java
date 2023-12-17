package org.example;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Request implements Subject {
    @JsonProperty("type")
    private RequestType type;
    @JsonProperty("createdDate")
    String date;
    //private LocalDateTime date;
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("username")
    String usernameProblem;
    @JsonProperty("to")
    String usernameResolved;
    List<Observer<?>> users;
    @JsonSetter("actorName")
    public void setActorName(String actorName){
        title = actorName;
    }
    @JsonSetter("movieTitle")
    public void setMovieTitle(String movieTitle){
        title = movieTitle;
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
        return title + " " + description;
    }

}
