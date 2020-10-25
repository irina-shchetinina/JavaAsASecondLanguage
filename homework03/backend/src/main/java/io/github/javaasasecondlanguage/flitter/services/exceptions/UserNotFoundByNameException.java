package io.github.javaasasecondlanguage.flitter.services.exceptions;

public class UserNotFoundByNameException extends RuntimeException {
    public UserNotFoundByNameException(String userName) {
        super("User with name " + userName + "is not found");
    }

    public UserNotFoundByNameException() {
        super("User not found");
    }
}
