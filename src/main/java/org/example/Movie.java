package org.example;

public class Movie extends Production{
    int duration;
    int releaseYear;
    @Override
    public void displayInfo() {
        System.out.println("film: " + this.title + " " + duration + " " + releaseYear);
    }
}
