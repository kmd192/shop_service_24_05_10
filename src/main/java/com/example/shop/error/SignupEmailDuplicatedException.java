package com.example.shop.error;

public class SignupEmailDuplicatedException extends RuntimeException{

    public SignupEmailDuplicatedException(String message){
        super(message);
    }
}
