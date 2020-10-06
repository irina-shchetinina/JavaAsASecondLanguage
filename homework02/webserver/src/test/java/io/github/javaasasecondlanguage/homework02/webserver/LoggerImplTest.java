package io.github.javaasasecondlanguage.homework02.webserver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class LoggerImplTest {


    private TestPrinter printer = new TestPrinter();

    @Test
    void off() {
        var logger = new LoggerImpl(LogLevelEnum.OFF, printer);

        shouldNotLog(msg -> logger.error(msg));
        shouldNotLog(msg -> logger.warn(msg));
        shouldNotLog(msg -> logger.info(msg));
        shouldNotLog(msg -> logger.debug(msg));
        shouldNotLog(msg -> logger.trace(msg));
    }

    @Test
    void error() {

        var logger = new LoggerImpl(LogLevelEnum.ERROR, printer);

        shouldLog(msg -> logger.error(msg));
        shouldNotLog(msg -> logger.warn(msg));
        shouldNotLog(msg -> logger.info(msg));
        shouldNotLog(msg -> logger.debug(msg));
        shouldNotLog(msg -> logger.trace(msg));
    }

    @Test
    void warn() {
        var logger = new LoggerImpl(LogLevelEnum.WARN, printer);

        shouldLog(msg -> logger.error(msg));
        shouldLog(msg -> logger.warn(msg));
        shouldNotLog(msg -> logger.info(msg));
        shouldNotLog(msg -> logger.debug(msg));
        shouldNotLog(msg -> logger.trace(msg));
    }

    @Test
    void info() {
        var logger = new LoggerImpl(LogLevelEnum.INFO, printer);

        shouldLog(msg -> logger.error(msg));
        shouldLog(msg -> logger.warn(msg));
        shouldLog(msg -> logger.info(msg));
        shouldNotLog(msg -> logger.debug(msg));
        shouldNotLog(msg -> logger.trace(msg));
    }

    @Test
    void debug() {
        var logger = new LoggerImpl(LogLevelEnum.DEBUG, printer);

        shouldLog(msg -> logger.error(msg));
        shouldLog(msg -> logger.warn(msg));
        shouldLog(msg -> logger.info(msg));
        shouldLog(msg -> logger.debug(msg));
        shouldNotLog(msg -> logger.trace(msg));
    }

    @Test
    void trace() {
        var logger = new LoggerImpl(LogLevelEnum.TRACE, printer);

        shouldLog(msg -> logger.error(msg));
        shouldLog(msg -> logger.warn(msg));
        shouldLog(msg -> logger.info(msg));
        shouldLog(msg -> logger.debug(msg));
        shouldLog(msg -> logger.trace(msg));
    }



    private void shouldLog(Consumer<String> action) {
        testLog(true, action);
    }

    private void shouldNotLog(Consumer<String> action) {
        testLog(false, action);
    }

    private void testLog(boolean shouldLog, Consumer<String> action) {
        String msg = UUID.randomUUID().toString();
        action.accept(msg);
        if (shouldLog) {
            assertEquals(msg, printer.getLastPrintedMsg());
        } else {
            assertNull(printer.getLastPrintedMsg());
        }
        printer.reset();
    }

    private class TestPrinter implements Printer {
        private String printStr = null;

        @Override
        public void print(String msg) {
            printStr = msg;
        }

        public String getLastPrintedMsg() {
            return printStr;
        }

        public void reset() {
            printStr = null;
        }
    }
}