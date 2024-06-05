package org.example.elegant.common.exceptions;

public class WrongInputException extends RuntimeException{
    public WrongInputException(String message){
        super(message);
    }
}
