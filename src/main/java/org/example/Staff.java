package org.example;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
public abstract class Staff<T> extends User<T> implements StaffInterface{

    List<Request> toBeResolved;
    SortedSet<Production> productionsContribution;
    SortedSet<Actor> actorsContribution;

    @Override
    public void addProductionSystem(Production p) {
        IMDB.getInstance().productions.add(p);
        productionsContribution.add(p);
    }

    @Override
    public void addActorSystem(Actor a) {
        IMDB.getInstance().actors.add(a);
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
