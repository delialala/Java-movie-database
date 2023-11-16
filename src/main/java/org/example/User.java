package org.example;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedSet;

public class User implements Comparable<User> {

    private class Information{
        private Credentials cred;
        private String nume;
        private String tara;
        private int varsta;
        private enum gen{F, M, N};
        private LocalDateTime dataNastere;
    }
    AccountType tipUtilizator;
    String username;
    int experienta;
    List<String> notificari;
    SortedSet<Object> favorite;
    public void stergeFavorit(Object deSters){
        favorite.remove(deSters);
    }
    public void adaugaFavorit(Object deAdaugat){
        favorite.add(deAdaugat);
    }
    public void addExperience(){
        experienta++;
    }
    @Override
    public int compareTo(@NotNull User user) {
        return 0;
    }
}
