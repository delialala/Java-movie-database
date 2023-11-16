package org.example;

public class Movie extends Production{
    int durata;
    int anLansare;
    @Override
    public void displayInfo() {
        System.out.println("film: " + durata + " " + anLansare);
    }
}
