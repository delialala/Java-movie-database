package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class Series extends Production{
    @JsonProperty("releaseYear")
    int releaseYear;
    @JsonProperty("numSeasons")
    int numSeasons;
    @JsonProperty("seasons")
    private Map<String, List<Episode>> episodeDistribution;
    @Override
    public void displayInfo() {
        System.out.println(
                "name:" + title + "\n"
                + "year it aired: " + releaseYear + "\n"
                + "number of seasons: " + numSeasons + "\n"
        );
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.title.compareTo(((Series) o).title);
    }
}
