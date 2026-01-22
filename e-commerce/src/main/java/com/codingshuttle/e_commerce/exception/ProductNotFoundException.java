package com.codingshuttle.e_commerce.exception;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException(String message){

        super(message);
    }
}
