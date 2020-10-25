package io.github.javaasasecondlanguage.flitter.services.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException{

    public UserAlreadyRegisteredException() {
        super("This name is already taken");
    }

    public String toString(){
        return ("This name is already taken") ;
    }
}
