package com.spr.demo.helpers.tests;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.spr.demo.helpers.HairballRuntimeException;

public final class DebugHelpers {

    /**
     * @param request --
     * @param routineName --
     */
    public static void showParts(final HttpServletRequest request, final String routineName) {
        try {
            LOGGER.info(routineName + " - " + "parts " + request.getParts().size());
            for (final Part p : request.getParts()) {
                final String partName = p.getName();
                LOGGER.info(routineName + " - partName: " + partName);
                try (InputStreamReader isr = new InputStreamReader(p.getInputStream(), "UTF-8")) {
                    int kount = 0;
                    final StringBuilder builder = new StringBuilder();
                    final char[] buf = new char[256];
                    while ((kount = isr.read(buf)) > 0) {
                        builder.append(buf, 0, kount);
                    }
                    LOGGER.info(routineName + " - part content: " + builder.toString());
                }
            }
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param request --
     * @param routineName --
     */
    public static void showContent(final HttpServletRequest request, final String routineName) {
        try (final Reader reader = request.getReader()) {
            int kount = 0;
            final StringBuilder builder = new StringBuilder();
            final char[] buf = new char[256];
            while ((kount = reader.read(buf)) > 0) {
                builder.append(buf, 0, kount);
            }
            LOGGER.info(routineName + " - body content: " + builder.toString());
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param request --
     * @param routineName --
     */
    public static void showParams(final HttpServletRequest request, final String routineName) {
        LOGGER.info(routineName + " - " + "Param names");
        for (final Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            LOGGER.info(routineName + " - e.nextElement(): " + e.nextElement());
        }

    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(DebugHelpers.class);

    /** */
    private DebugHelpers() {}
}
