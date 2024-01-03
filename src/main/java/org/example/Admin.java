package org.example;

import java.util.LinkedList;

public class Admin<T> extends Staff{
    public static class RequestsHolder{
        public static LinkedList<Request> adminRequests = new LinkedList<>();
    }
}
