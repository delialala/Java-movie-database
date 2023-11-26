package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
public class IMDB {
    public Collection<User<?>> users;
    public Collection <Actor> actors;
    public Collection <Request> requests;
    public Collection <Production> productions;
    private static IMDB imdb = null;
    private IMDB(){
        users = new ArrayList<>();
        actors = new ArrayList<>();
        requests = new ArrayList<>();
        productions = new ArrayList<>();
    }

    public static IMDB getInstance(){
        if(imdb == null)
            imdb = new IMDB();
        return imdb;
    }

    public void parseJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = mapper.readValue("src/main/resourcecs/input/actors.json", Map.class);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public void run(){
        //reading actors json
        try{
            ObjectMapper mapper = new ObjectMapper();
            Actor[] actori = mapper.readValue(Paths.get("src\\main\\resources\\input\\actors.json").toFile(), Actor[].class);

            List<Actor> actorList = Arrays.asList(actori);
            for (Actor i : actorList) {
                System.out.println(i.name);
                for(Pair l : i.performances)
                    System.out.println(l.getType() + ":" + l.getTitle());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        IMDB.getInstance().run();
    }
}
