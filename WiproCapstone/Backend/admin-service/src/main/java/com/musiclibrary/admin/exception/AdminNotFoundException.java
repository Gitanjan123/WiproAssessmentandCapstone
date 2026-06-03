package com.musiclibrary.admin.exception;

public class AdminNotFoundException
        extends RuntimeException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}