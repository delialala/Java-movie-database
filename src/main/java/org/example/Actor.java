package org.example;

import java.util.HashMap;

public class Actor {
    String nume;
    enum Type{
        Movie, Sitcom
    }
    HashMap<String, Type> filmeJucate;
    String biografie;

    public String getNume() {
        return nume;
    }

    public HashMap<String, Type> getFilmeJucate() {
        return filmeJucate;
    }

    public String getBiografie() {
        return biografie;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setFilmeJucate(HashMap<String, Type> filmeJucate) {
        this.filmeJucate = filmeJucate;
    }

    public void setBiografie(String biografie) {
        this.biografie = biografie;
    }
}
