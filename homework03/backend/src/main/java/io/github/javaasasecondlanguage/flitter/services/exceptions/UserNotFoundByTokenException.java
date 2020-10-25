package io.github.javaasasecondlanguage.flitter.services.exceptions;

public class UserNotFoundByTokenException extends RuntimeException {
    public UserNotFoundByTokenException(String userToken) {
        super("User with token " + userToken + "is not found");
    }

    public UserNotFoundByTokenException() {
        super("User not found by token");
    }
}
