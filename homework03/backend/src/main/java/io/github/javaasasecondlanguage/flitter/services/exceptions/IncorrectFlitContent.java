package io.github.javaasasecondlanguage.flitter.services.exceptions;

public class IncorrectFlitContent extends RuntimeException {
    public IncorrectFlitContent(String msg) {
        super(msg);
    }

    public IncorrectFlitContent() {
        super("IncorrectFlitContent");
    }
}
