package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
public class IMDB {
    List<User> useri;
    List <Actor> actori;
    List <Request> cereri;
    List <Production> productii;
    private static IMDB imdb = null;
    private IMDB(){
        useri = new ArrayList<>();
        actori = new ArrayList<>();
        cereri = new ArrayList<>();
        productii = new ArrayList<>();
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

    }
    public static void main(String[] args){
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
}
