package com.spectrum.notes.exception;

public class NotUniqueResultException extends RuntimeException {

    public NotUniqueResultException() {
        super("Too many results returned");
    }
}
