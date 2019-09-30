package com.spr.demo.helpers.tests;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.spr.demo.EchoServlet;
import com.spr.demo.helpers.HairballRuntimeException;

public class EchoServletInner {

    /**
     * @param request --
     * @param routineName --
     */
    public static void smith(final HttpServletRequest request, final String routineName) {
        EchoServlet.LOGGER.info(routineName + " - " + "parts");
        try {
            for (final Part p : request.getParts()) {
                final String partName = p.getName();
                EchoServlet.LOGGER.info(routineName + " - partName: " + partName);
                try (
                    InputStreamReader isr =
                        new InputStreamReader(p.getInputStream(), "UTF-8")) {
                    int kount = 0;
                    final StringBuilder builder = new StringBuilder();
                    final char[] buf = new char[256];
                    while ((kount = isr.read(buf)) > 0) {
                        builder.append(buf, 0, kount);
                    }
                    EchoServlet.LOGGER.info(routineName + " - part content: " + builder.toString());
                }
            }
        } catch (final Exception ex) {
            EchoServlet.LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param request --
     * @param routineName --
     */
    public static void jones(final HttpServletRequest request, final String routineName) {
        EchoServlet.LOGGER.info(routineName + " - " + "Param names");
        for (final Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            EchoServlet.LOGGER.info(routineName + " - e.nextElement(): " + e.nextElement());
        }
        try (final Reader reader = request.getReader()) {
            int kount = 0;
            final StringBuilder builder = new StringBuilder();
            final char[] buf = new char[256];
            while ((kount = reader.read(buf)) > 0) {
                builder.append(buf, 0, kount);
            }
            EchoServlet.LOGGER.info(routineName + " - body content: " + builder.toString());
        } catch (final Exception ex) {
            EchoServlet.LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new HairballRuntimeException(ex);
        }
    }
}