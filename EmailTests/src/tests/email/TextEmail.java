package tests.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * A simple text email, no formatting.
 *
 * @author heibel
 */
public class TextEmail extends Email<SimpleEmail> {

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
     */
    public TextEmail(
                     final EmailAddress from,
                     final String subject,
                     final EmailAddress to,
                     final EmailAddress... toMore) throws EmailException {
        super(new SimpleEmail(), from, subject, to, toMore);
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

    public TextEmail(
                     final EmailAddress from,
                     final String subject,
                     final EmailAddress to,
                     final EmailAddress[] toMore,
                     final EmailAddress[] cc,
                     final EmailAddress[] bcc) throws EmailException {
        super(new SimpleEmail(), from, subject, to, toMore, cc, bcc);
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

}
