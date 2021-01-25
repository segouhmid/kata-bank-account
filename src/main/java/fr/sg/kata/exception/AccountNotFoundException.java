package fr.sg.kata.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountNotFoundException extends RuntimeException{
    private String message;
}
