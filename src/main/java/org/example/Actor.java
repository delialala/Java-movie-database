package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Actor implements Comparable{
    public String name;

    @Override
    public int compareTo(@NotNull Object o) {
        return this.name.compareTo(((Actor) o).name);
    }

    public enum Type{
        Movie, Sitcom, Series
    }
    @JsonProperty("performances")
    List<Pair<String, Type>> performances;
    @JsonProperty("biography")
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
