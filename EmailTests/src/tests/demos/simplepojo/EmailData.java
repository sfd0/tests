/**
 *
 */
package tests.demos.simplepojo;

import java.util.Arrays;

/**
 *
 */
public class EmailData implements IEmailData {

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getTemplate()
     */
    @Override
    public Template getTemplate() {
        return template;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setTemplate(tests.simplepojo.Template)
     */
    @Override
    public void setTemplate(final Template template) {
        this.template = template;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getRequesterName()
     */
    @Override
    public RequesterName getRequesterName() {
        return requesterName;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setRequesterName(tests.simplepojo.RequesterName)
     */
    @Override
    public void setRequesterName(final RequesterName requesterName) {
        this.requesterName = requesterName;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getFromAddress()
     */
    @Override
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setFromAddress(java.lang.String)
     */
    @Override
    public void setFromAddress(final String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getToAddress()
     */
    @Override
    public String[] getToAddress() {
        return toAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setToAddress(java.lang.String[])
     */
    @Override
    public void setToAddress(final String[] toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getCcAddress()
     */
    @Override
    public String[] getCcAddress() {
        return ccAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setCcAddress(java.lang.String[])
     */
    @Override
    public void setCcAddress(final String[] ccAddress) {
        this.ccAddress = ccAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getBccAddress()
     */
    @Override
    public String[] getBccAddress() {
        return bccAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setBccAddress(java.lang.String[])
     */
    @Override
    public void setBccAddress(final String[] bccAddress) {
        this.bccAddress = bccAddress;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getSubject()
     */
    @Override
    public String getSubject() {
        return subject;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setSubject(java.lang.String)
     */
    @Override
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getValues()
     */
    @Override
    public Value[] getValues() {
        return values;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setValues(tests.simplepojo.Value[])
     */
    @Override
    public void setValues(final Value[] values) {
        this.values = values;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#getAttachments()
     */
    @Override
    public Attachment[] getAttachments() {
        return attachments;
    }

    /**
     * {@inheritDoc}
     *
     * @see tests.simplepojo.IEmailData#setAttachments(tests.simplepojo.Attachment[])
     */
    @Override
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
