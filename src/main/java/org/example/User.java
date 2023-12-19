package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.SortedSet;

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
    static class Information{
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
        private String birthDate;

        public Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.age = builder.age;
            this.birthDate = builder.birthDate;
            this.gender = builder.gender;
            }
        @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
        static class InformationBuilder {
            private Credentials credentials;
            private String name;
            private String country;
            private int age;
            private String birthDate;
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

            public InformationBuilder birthDate(String birthDate) {
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
    List<String> notifications;
    @JsonProperty("favorite")
    SortedSet<T> favorites;

    public SortedSet<T> getFavorites() {
        return favorites;
    }

    public void setFavorites(SortedSet<T> favorites) {
        this.favorites = favorites;
    }

    public void removeFavorite(T e){
        favorites.remove(e);
    }
    public void addFavorite(T e){
        favorites.add(e);
    }
    public void addExperience(){
        experience++;
    }

    @JsonSetter("favoriteActors")
    public void setFavoriteActors(SortedSet<T> favorites) {
        this.favorites = favorites;
    }
    @JsonSetter("favoriteProductions")
    public void setFavoriteProductions(SortedSet<T> favorites) {
        this.favorites = favorites;
    }

    @Override
    public void update(String message) {
        System.out.println("new rating");
    }



    @Override
    public int compareTo(@NotNull User user) {
        return this.username.compareTo(user.username);
    }
}
