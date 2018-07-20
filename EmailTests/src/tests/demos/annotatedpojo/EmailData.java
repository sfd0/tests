/**
 *
 */
package tests.demos.annotatedpojo;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonSetter;

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
     * @param templatePhooey
     *            the template to set
     */
    @JsonSetter("template")
    public void setTemplateX(final Template templatePhooey) {
        this.template = templatePhooey;
    }

    /**
     * @return the requesterName
     */
    public RequesterName getRequesterName() {
        return requesterName;
    }

    /**
     * @param requesterNamePhooey
     *            the requesterName to set
     */
    @JsonSetter("requesterName")
    public void setRequesterNameX(final RequesterName requesterNamePhooey) {
        this.requesterName = requesterNamePhooey;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddressPhooey
     *            the fromAddress to set
     */
    @JsonSetter("fromAddress")
    public void setFromAddressX(final String fromAddressPhooey) {
        this.fromAddress = fromAddressPhooey;
    }

    /**
     * @return the toAddress
     */
    public String[] getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddressPhooey
     *            the toAddress to set
     */
    @JsonSetter("toAddress")
    public void setToAddressX(final String[] toAddressPhooey) {
        this.toAddress = toAddressPhooey;
    }

    /**
     * @return the ccAddress
     */
    public String[] getCcAddress() {
        return ccAddress;
    }

    /**
     * @param ccAddressPhooey
     *            the ccAddress to set
     */
    @JsonSetter("ccAddress")
    public void setCcAddressX(final String[] ccAddressPhooey) {
        this.ccAddress = ccAddressPhooey;
    }

    /**
     * @return the bccAddress
     */
    public String[] getBccAddress() {
        return bccAddress;
    }

    /**
     * @param bccAddressPhooey
     *            the bccAddress to set
     */
    @JsonSetter("bccAddress")
    public void setBccAddressX(final String[] bccAddressPhooey) {
        this.bccAddress = bccAddressPhooey;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subjectPhooey
     *            the subject to set
     */
    @JsonSetter("subject")
    public void setSubjectX(final String subjectPhooey) {
        this.subject = subjectPhooey;
    }

    /**
     * @return the values
     */
    public Value[] getValues() {
        return values;
    }

    /**
     * @param valuesPhooey
     *            the values to set
     */
    @JsonSetter("values")
    public void setValuesX(final Value[] valuesPhooey) {
        this.values = valuesPhooey;
    }

    /**
     * @return the attachments
     */
    public Attachment[] getAttachments() {
        return attachments;
    }

    /**
     * @param attachmentsPhooey
     *            the attachments to set
     */
    @JsonSetter("attachments")
    public void setAttachmentsX(final Attachment[] attachmentsPhooey) {
        this.attachments = attachmentsPhooey;
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
