package org.example;
import org.example.ApplicationStates.*;
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
    private static IMDB instance = null;

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
    public <T> Boolean isInDatabase(List<T> list, T key){
        for(T value : list){
            if(value.equals(key))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private IMDB(){
        users = new ArrayList<>();
        requests = new ArrayList<>();
        productions = new ArrayList<>();
        actors = new ArrayList<>();
    }

    public static IMDB getInstance(){
        if(instance == null)
            instance = new IMDB();
        return instance;
    }


    public void readJson(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Actor[] actori = mapper.readValue(Paths.get("/home/delia/IdeaProjects/IMDBTEMA/src/main/resources/input/actors.json").toFile(), Actor[].class);
            actors = Arrays.asList(actori);

            Production[] productionsArray = mapper.readValue(Paths.get("/home/delia/IdeaProjects/IMDBTEMA/src/main/resources/input/production.json").toFile(), Production[].class);
            productions = Arrays.asList(productionsArray);

            Request[] requestsArray = mapper.readValue(Paths.get("/home/delia/IdeaProjects/IMDBTEMA/src/main/resources/input/requests.json").toFile(), Request[].class);
            requests = Arrays.asList(requestsArray);

            User<?>[] userArray = mapper.readValue(Paths.get("/home/delia/IdeaProjects/IMDBTEMA/src/main/resources/input/accounts.json").toFile(), User[].class);
            users = Arrays.asList(userArray);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run(){
        readJson();

    }
    public static void main(String[] args){
        IMDB.getInstance().run();
        Application app = new Application();
    }
}
