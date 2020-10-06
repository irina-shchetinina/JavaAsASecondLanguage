package io.github.javaasasecondlanguage.homework02.webserver;

public interface Logger {
    void error(String msg);

    void warn(String msg);

    void info(String msg);

    void debug(String msg);

    void trace(String msg);
}
