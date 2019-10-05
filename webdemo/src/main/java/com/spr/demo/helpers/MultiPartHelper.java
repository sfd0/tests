/**
 *
 */
package com.spr.demo.helpers;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.spr.demo.helpers.tests.FileItemWrapper;

/**
 *
 */
public class MultiPartHelper {

    /**
     * @param request --
     */
    public MultiPartHelper(final ServletContext servletContext, final HttpServletRequest request) {
        final String routineName = "MultiPartHelper";
        try {
            // Create a factory for disk-based file items
            final DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            final File repository =
                (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            final ServletFileUpload upload = new ServletFileUpload(factory);
            if (!ServletFileUpload.isMultipartContent(request)) {
                LOGGER.warn(routineName + " - " + "Not a multipart content.");
                return;
            }

            // Parse the request
            final List<FileItem> items = upload.parseRequest(request);
            for (final FileItem item : items) {
                LOGGER.info(routineName + " - item: " + new FileItemWrapper(item));
                itemsMap.put(item.getFieldName(), item);
                if (item.isFormField()) {
                    formFieldItems.put(item.getFieldName(), item);
                }
            }
        } catch (final FileUploadException ex) {
            throw new HairballRuntimeException(ex);
        }
    }

    /**
     * @param fieldName --
     * @return --
     */
    public String getFormFieldValue(final String fieldName) {
        final String routineName = "getFormFieldValue";
        if (formFieldItems.containsKey(fieldName)) {
            return formFieldItems.get(fieldName).getString();
        }
        final String msg = "Unrecognized form field " + fieldName;
        LOGGER.info(routineName + " - " + msg);
        throw new IllegalArgumentException(msg);
    }

    /**
     * @param fieldName --
     * @return --
     */
    public FileItem getItem(final String fieldName) {
        final String routineName = "getItem";
        if (itemsMap.containsKey(fieldName)) {
            return itemsMap.get(fieldName);
        }
        final String msg = "Unrecognized form field " + fieldName;
        LOGGER.info(routineName + " - " + msg);
        throw new IllegalArgumentException(msg);
    }

    private final Map<String, FileItem> itemsMap = new HashMap<>();

    private final Map<String, FileItem> formFieldItems = new HashMap<>();

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(MultiPartHelper.class);

}
