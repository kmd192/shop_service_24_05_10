package com.example.shop.error;

public class SignupUsernameDuplicatedException extends RuntimeException{

    public SignupUsernameDuplicatedException(String message){
        super(message);
    }
}
