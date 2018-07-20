package tests.email.attachmentemail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import tests.email.Email;
import tests.email.EmailAddress;
import tests.email.StaticUtils;

/**
 * A simple text email, still no formatting.
 *
 * @author heibel
 */
public class AttachmentEmail extends Email<MultiPartEmail> {

    /**
     * Constructor.
     *
     * @param from
     *            the sender address.
     * @param subject
     *            the subject.
     * @param to
     *            the first recipient.
     */
    public AttachmentEmail(
                           final EmailAddress from, //
                           final String subject, //
                           final EmailAddress... to) throws EmailException {
        super(
                new MultiPartEmail(),
                from,
                subject,
                to[0],
                to.length > 1 ? StaticUtils.slice(to, 1) : null);
    }

    /**
     * Constructor.
     *
     * @param from
     *            the sender address.
     * @param subject
     *            the subject.
     * @param cc
     *            additional recipients cc (optional).
     * @param bcc
     *            additional recipients bcc (optional).
     * @param to
     *            the first recipient.
     */

    public AttachmentEmail(
                           final EmailAddress from,
                           final String subject,
                           final EmailAddress[] cc,
                           final EmailAddress[] bcc,
                           final EmailAddress... to) throws EmailException {
        super(
                new MultiPartEmail(),
                from,
                subject,
                to[0],
                to.length > 1 ? StaticUtils.slice(to, 1) : null,
                cc,
                bcc);
    }

    /**
     * Add a file as an attachment.
     *
     * @param file
     *            -- To attache.
     * @param name
     *            -- Dunno.
     * @param description
     *            -- Dunno.
     * @throws EmailException
     *             -- Dunno (sorry, tried, but the docs not so clear).
     */
    public void attachFile(
            final File file,
            final String name,
            final String description) throws EmailException {
        final String routineName = "attachFile";
        LOGGER.debug(routineName + " - Attaching: " + file.getName());
        final EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(file.getPath());
        attachment.setDescription(description);
        attachment.setName(name);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        getEmail().attach(attachment);
        LOGGER.debug(routineName + " - " + "Attached.");
    }

    /**
     * Add text from a stream as an attachment.
     *
     * @param is
     *            -- The input.
     * @param name
     *            -- Dunno.
     * @param description
     *            -- DUnno.
     * @throws EmailException
     *             -- Dunno.
     */
    public void attachStream(
            final InputStream is,
            final String name,
            final String description) throws EmailException {
        final String routineName = "attachStream";
        LOGGER.debug(routineName + " - Sending text from stream");
        getEmail().attach(getDataSource(is), name, description);
    }

    /**
     * Add text from a string as an attachment.
     *
     * @param is
     *            -- The input.
     * @param name
     *            -- Dunno.
     * @param description
     *            -- DUnno.
     * @throws EmailException
     *             -- Dunno.
     */
    public void attachString(
            final String is,
            final String name,
            final String description) throws EmailException {
        final String routineName = "attachStream";
        LOGGER.debug(routineName + " - Sending text from a string");
        getEmail().attach(getDataSource(is), name, description);
    }

    /**
     * Create a DataSource from a string.
     *
     * @param string
     *            -- Source.
     * @return -- The DataSource.
     * @throws EmailException
     *             -- We failed.
     */
    private DataSource getDataSource(final String string) throws EmailException {
        final String routineName = "getDataSource";
        try {
            return new ByteArrayDataSource(string, "text/plain");
        } catch (final IOException ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new EmailException(ex.getMessage(), ex);
        }
    }

    /**
     * Create a DataSource from a stream.
     *
     * @param istream
     *            -- Source.
     * @return -- The DataSource.
     * @throws EmailException
     *             -- We failed.
     */
    private DataSource getDataSource(final InputStream istream) throws EmailException {
        final String routineName = "getDataSource";
        try {
            return new ByteArrayDataSource(istream, "text/plain");
        } catch (final IOException ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
            throw new EmailException(ex.getMessage(), ex);
        }
    }

    /**
     * Sets the mail message.
     *
     * @param message
     *            the message.
     */
    public void setMessage(final String message) throws EmailException {
        getEmail().setMsg(message);
    }

    /** {@inheritDoc} */
    @Override
    public org.apache.commons.mail.Email getEmailToBeSent() throws EmailException {
        return getEmail();
    }

    /**
    *
    */
    private static final Logger LOGGER = Logger.getLogger(AttachmentEmail.class);
}
