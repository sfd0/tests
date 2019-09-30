package com.spr.demo.helpers;

import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public final class ConsoleAppenderHelper {

    /**
    *
    */
    public static void setConsoleAppender(final Logger logger) {
        if (!findConsoleAppender(logger)) {
            final ConsoleAppender c =
                new ConsoleAppender(new PatternLayout("ConsoleAppender.class"));
            c.setThreshold(Level.ALL);
            c.setName("default console");
            c.setTarget("system.out");
            logger.addAppender(c);
        }
    }

    /**
     * Find a console appender.
     *
     * @param logger --
     * @return -- True if found.
     */
    @SuppressWarnings("unchecked")
    private static boolean findConsoleAppender(final Logger logger) {
        for (final Enumeration<Appender> en = logger.getAllAppenders(); en.hasMoreElements();) {
            final Appender appender = en.nextElement();
            if (appender instanceof ConsoleAppender) {
                return true;
            }
        }
        for (final Logger lo = (Logger) logger.getParent(); null != lo;) {
            return findConsoleAppender(lo);
        }
        return false;
    }

    /** */
    private ConsoleAppenderHelper() {}
}
