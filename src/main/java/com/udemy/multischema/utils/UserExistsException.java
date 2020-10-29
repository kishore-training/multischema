package com.udemy.multischema.utils;

public class UserExistsException extends Exception {

    public UserExistsException(String message){
        super(message);
    }
}
