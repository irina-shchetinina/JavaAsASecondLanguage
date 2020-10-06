package io.github.javaasasecondlanguage.homework02.webserver;

public enum LogLevelEnum {
    OFF(10), ERROR(5), WARN(4), INFO(3), DEBUG(2), TRACE(1);

    private final int severity;

    LogLevelEnum(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }
}
