package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public abstract class Staff<T> extends User<T> implements StaffInterface{

    List<Request> toBeResolved;
    SortedSet<Production> productionsContribution = new TreeSet<>();
    SortedSet<Actor> actorsContribution = new TreeSet<>();
    @JsonProperty("productionsContribution")
    public void addProductionsContribution(ArrayList<String> names){
        for(String name : names){
            for(Production production : IMDB.getInstance().getProductions()){
                if(production.title.equals(name))
                    productionsContribution.add(production);
            }
        }
    }
    @JsonProperty("actorsContribution")
    public void addActorsContribution(ArrayList<String> names){
        for(String name : names) {
            for (Actor actor : IMDB.getInstance().getActors()) {
                if (actor.name.equals(name))
                    actorsContribution.add(actor);
            }
        }
    }

    @Override
    public void addProductionSystem(Production p) {
        IMDB.getInstance().getProductions().add(p);
        productionsContribution.add(p);
    }

    @Override
    public void addActorSystem(Actor a) {
        IMDB.getInstance().getActors().add(a);
    }

    @Override
    public void removeProductionSystem(String name) {

    }

    @Override
    public void removeActorSystem(String name) {

    }

    @Override
    public void updateProduction(Production p) {

    }

    @Override
    public void updateActor(Actor a) {

    }
}
