package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
    List<Pair<String, Type>> performances = new ArrayList<>();
    @JsonProperty("biography")
    String biography;

    public void setPerformances(List<Pair<String, Type>> performances) {
        this.performances = performances;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public List<Pair<String, Type>> getPerformances() {
        return performances;
    }

    public void addPerformance(String title, Type type) {
        this.performances.add(new Pair<>(title, type));
    }

    public void setbiography(String biography) {
        this.biography = biography;
    }
    public void displayInfo(){
        System.out.println("Name: " + name + "\n"
        + "Biography: " + biography + "\n"
        + "Performances: ");
        for(Pair<String, Type> performance : performances){
            System.out.println(performance.getTitle() + " - " + performance.getType());
        }
        System.out.println("========================");
    }
}
