package com.example.releases.exceptions;

public class BookNotFoundException extends RuntimeException{
    //serialization
    private static final long serialVersionUID = 1;

    public BookNotFoundException(String message){
        super(message);
    }

}
