package tests.edflux.email.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

import tests.demos.annotatedpojo.EmailData;
import tests.edflux.email.EmailAddress;
import tests.edflux.email.HTMLEmail;
import tests.edflux.email.SMTPClient;

public class EmailProcessor {

    private EmailData emailData;

    private String emailBody;

    /**
     * @param emailData
     *            the email header into.
     * @param emailBody
     *            the body.
     */
    public EmailProcessor(final EmailData emailData, final String emailBody) throws Exception {
        this.emailBody = emailBody;
        this.emailData = emailData;
    }

    public void processEmail() {
        try {
            final HTMLEmail email = getMailToBeSent();
            if (getEmailData().getAttachments().length != 0) {
                attachFile(email);
            }
            sendMail(email);
        } catch (final Exception e) {
            // LOGGER.error("Error while sending email", e);
        }
    }

    /**
     * Attach file to mail
     *
     * @param email
     */
    private void attachFile(final HTMLEmail email) {
        // try {
        // for (File attachmetFile : getEmailData().getAttachments()) {
        // email.attach(attachmetFile, "Mail attachment");
        // }
        // } catch (final EmailException e) {
        // LOGGER.error("Error adding attachment.", e);
        // }
    }

    /**
     * Get Mail to be Sent
     *
     * @param mailContent
     * @return HTMLEmail
     * @throws DfException
     * @throws EmailException
     */
    private HTMLEmail getMailToBeSent() throws Exception {
        final ClassLoader oldContextClassLoader = Thread.currentThread().getContextClassLoader();
        final HTMLEmail email = getEmailDetails();
        try {
            final ClassLoader classClassLoader = this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader(classClassLoader);
            email.getHTMLWriter().unescapedText(getEmailBody());
            email.getHTMLWriter().close();
        } catch (final Exception e) {
            System.out.println("EmailProcessor.getMailToBeSent() ERROR : " + e);
        } finally {
            Thread.currentThread().setContextClassLoader(oldContextClassLoader);
        }
        return email;
    }

    /**
     * Send mail
     *
     * @param email
     */
    private void sendMail(final HTMLEmail email) {
        try {
            new SMTPClient().send(email);
        } catch (final Exception e) {
            System.out.println("EmailProcessor.sendMail() Error sending alert mail" + e);
        }
    }

    /**
     * Get email details
     *
     * @return HTMLEmail
     * @throws DfException
     * @throws EmailException
     */
    private HTMLEmail getEmailDetails() throws Exception {
        final String mailSubject = getEmailData().getSubject();
        final List<EmailAddress> addresses = getReceipients(getEmailData().getToAddress());
        final List<EmailAddress> addressesInCC = getReceipients(getEmailData().getCcAddress());
        final List<EmailAddress> addressesInBCC = getReceipients(getEmailData().getBccAddress());

        return new HTMLEmail(
                new EmailAddress(getEmailData().getFromAddress()),
                mailSubject,
                addresses.remove(0),
                addresses.toArray(new EmailAddress[addresses.size()]),
                addressesInCC.toArray(new EmailAddress[addressesInCC.size()]),
                addressesInBCC.toArray(new EmailAddress[addressesInBCC.size()]));

    }

    /**
     * Converts recipients to List<EmailAddress>.
     *
     * @param recipients
     * @return
     * @throws DfException
     */
    private List<EmailAddress> getReceipients(final String[] recipients) {
        final List<EmailAddress> recipientList = new ArrayList<>();
        for (int i = 0; i <= recipients.length - 1; i++) {
            recipientList.add(new EmailAddress(recipients[i]));
        }
        return recipientList;
    }

    public EmailData getEmailData() {
        return emailData;
    }

    public void setEmailData(final EmailData emailData) {
        this.emailData = emailData;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(final String emailBody) {
        this.emailBody = emailBody;
    }

}
