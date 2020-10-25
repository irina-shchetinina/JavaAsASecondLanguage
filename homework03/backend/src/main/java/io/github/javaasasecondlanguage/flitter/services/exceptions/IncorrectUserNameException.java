package io.github.javaasasecondlanguage.flitter.services.exceptions;

public class IncorrectUserNameException extends RuntimeException {

    public IncorrectUserNameException() {
        super("Incorrect user name");
    }

    public IncorrectUserNameException(String message) {
        super(message);
    }
}
