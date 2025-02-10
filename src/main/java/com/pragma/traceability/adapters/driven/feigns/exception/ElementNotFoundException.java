package com.pragma.traceability.adapters.driven.feigns.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super( message);
    }
}
