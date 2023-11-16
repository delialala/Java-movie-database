package org.example;

public class Pair<L,R> {
    private L title;
    private R type;

    public Pair() {
        title = null;
        type = null;
    }

    public Pair(L title, R type) {
        this.title = title;
        this.type = type;
    }

    public L getTitle() {
        return title;
    }

    public R getType() {
        return type;
    }

    public void setTitle(L title) {
        this.title = title;
    }

    public void setType(R type) {
        this.type = type;
    }
}