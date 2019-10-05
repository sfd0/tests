/**
 *
 */
package com.spr.demo.helpers;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 */
@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {

    /**
     * {@inheritDoc}
     *
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() {
        final String prefix = getServletContext().getRealPath("/");
        // System.err.println("prefix: " + prefix);
        final String file = getInitParameter("log4j-init-file");
        // System.err.println("file: " + file);
        // if the log4j-init-file is not set, then no point in trying
        if (file != null) {
            System.setProperty("log_dir_path", prefix + "../../logs");
            PropertyConfigurator.configure(prefix + file);
        }
        System.out.println("[out] Initialized log4j from " + prefix + file);
        Logger.getGlobal().warning("[java logger] Initialized log4j from " + prefix + file);
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse res) {}
}
