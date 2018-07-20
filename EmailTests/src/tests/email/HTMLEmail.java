package tests.email;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import tests.email.html.HTMLStreamWriter;

/**
 * A HTML formatted email.
 *
 * @author heibel
 */
public class HTMLEmail extends Email<HtmlEmail> {

    private final StringWriter messageBuffer = new StringWriter();

    private final HTMLStreamWriter html = new HTMLStreamWriter(this.messageBuffer);

    /**
     * Constructor.
     *
     * @param from
     *            the sender.
     * @param subject
     *            the subject.
     * @param to
     *            the first recipient (mandatory).
     * @param toMore
     *            additional recipients (optional).
     */
    public HTMLEmail(final EmailAddress from, final String subject, final EmailAddress to,
                     final EmailAddress... toMore) throws EmailException {
        super(new HtmlEmail(), from, subject, to, toMore);
    }

    /**
     * Constructor.
     *
     * @param from
     *            the sender address.
     * @param subject
     *            the subject.
     * @param to
     *            the first recipient.
     * @param toMore
     *            more recipients (optional).
     * @param cc
     *            additional recipients cc (optional).
     * @param bcc
     *            additional recipients bcc (optional).
     */

    public HTMLEmail(final EmailAddress from, final String subject, final EmailAddress to,
                     final EmailAddress[] toMore, final EmailAddress[] cc,
                     final EmailAddress[] bcc) throws EmailException {
        super(new HtmlEmail(), from, subject, to, toMore, cc, bcc);
    }

    public HTMLEmail(final EmailAddress from, final String subject, final EmailAddress to,
                     final EmailAddress[] toMore, final EmailAddress[] cc) throws EmailException {
        super(new HtmlEmail(), from, subject, to, toMore, cc);
    }

    /**
     * @return Returns a {@link HTMLStreamWriter} object to write the message.
     */
    public HTMLStreamWriter getHTMLWriter() {
        return this.html;
    }

    /** {@inheritDoc} */
    @Override
    public org.apache.commons.mail.Email getEmailToBeSent() throws EmailException {
        final org.apache.commons.mail.HtmlEmail wrappedEmail = getEmail();

        try {
            this.html.close();
        } catch (final IOException exc) {
            throw new EmailException(exc);
        }
        wrappedEmail.setCharset(StandardCharsets.UTF_8.name());
        wrappedEmail.setHtmlMsg(this.messageBuffer.toString());
        return wrappedEmail;
    }

    /**
     * attaches the file to HTML mail can be called multiple times to attach
     * more than one items.
     *
     * @param fileAttachment
     *            file to be attached.
     * @param description
     *            Description about file.
     * @throws EmailException
     */
    public void attach(final File fileAttachment, final String description) throws EmailException {
        final EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(fileAttachment.getAbsolutePath());
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(description);
        attachment.setName(fileAttachment.getName());
        getEmail().attach(attachment);
    }

}
