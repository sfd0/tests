package org.gradle.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.spr.demo.helpers.ConsoleAppenderHelper;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
                                                                                               throws ServletException,
                                                                                                   IOException {
        response.getWriter().print("Hello, World!");
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
                                                                                                throws ServletException,
                                                                                                    IOException {
        final String routineName = "doPost";
        ConsoleAppenderHelper.setConsoleAppender(LOGGER);
        String name = request.getParameter("say-hello-text-input");
        LOGGER.info(routineName + " - name: " + name);
        if (name == null) name = "World";
        request.setAttribute("user", name);
        request.getRequestDispatcher("/response.jsp").forward(request, response);
    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(HelloServlet.class);
}
