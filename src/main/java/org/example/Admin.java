package org.example;

import java.util.ArrayList;
import java.util.SortedSet;

public class Admin<T> extends Staff{
    int test;
    SortedSet<T> contributions;

    public SortedSet<T> getContributions() {
        return contributions;
    }

    public void setContributions(SortedSet<T> contributions) {
        this.contributions = contributions;
    }
}
