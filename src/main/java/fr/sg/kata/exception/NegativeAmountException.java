package fr.sg.kata.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NegativeAmountException extends RuntimeException{
    private String message;
}