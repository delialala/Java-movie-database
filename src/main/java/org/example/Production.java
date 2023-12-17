package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        @JsonSubTypes.Type(value = Series.class, name = "Series")
})
public abstract class Production implements Comparable{

    public String title;
    @JsonProperty("directors")
    List<String> directors;
    @JsonProperty("actors")
    List<String> actors;
    @JsonProperty("genres")
    List<Genre> genres;
    @JsonProperty("ratings")
    List<Rating> ratings;
    @JsonProperty("plot")
    String plot;
    @JsonProperty("averageRating")
    Double averageRating;
    //comparare in functie de title
    public abstract void displayInfo();

}
