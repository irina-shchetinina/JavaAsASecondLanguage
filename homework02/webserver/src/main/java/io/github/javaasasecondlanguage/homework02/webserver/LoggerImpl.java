package io.github.javaasasecondlanguage.homework02.webserver;

public class LoggerImpl implements Logger {

    private LogLevelEnum minLogLevel;

    public LoggerImpl(LogLevelEnum minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    @Override
    public void error(String msg) {
        log(msg, LogLevelEnum.ERROR);
    }

    @Override
    public void warn(String msg) {
        log(msg, LogLevelEnum.WARN);
    }

    @Override
    public void info(String msg) {
        log(msg, LogLevelEnum.INFO);
    }

    @Override
    public void debug(String msg) {
        log(msg, LogLevelEnum.DEBUG);
    }

    @Override
    public void trace(String msg) {
        log(msg, LogLevelEnum.TRACE);
    }

    private void log(String msg, LogLevelEnum msgLogLevel) {
        if (msgLogLevel.getSeverity() >= minLogLevel.getSeverity()) {
            System.out.println(msg);
        }
    }

}
