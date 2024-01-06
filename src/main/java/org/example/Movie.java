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
        System.out.println(
                "Movie name: " + title + "\n"
                        + "Release year: " + releaseYear + "\n"
                        + "Duration: " + duration);
        super.displayGeneralInfo();
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDuration() {
        return duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @Override
    public boolean isMovieOrSeries() {
        return true;
    }

}
