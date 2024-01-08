package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        @JsonSubTypes.Type(value = Series.class, name = "Series")
})
public abstract class Production implements Comparable, Subject{

    public String title;
    List<Observer> users = new ArrayList<>();
    @JsonProperty("directors")
    List<String> directors = new ArrayList<>();
    @JsonProperty("actors")
    List<String> actors = new ArrayList<>();
    @JsonProperty("genres")
    List<Genre> genres = new ArrayList<>();
    @JsonProperty("ratings")
    List<Rating> ratings = new ArrayList<>();

    @JsonProperty("plot")
    String plot;
    @JsonProperty("averageRating")
    Double averageRating;
    //comparare in functie de title
    public abstract void displayInfo();
    // true for movie false for series
    public abstract boolean isMovieOrSeries();
    public boolean isGenreIncluded(Genre genreToBeFound){
        for(Genre genre : genres){
            if(genre.equals(genreToBeFound))
                return true;
        }
        return false;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getPlot() {
        return plot;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
    public void addDirector(String name){
        directors.add(name);
    }
    public void addActor(String name){
        actors.add(name);
    }
    public void addGenre(Genre genre){
        genres.add(genre);
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }


    public Double getAverageRating() {
        return averageRating;
    }
    public void addRating(Rating rating){
        ratings.add(rating);
        // calculating average rating
        averageRating = (averageRating * (ratings.size() - 1) + rating.rating ) / ratings.size();
    }
    public void removeRating(Rating rating){
        ratings.remove(rating);
        averageRating = ((averageRating * (ratings.size()) + 1) - rating.rating) / ratings.size();
    }
    public void displayGeneralInfo(){
        System.out.print("Directors: ");
        for(String director :directors){
            System.out.print(director + " ");
        }
        System.out.println();
        System.out.print("Actors: ");
        for(String actor : actors){
            System.out.print(actor + " ");
        }
        System.out.println();
        System.out.print("Genres: ");
        for(Genre genre : genres){
            System.out.print(genre + " ");
        }
        System.out.println();
        System.out.println("Plot: " + plot);
        System.out.println();
        System.out.println("Average rating: " + averageRating);
        System.out.println("Ratings: ");
        for(Rating rating : ratings){
            System.out.print(rating);
        }
        System.out.println("====================================");
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("Plot: ").append(plot).append("\n").append("Average rating: ").append(averageRating).append("\n");
        build.append("\n");
        build.append("Genres: ");
        for(Genre genre : genres){
            build.append(genre);
            build.append(" ");
        }
        build.append("\n");
        build.append("Directors: ");
        for(String director : directors){
            build.append(director);
            build.append(" ");
        }
        build.append("\n");
        build.append("Actors: ");
        for(String actor : actors){
            build.append(actor);
            build.append(" ");
        }
        build.append("\n");
        return build.toString();
    }

    @Override
    public int compareTo(@NotNull Object o) {
        if (o instanceof Production) {
            return this.title.compareTo(((Production) o).title);
        }
        return 0;
    }
    @Override
    public void addObserver(Observer user) {
        users.add(user);
    }

    @Override
    public void removeObserver(Observer user) {
        users.remove(user);
    }
    @Override
    public void notifyUsers(String notification) {
        for(Observer user : users){
            user.update(notification);
        }
    }

    public String getUpdate(Rating rating) {
        return "Filmul " + title + " pe care il ai la favorite a primit nota " + rating.rating + " de la utilizatorul " + rating.getUsername();
    }


}
