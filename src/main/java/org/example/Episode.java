package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
    @JsonProperty("episodeName")
    String episodeName;
    @JsonProperty("duration")
    String duration;
}
