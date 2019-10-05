/**
 *
 */
package com.spr.demo.helpers.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

/**
 *
 */
public class FileItemWrapper {

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FileItemWrapper [getHeaders()=");
        builder.append(getHeaders());
        builder.append(", getContentType()=");
        builder.append(getContentType());
        builder.append(", getName()=");
        builder.append(getName());
        builder.append(", isInMemory()=");
        builder.append(isInMemory());
        builder.append(", getSize()=");
        builder.append(getSize());
        builder.append(", get()=");
        builder.append(Arrays.toString(get()));
        builder.append(", getString()=");
        builder.append(getString());
        builder.append(", getFieldName()=");
        builder.append(getFieldName());
        builder.append(", isFormField()=");
        builder.append(isFormField());
        builder.append("]");
        return builder.toString();
    }

    /**
     * @param item --
     */
    public FileItemWrapper(final FileItem item) {
        itsItem = item;
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItemHeadersSupport#getHeaders()
     */
    public FileItemHeaders getHeaders() {
        return itsItem.getHeaders();
    }

    /**
     * @param headers
     * @see org.apache.commons.fileupload.FileItemHeadersSupport#setHeaders(org.apache.commons.fileupload.FileItemHeaders)
     */
    public void setHeaders(final FileItemHeaders headers) {
        itsItem.setHeaders(headers);
    }

    /**
     * @return
     * @throws IOException
     * @see org.apache.commons.fileupload.FileItem#getInputStream()
     */
    public InputStream getInputStream() throws IOException {
        return itsItem.getInputStream();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#getContentType()
     */
    public String getContentType() {
        return itsItem.getContentType();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#getName()
     */
    public String getName() {
        return itsItem.getName();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#isInMemory()
     */
    public boolean isInMemory() {
        return itsItem.isInMemory();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#getSize()
     */
    public long getSize() {
        return itsItem.getSize();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#get()
     */
    public byte[] get() {
        return itsItem.get();
    }

    /**
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     * @see org.apache.commons.fileupload.FileItem#getString(java.lang.String)
     */
    public String getString(final String encoding) throws UnsupportedEncodingException {
        return itsItem.getString(encoding);
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#getString()
     */
    public String getString() {
        return itsItem.getString();
    }

    /**
     * @param file
     * @throws Exception
     * @see org.apache.commons.fileupload.FileItem#write(java.io.File)
     */
    public void write(final File file) throws Exception {
        itsItem.write(file);
    }

    /**
     * @see org.apache.commons.fileupload.FileItem#delete()
     */
    public void delete() {
        itsItem.delete();
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#getFieldName()
     */
    public String getFieldName() {
        return itsItem.getFieldName();
    }

    /**
     * @param name
     * @see org.apache.commons.fileupload.FileItem#setFieldName(java.lang.String)
     */
    public void setFieldName(final String name) {
        itsItem.setFieldName(name);
    }

    /**
     * @return
     * @see org.apache.commons.fileupload.FileItem#isFormField()
     */
    public boolean isFormField() {
        return itsItem.isFormField();
    }

    /**
     * @param state
     * @see org.apache.commons.fileupload.FileItem#setFormField(boolean)
     */
    public void setFormField(final boolean state) {
        itsItem.setFormField(state);
    }

    /**
     * @return
     * @throws IOException
     * @see org.apache.commons.fileupload.FileItem#getOutputStream()
     */
    public OutputStream getOutputStream() throws IOException {
        return itsItem.getOutputStream();
    }

    private final FileItem itsItem;

}
