package fr.sg.kata.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BalanceInsufficientException extends RuntimeException{
    private String message;
}