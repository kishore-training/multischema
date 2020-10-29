package com.udemy.multischema.utils;

public class AppointmentExistsException extends Exception {

    public AppointmentExistsException(String message){
        super(message);
    }
}
