package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
    @JsonProperty("episodeName")
    String episodeName;
    @JsonProperty("duration")
    String duration;
    // jackson kills itself if you remove this so keep it in please
    public Episode() {
    }
    public Episode(String episodeName, String duration) {
        this.episodeName = episodeName;
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "Name: " + episodeName + "\n"
                + "Duration: " + duration;
    }
}
