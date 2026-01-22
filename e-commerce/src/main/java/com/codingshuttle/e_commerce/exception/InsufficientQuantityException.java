package com.codingshuttle.e_commerce.exception;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message){
         super(message);
    }
}
