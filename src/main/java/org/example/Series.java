package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Series extends Production{
    @JsonProperty("releaseYear")
    int releaseYear;
    @JsonProperty("numSeasons")
    int numSeasons;
    @JsonProperty("seasons")
    private Map<String, List<Episode>> episodeDistribution;

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setEpisodeDistribution(Map<String, List<Episode>> episodeDistribution) {
        this.episodeDistribution = episodeDistribution;
    }

    public void setNumSeasons(int numSeasons) {
        this.numSeasons = numSeasons;
    }

    public void addSeason(String name, ArrayList<Episode> episodes){
        episodeDistribution.put(name, episodes);
    }
    @Override
    public void displayInfo() {
        System.out.println(
                "Series name: " + title + "\n"
                        + "Release year: " + releaseYear + "\n"
                        + "Number of seasons: " + numSeasons + "\n");
        System.out.println("Episode distribution: ");
        for(Map.Entry<String, List<Episode>> entry: episodeDistribution.entrySet()){
            String seasonName = entry.getKey();
            List<Episode> episodes = entry.getValue();
            System.out.println("Episode list for " + seasonName + ":");
            for(Episode episode : episodes){
                System.out.println(episode);
            }
        }
        super.displayGeneralInfo();
    }

    @Override
    public boolean isMovieOrSeries() {
        return false;
    }

}
