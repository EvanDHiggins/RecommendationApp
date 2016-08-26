package com.evan.musicrecommender;

import java.util.NoSuchElementException;

/**
 * Created by evanh on 8/16/2016.
 */
public class Optional<T> {

    boolean hasValue;

    T value;

    private static final Optional<?> EMPTY = new Optional<>();

    public static<T> Optional<T> of(T newValue) {
        return new Optional<>(newValue);
    }

    public static<T> Optional<T> ofNullable(T newValue) {
        if(newValue == null)
            return empty();
        return Optional.of(newValue);
    }

    public static<T> Optional<T> empty() {
        return (Optional<T>)EMPTY;
    }

    public Optional() {
        boolean hasValue = false;
        this.value = null;
    }

    public Optional(T value) {
        this.hasValue = true;
        this.value = value;
    }

    public boolean isPresent() {
        return hasValue;
    }

    public T get() {
        if(!hasValue)
            throw new NoSuchElementException("Optional.get() called on empty optional.");
        return value;
    }
}
