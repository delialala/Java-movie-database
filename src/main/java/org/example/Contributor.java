package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.SortedSet;

public class Contributor<T> extends Staff implements RequestsManager{
    @Override
    public void createRequest(Request r) {

    }

    @Override
    public void removeRequest(Request r) {

    }
    //eu il am pasta aici doar ca sa nu tipe checkerul la mn
    SortedSet<T> contributions;

    public SortedSet<T> getContributions() {
        return contributions;
    }

    public void setContributions(SortedSet<T> contributions) {
        this.contributions = contributions;
    }
}
