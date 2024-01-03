package org.example;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Request implements Subject {
    @JsonProperty("type")
    private RequestType type;
    @JsonProperty("createdDate")
    Date date = new Date();
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("username")
    String usernameProblem;
    @JsonProperty("to")
    String usernameResolved;
    List<Observer> users = new ArrayList<>();

    public void setType(RequestType type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsernameProblem(String usernameProblem) {
        this.usernameProblem = usernameProblem;
    }

    public void setUsernameResolved(String usernameResolved) {
        this.usernameResolved = usernameResolved;
    }

    public void setUsers(List<Observer> users) {
        this.users = users;
    }

    public String getUsernameProblem() {
        return usernameProblem;
    }
    public void displayInfo(){
        System.out.println("Request type: " + type + "\n"
        + "Date created: " + date + "\n"
        + "Title: " + title + "\n"
        + "Description: " + description + "\n"
        + "Username of the person the request is dedicated to: " + usernameResolved);
    }
    public String getUsernameResolved() {
        return usernameResolved;
    }

    @JsonSetter("actorName")
    public void setActorName(String actorName){
        title = actorName;
    }
    @JsonSetter("movieTitle")
    public void setMovieTitle(String movieTitle){
        title = movieTitle;
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
    public void notifyUsers(String notification) {
        for(Observer user : users){
            user.update(notification);
        }
    }

    public RequestType getType() {
        return type;
    }

}
