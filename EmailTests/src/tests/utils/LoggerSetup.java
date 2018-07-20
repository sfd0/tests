/**
 *
 */
package tests.utils;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 */
public final class LoggerSetup {

    /**
     * Set up a console logger.
     *
     * @param logger
     *            --
     */
    public static void setUp(final Logger logger) {
        logger.addAppender(new ConsoleAppender(new PatternLayout("%d %5p [%t]%c - %m%n")));
        logger.setLevel(Level.ALL);
    }

    /**
     * Set up a console logger.
     *
     * @param loggerName
     *            --
     */
    public static void setUp(final String loggerName) {
        final Logger logger = Logger.getLogger(loggerName);
        logger.addAppender(new ConsoleAppender(new PatternLayout("%d %5p [%t]%c - %m%n")));
        logger.setLevel(Level.ALL);
    }

    /** */
    private LoggerSetup() {}
}
