package org.example;

public interface Subject {
     void addObserver(Observer user);
     void removeObserver(Observer user);
     void notifyUsers(String notification);
}
