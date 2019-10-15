package com.demo.contactif.exception;

public class NotFoundInStoreException extends RuntimeException {
    public NotFoundInStoreException() {
        super("There is no such data.");
    }
}
