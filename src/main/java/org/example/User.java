package org.example;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public abstract class User<T> implements Comparable<User<T>> {

    class Information{
        private Credentials credentials;
        private String nume;
        private String country;
        private int age;
        private enum gender{F, M, N}
        private LocalDateTime birthDate;

        public Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.nume = builder.nume;
            this.country = builder.country;
            this.age = builder.age;
            this.birthDate = builder.birthDate;
            }
        static class InformationBuilder {
            private Credentials credentials;
            private String nume;
            private String country;
            private int age;
            private LocalDateTime birthDate;

            public InformationBuilder credentials(Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            public InformationBuilder nume(String nume) {
                this.nume = nume;
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

            public InformationBuilder birthDate(LocalDateTime birthDate) {
                this.birthDate = birthDate;
                return this;
            }
        }

    }
    AccountType userType;
    String username;
    int experience;
    List<String> notifications;
    SortedSet<T> favoriteProductions;
    public void stergeFavorit(T deSters){
        favoriteProductions.remove(deSters);
    }
    public void adaugaFavorit(T deAdaugat){
        favoriteProductions.add(deAdaugat);
    }
    public void addExperience(){
        experience++;
    }
    @Override
    public int compareTo(@NotNull User user) {
        return 0;
    }
}
