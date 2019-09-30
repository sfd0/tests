package org.gradle.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "HulloServlet", urlPatterns = { "/rest/hullo" }, loadOnStartup = 1)
public class HulloServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
                                                                                               throws ServletException,
                                                                                                   IOException {
        response.getWriter().print("Hullo, Wurld!");
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
                                                                                                throws ServletException,
                                                                                                    IOException {
        String name = request.getParameter("name");
        if (name == null) name = "Wurld";
        request.setAttribute("user", name);
        final RequestDispatcher d = request.getRequestDispatcher("/response.jsp");
        d.forward(request, response);
    }
}
