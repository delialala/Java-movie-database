package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import com.fasterxml.*;
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
        Map<?, ?> map = mapper.readValue("src/main/resourcecs/input/actors.json", Map.cl)
        Actor actor = mapper.readTree()
    }

    public void run(){

    }
}
