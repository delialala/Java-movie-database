package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public abstract class Staff<T> extends User<T> implements StaffInterface{

    List<Request> requestList = new LinkedList<>();
    public SortedSet<Production> productionsContribution = new TreeSet<>();
    public SortedSet<Actor> actorsContribution = new TreeSet<>();
    @JsonProperty("productionsContribution")
    public void addProductionsContribution(ArrayList<String> names){
        for(String name : names){
            for(Production production : IMDB.getInstance().getProductions()){
                if(production.title.equals(name))
                    productionsContribution.add(production);
            }
        }
    }

    public List<Request> getRequestList() {
        return requestList;
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
        actorsContribution.add(a);
    }

    @Override
    public void removeProductionSystem(String name) {
        Production production = IMDB.getInstance().getProduction(name);
        IMDB.getInstance().getProductions().remove(production);
        // let's assume we already checked that the production exists in the contributions list
        productionsContribution.remove(production);
    }

    @Override
    public void removeActorSystem(String name) {
        Actor actor = IMDB.getInstance().getActor(name);
        IMDB.getInstance().getActors().remove(actor);
        // let's assume we already checked that the actor exists in the contributions list
        actorsContribution.remove(actor);
    }
    public boolean actorExistsInContributions(String name){
        for(Actor actor : actorsContribution){
            if(actor.getname().equals(name))
                return true;
        }
        return false;
    }

    public boolean productionExistsInContributions(String name){
        for(Production production : productionsContribution){
            if(production.title.equals(name))
                return true;
        }
        return false;
    }
    public void showProductionContributions(){
        for(Production production : productionsContribution){
            production.displayInfo();
        }
    }
    public void showActorsContributions(){
        for (Actor actor : actorsContribution){
            actor.displayInfo();
        }
    }
    // couldn't find a use for these either
    @Override
    public void updateProduction(Production p) {
    }

    @Override
    public void updateActor(Actor a) {

    }
    public void resolveRequest(Request request){
        IMDB.getInstance().getRequests().remove(request);
        requestList.remove(request);
        if(request.getType() == RequestType.MOVIE_ISSUE || request.getType() == RequestType.ACTOR_ISSUE){
            User<?> userProblem = IMDB.getInstance().getUser(request.getUsernameProblem());
            userProblem.setExperience(userProblem.getExperience() + (new AcceptedRequestExp()).calculateExperience());
        }
        setExperience(getExperience() + (new ResolveRequestExp()).calculateExperience());
    }
}
