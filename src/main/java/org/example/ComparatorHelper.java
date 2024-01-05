package org.example;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ComparatorHelper<T> implements Comparator<T> {

    @Override
    public int compare(T t, T t1) {
        String stringT = null, stringT1 = null;
        if(t instanceof Actor)
            stringT = ((Actor) t).getname();
        if(t instanceof Production)
            stringT = ((Production) t).title;
        if(t1 instanceof Actor)
            stringT1 = ((Actor) t1).getname();
        if(t1 instanceof Production)
            stringT1 = ((Production) t1).title;
        return stringT.compareTo(stringT1);
    }

    @Override
    public Comparator<T> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<T> thenComparing(Comparator<? super T> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
