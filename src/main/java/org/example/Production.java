package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Production implements Comparable<Production>{

    String title;
    List<String> directors;
    List<String> actors;
    List<String> genres;
    List<Rating> ratings;
    String plot;
    Double averageRating;
    //comparare in functie de title
    @Override
    public int compareTo(@NotNull Production production) {
        return this.title.compareTo(production.title);
    }
    public abstract void displayInfo();

}
