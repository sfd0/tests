package tests.simplepojo;

/**
 *
 */
public interface IEmailData {

    /**
     * @return the template
     */
    Template getTemplate();

    /**
     * @param template
     *            the template to set
     */
    void setTemplate(Template template);

    /**
     * @return the requesterName
     */
    RequesterName getRequesterName();

    /**
     * @param requesterName
     *            the requesterName to set
     */
    void setRequesterName(RequesterName requesterName);

    /**
     * @return the fromAddress
     */
    String getFromAddress();

    /**
     * @param fromAddress
     *            the fromAddress to set
     */
    void setFromAddress(String fromAddress);

    /**
     * @return the toAddress
     */
    String[] getToAddress();

    /**
     * @param toAddress
     *            the toAddress to set
     */
    void setToAddress(String[] toAddress);

    /**
     * @return the ccAddress
     */
    String[] getCcAddress();

    /**
     * @param ccAddress
     *            the ccAddress to set
     */
    void setCcAddress(String[] ccAddress);

    /**
     * @return the bccAddress
     */
    String[] getBccAddress();

    /**
     * @param bccAddress
     *            the bccAddress to set
     */
    void setBccAddress(String[] bccAddress);

    /**
     * @return the subject
     */
    String getSubject();

    /**
     * @param subject
     *            the subject to set
     */
    void setSubject(String subject);

    /**
     * @return the values
     */
    Value[] getValues();

    /**
     * @param values
     *            the values to set
     */
    void setValues(Value[] values);

    /**
     * @return the attachments
     */
    Attachment[] getAttachments();

    /**
     * @param attachments
     *            the attachments to set
     */
    void setAttachments(Attachment[] attachments);

}