package org.example;

public interface Subject {
    abstract void addObserver(Observer<?> user);
    abstract void removeObserver(Observer<?> user);
    abstract void notifyUsers();
    abstract String getUpdate();
}
