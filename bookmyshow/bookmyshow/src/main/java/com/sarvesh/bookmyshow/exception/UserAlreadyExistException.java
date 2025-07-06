package com.sarvesh.bookmyshow.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
