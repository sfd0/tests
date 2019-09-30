package com.spr.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.spr.demo.helpers.ConsoleAppenderHelper;
import com.spr.demo.helpers.HairballRuntimeException;

@SuppressWarnings("serial")
public class EchoServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
                                                                                               throws ServletException,
                                                                                                   IOException {
        final String routineName = "doGet";
        helper(request, response, routineName);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
                                                                                                throws ServletException,
                                                                                                    IOException {
        final String routineName = "doPost";
        helper(request, response, routineName);
    }

    private void helper(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String callerName) {
        final String routineName = "helper";
        try {
            ConsoleAppenderHelper.setConsoleAppender(LOGGER);
            String echoText = request.getParameter("echo-input");
            if (echoText == null) echoText = "default";
            LOGGER.info(callerName + " - echoing: " + echoText);
            response.setContentType("text/plain");
            response.setStatus(200);
            final PrintWriter pw = response.getWriter();
            pw.print(echoText + echoText);
            pw.flush();
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - ex.getMessage(), ex: " + ex, ex);
            throw new HairballRuntimeException(ex);
        }

    }

    /**
     *
     */
    public static final Logger LOGGER = Logger.getLogger(EchoServlet.class);
}
