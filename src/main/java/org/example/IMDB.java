package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
public class IMDB{
    private List<User<?>> users;
    private List <Actor> actors;
    private List <Request> requests;
    private List <Production> productions;
    private static IMDB imdb = null;

    public Collection<User<?>> getUsers() {
        return users;
    }


    public Collection<Request> getRequests() {
        return requests;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setUsers(List<User<?>> users) {
        this.users = users;
    }


    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    private IMDB(){
        users = new ArrayList<>();
        requests = new ArrayList<>();
        productions = new ArrayList<>();
        actors = new ArrayList<>();
    }

    public static IMDB getInstance(){
        if(imdb == null)
            imdb = new IMDB();
        return imdb;
    }


    public void readJson(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Actor[] actori = mapper.readValue(Paths.get("src\\main\\resources\\input\\actors.json").toFile(), Actor[].class);
            actors = Arrays.asList(actori);

            Production[] productionsArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\production.json").toFile(), Production[].class);
            productions = Arrays.asList(productionsArray);

            Request[] requestsArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\requests.json").toFile(), Request[].class);
            requests = Arrays.asList(requestsArray);

            User<?>[] userArray = mapper.readValue(Paths.get("src\\main\\resources\\input\\accounts.json").toFile(), User[].class);
            users = Arrays.asList(userArray);

        } catch (Exception ex) {
            System.out.println("oopsie, eroare la citire json");
        }
    }

    public void run(){
        readJson();

    }
    public static void main(String[] args){
        System.out.println("yahoo");
        System.out.println("yahoo");
        IMDB.getInstance().run();
    }
}
