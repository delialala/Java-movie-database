package org.example;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating{
    @JsonProperty("username")
    String username;
    @JsonProperty("rating")
    int rating;
    @JsonProperty("comment")
    String comment;

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Rating by: " + username + "\n"
                + "Score given: " + rating + "\n"
                + "Comment: " + comment + "\n";
    }
}
