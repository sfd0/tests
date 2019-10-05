package org.gradle.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.spr.demo.helpers.HairballRuntimeException;
import com.spr.demo.helpers.tests.DebugHelpers;
import com.spr.demo.helpers.tests.FileItemWrapper;

@SuppressWarnings("serial")
@WebServlet(name = "FormServlet", urlPatterns = { "/rest/form" }, loadOnStartup = 1)
public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
                                                                                               throws ServletException,
                                                                                                   IOException {
        response.getWriter().print("Form Test");
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
                                                                                                throws ServletException,
                                                                                                    IOException {
        final String routineName = getClass().getName() + ".doPost";
        //
        doPostHelper(request, response, routineName);
    }

    /**
     * @param request --
     * @param response --
     * @param routineName --
     */
    private void doPostHelper(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String routineName) {
        try {
            fileUploadTest(request, routineName);
            // response.sendRedirect(request.getContextPath() + "/response.jsp");
            request.getRequestDispatcher("/response.jsp").forward(request, response);
        } catch (final Exception ex) {
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param request
     * @param routineName TODO
     */
    private void fileUploadTest(final HttpServletRequest request, final String routineName) {
        try {
            // Create a factory for disk-based file items
            final DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            final ServletContext servletContext = this.getServletConfig().getServletContext();
            final File repository =
                (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            final ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            final List<FileItem> items = upload.parseRequest(request);
            for (final FileItem item : items) {
                LOGGER.info(routineName + " - item: " + new FileItemWrapper(item));
            }
        } catch (final FileUploadException ex) {
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param request --
     * @param response --
     * @param routineName --
     */
    @SuppressWarnings("unused")
    private void postHelper(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String routineName) {
        try {
            final String contentType = request.getContentType();
            LOGGER.info(routineName + " - contentType: " + contentType);
            LOGGER.info(routineName + " - " + "Params");
            DebugHelpers.showParams(request, routineName);
            DebugHelpers.showContent(request, routineName);
            DebugHelpers.showParts(request, routineName);
        } catch (final Exception ex) {
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(FormServlet.class);
}
