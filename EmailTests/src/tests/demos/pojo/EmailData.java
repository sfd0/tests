/**
 *
 */
package tests.demos.pojo;

import java.util.Arrays;

/**
 *
 */
public class EmailData {

    /**
     * @return the template
     */
    public Template getTemplate() {
        return template;
    }

    /**
     * @param template
     *            the template to set
     */
    public void setTemplate(final Template template) {
        this.template = template;
    }

    /**
     * @return the requesterName
     */
    public RequesterName getRequesterName() {
        return requesterName;
    }

    /**
     * @param requesterName
     *            the requesterName to set
     */
    public void setRequesterName(final RequesterName requesterName) {
        this.requesterName = requesterName;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress
     *            the fromAddress to set
     */
    public void setFromAddress(final String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return the toAddress
     */
    public String[] getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress
     *            the toAddress to set
     */
    public void setToAddress(final String[] toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the ccAddress
     */
    public String[] getCcAddress() {
        return ccAddress;
    }

    /**
     * @param ccAddress
     *            the ccAddress to set
     */
    public void setCcAddress(final String[] ccAddress) {
        this.ccAddress = ccAddress;
    }

    /**
     * @return the bccAddress
     */
    public String[] getBccAddress() {
        return bccAddress;
    }

    /**
     * @param bccAddress
     *            the bccAddress to set
     */
    public void setBccAddress(final String[] bccAddress) {
        this.bccAddress = bccAddress;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * @return the values
     */
    public Value[] getValues() {
        return values;
    }

    /**
     * @param values
     *            the values to set
     */
    public void setValues(final Value[] values) {
        this.values = values;
    }

    /**
     * @return the attachments
     */
    public Attachment[] getAttachments() {
        return attachments;
    }

    /**
     * @param attachments
     *            the attachments to set
     */
    public void setAttachments(final Attachment[] attachments) {
        this.attachments = attachments;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmailData [template="
                + template
                + ", requesterName="
                + requesterName
                + ", fromAddress="
                + fromAddress
                + ", toAddress="
                + Arrays.toString(toAddress)
                + ", ccAddress="
                + Arrays.toString(ccAddress)
                + ", bccAddress="
                + Arrays.toString(bccAddress)
                + ", subject="
                + subject
                + ", values="
                + Arrays.toString(values)
                + ", attachments="
                + Arrays.toString(attachments)
                + "]";
    }

    private Template template = null;

    private RequesterName requesterName = null;

    private String fromAddress = null;

    private String[] toAddress = null;

    private String[] ccAddress = null;

    private String[] bccAddress = null;

    private String subject = null;

    private Value[] values = null;

    private Attachment[] attachments = null;
}
