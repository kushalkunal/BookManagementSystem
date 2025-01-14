package com.example.bookManagementSystem.exception;

public class InvalidBookInputException extends RuntimeException {
    public InvalidBookInputException(String message) {
        super(message);
    }
}