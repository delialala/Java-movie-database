package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Movie extends Production{
    @JsonProperty("duration")
    String duration;
    @JsonProperty("releaseYear")
    int releaseYear;
    @Override
    public void displayInfo() {
        System.out.println("film: " + this.title + " " + duration + " " + releaseYear);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
