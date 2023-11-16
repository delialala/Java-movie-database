package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Production implements Comparable<Production>{

    String titlu;
    List<String> regizori;
    List<String> actori;
    List<String> genre;
    List<Rating> evaluari;
    String descriere;
    Double nota;
    //comparare in functie de titlu
    @Override
    public int compareTo(@NotNull Production production) {
        return this.titlu.compareTo(production.titlu);
    }
    public abstract void displayInfo();

}
