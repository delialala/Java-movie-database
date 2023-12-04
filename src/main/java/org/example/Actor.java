package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Actor {
    public String name;

    public enum Type{
        @JsonProperty
        Movie, Sitcom
    }
    List<Pair<String, Type>> performances;
    String biography;

    public String getname() {
        return name;
    }



    public String getbiography() {
        return biography;
    }

    public void setname(String name) {
        this.name = name;
    }

    public List<Pair<String, Type>> getPerformances() {
        return performances;
    }

    public void setPerformances(String title, Type type) {
        this.performances.add(new Pair<>(title, type));
    }

    public void setbiography(String biography) {
        this.biography = biography;
    }
}
