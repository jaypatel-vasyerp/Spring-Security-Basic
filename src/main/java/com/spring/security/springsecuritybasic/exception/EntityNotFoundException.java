package com.spring.security.springsecuritybasic.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException (String message){
        super(message);
    }

    public EntityNotFoundException (){

    }

}
