package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.ApplicationStates.Application;
import org.jetbrains.annotations.NotNull;


import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "userType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Contributor.class, name = "Contributor"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin"),
        @JsonSubTypes.Type(value = Regular.class, name = "Regular")
})
public abstract class User<T> implements Comparable<User<T>>, Observer {
    @JsonDeserialize(builder = Information.InformationBuilder.class)
    public static class Information{
        @JsonProperty("credentials")
        private Credentials credentials;
        @JsonProperty("name")
        private String name;
        @JsonProperty("country")
        private String country;
        @JsonProperty("age")
        private int age;
        @JsonProperty("gender")
        private String gender;
        @JsonProperty("birthDate")
        private Date birthDate;

        public String getName() {
            return name;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.age = builder.age;
            this.birthDate = builder.birthDate;
            this.gender = builder.gender;
            }
        @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
        public static class InformationBuilder {
            private Credentials credentials;
            private String name;
            private String country;
            private int age;
            private Date birthDate;
            private String gender;

            public InformationBuilder credentials(Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            public InformationBuilder name(String name) {
                this.name = name;
                return this;
            }

            public InformationBuilder country(String country) {
                this.country = country;
                return this;
            }

            public InformationBuilder age(int age) {
                this.age = age;
                return this;
            }

            public InformationBuilder birthDate(Date birthDate) {
                this.birthDate = birthDate;
                return this;
            }
            public InformationBuilder gender(String userGender) {
                this.gender = userGender;
                return this;
            }
            public Information build(){
                return new Information(this);
            }
        }
    }
    @JsonProperty("information")
    Information information;
    @JsonProperty("userType")
    AccountType userType;
    @JsonProperty("username")
    String username;
    @JsonProperty("experience")
    int experience;
    @JsonProperty("notifications")
    List<String> notifications = new ArrayList<>();
    @JsonProperty("favorite")
    SortedSet<T> favorites = new TreeSet<>(new ComparatorHelper<>());

    public SortedSet<T> getFavorites() {
        return favorites;
    }

    public void setFavorites(SortedSet<T> favorites) {
        this.favorites = favorites;
    }

    public void removeFavoriteActor(Actor e){
        favorites.remove((T)e);
    }
    public void removeFavoriteProduction(Production e){
        favorites.remove((T)e);
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setUserType(AccountType userType) {
        this.userType = userType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean addFavorite(String nameToBeAdded){
        // search if the actor or production exists
        for(Actor actor : IMDB.getInstance().getActors()){
            if(nameToBeAdded.equals(actor.getname()))
            {
                favorites.add((T) actor);
                return true;
            }
        }
            for(Production production : IMDB.getInstance().getProductions()){
                if(nameToBeAdded.equals(production.title))
                {
                    favorites.add((T)production);
                    production.addObserver(this);
                    return true;
                }
            }
        return false;
    }
    public boolean removeFavorite(String nameToBeAdded){
        // search if the actor or production exists
        for(Actor actor : IMDB.getInstance().getActors()){
            if(nameToBeAdded.equals(actor.getname()))
            {
                favorites.remove((T) actor);
                return true;
            }
        }
        for(Production production : IMDB.getInstance().getProductions()){
            if(nameToBeAdded.equals(production.title))
            {
                favorites.remove((T)production);
                production.removeObserver(this);
                return true;
            }
        }
        return false;
    }

    public void addExperience(){
        experience++;
    }

    @JsonSetter("favoriteActors")
    public void setFavoriteActors(ArrayList<String> favoriteActors) {
        for(String actorName : favoriteActors){
            //look in the actors list for the name
            for(Actor actor : IMDB.getInstance().getActors()){
                if(actorName.equals(actor.name))
                    favorites.add((T) actor);
            }
        }
    }
    @JsonSetter("favoriteProductions")
    public void setFavoriteProductions(ArrayList<String> favoriteProductions) {
        for(String productionName : favoriteProductions){
            //look in the productions list for the name
            for(Production production : IMDB.getInstance().getProductions()){
                if(productionName.equals(production.title))
                {
                    favorites.add((T) production);
                    production.addObserver(this);
                }
            }
        }
    }

    public void displayInfo(){
        System.out.println("Username: " + username + "\n"
                + "mail: " + information.credentials.getEmail() + "\n"
                + "password: " + information.credentials.getPassword() + "\n"
                + "age: " + information.age + "\n"
                + "birthdate: " + information.birthDate + "\n"
                + "country: " + information.country + "\n");
    }

    @Override
    public String toString() {
        String temp = "Mail: " + information.credentials.getEmail() + "\n"
                + "Experience: " + experience + "\n"
                + "Age: " + information.age + "\n"
                + "Birthdate: " + information.birthDate + "\n"
                + "Country: " + information.country + "\n";
        StringBuilder builder = new StringBuilder (temp);
        builder.append("Favorites: ");
        for(T t : favorites){
            if(t instanceof Production)
                builder.append(((Production) t).title);
            if(t instanceof Actor)
                builder.append(((Actor) t).name);
            builder.append("; ");
        }
        return builder.toString();
    }

    @Override
    public void update(String message) {
        notifications.add(message);
    }

    public Information getInformation() {
        return information;
    }

    public String getUsername() {
        return username;
    }

    public int getExperience() {
        return experience;
    }

    public List<String> getNotifications() {
        return notifications;
    }
    public void showFavorites(){
        for(T entry : favorites){
            if(entry instanceof Actor)
                System.out.println(((Actor) entry).name);
            if(entry instanceof Production)
                System.out.println(((Production) entry).title);
        }
    }
    public boolean existsInFavorites(String name){
        for(T entry : favorites){
            if(entry instanceof Actor)
                if(name.equals(((Actor) entry).name))
                    return true;
            if(entry instanceof Production)
                if(name.equals(((Production) entry).title))
                    return true;
        }
        return false;
    }
    public Rating whatRatingTheyveLeft(Production production){
        for(Rating rating : production.getRatings())
            if(rating.getUsername().equals(username))
                return rating;
        return null;
    }
    public boolean isInFavorites(String name){
        for(T t : favorites){
            if(t instanceof Production)
                if(((Production) t).title.equals(name))
                    return true;
            if(t instanceof Actor)
                if(((Actor) t).name.equals(name))
                    return true;
        }
        return false;
    }
    @Override
    public int compareTo(@NotNull User user) {
        return this.username.compareTo(user.username);
    }
}
