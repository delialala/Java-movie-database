package org.example.ApplicationStates;

import org.example.Genre;
import org.example.IMDB;
import org.example.Request;
import org.example.User;

import java.util.ArrayList;

public class Application {
    static User<?> user;
    ApplicationState state = new ChooseInterfaceState(this);
    static ArrayList<Request> userRequests = new ArrayList<>();
    int prodMinNumberReviews;
    int prodMaxNumberReviews;
    int prodMinRating;
    int prodMaxRating;
    Genre prodGenre = null;
    // 0 - any, 1 - movies, 2 - series
    int prodOnlyMoviesOrSeries = 0;
    boolean actorSortAlphabetically;
    int actorMinNumberPerformances;
    int actorMaxNumberPerformances;
    public void setUserRequests() {
        for (Request request : IMDB.getInstance().getRequests()) {
            if (request.getUsernameProblem().equals(Application.user.getUsername())) {
                userRequests.add(request);
            }
        }
    }

    public void setState(ApplicationState state) {
        this.state = state;
    }

}
