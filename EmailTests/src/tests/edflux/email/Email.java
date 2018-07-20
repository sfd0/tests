package tests.edflux.email;

import org.apache.commons.mail.EmailException;

/**
 * Abstract email base class. A wrapper for the Apache Commons email class.
 *
 * @param <T>
 *            the wrapped type.
 * @author heibel
 */
public abstract class Email<T extends org.apache.commons.mail.Email> {

    private final T emailObject;

    /**
     * Wraps and initializes the given email object.
     * 
     * @param email
     *            the email object.
     * @param from
     *            the sender address.
     * @param subject
     *            the subject.
     * @param to
     *            the first recipient (mandatory).
     * @param toMore
     *            additional recipients (optional).
     */
    public Email(final T email, final EmailAddress from, final String subject,
                 final EmailAddress to, final EmailAddress... toMore) throws EmailException {
        this(email, from, subject, to, toMore, null, null);
    }

    public Email(final T email, final EmailAddress from, final String subject,
                 final EmailAddress to, final EmailAddress[] toMore,
                 final EmailAddress[] cc) throws EmailException {
        this(email, from, subject, to, toMore, cc, null);
    }

    /**
     * Wraps and initializes the given email object.
     * 
     * @param email
     *            the email object.
     * @param from
     *            the sender address.
     * @param subject
     *            the subject.
     * @param to
     *            the first recipient (mandatory).
     * @param toMore
     *            additional recipients (optional).
     * @param cc
     *            additional recipients cc (optional).
     * @param bcc
     *            additional recipients bcc (optional).
     * @throws EmailException
     */
    public Email(final T email, final EmailAddress from, final String subject,
                 final EmailAddress to, final EmailAddress[] toMore, final EmailAddress[] cc,
                 final EmailAddress[] bcc) throws EmailException {
        this.emailObject = email;
        if (from.getName() != null) {
            this.emailObject.setFrom(from.getEmail(), from.getName());
        } else {
            this.emailObject.setFrom(from.getEmail());
        }
        this.emailObject.setSubject(subject);
        addTo(to);
        if (toMore != null) {
            for (final EmailAddress nextTo : toMore) {
                addTo(nextTo);
            }
        }
        if (cc != null) {
            for (final EmailAddress nextCc : cc) {
                addCc(nextCc);
            }
        }
        if (bcc != null) {
            for (final EmailAddress nextBcc : bcc) {
                addBcc(nextBcc);
            }
        }
    }

    /**
     * Gets the wrapped email object ready to be sent. It has all required data
     * except SMTP server information.
     * 
     * @return Returns the email object ready to be sent.
     */
    public abstract org.apache.commons.mail.Email getEmailToBeSent() throws EmailException;

    /**
     * @return Returns the wrapped email object.
     */
    protected final T getEmail() {
        return this.emailObject;
    }

    /**
     * Adds a recipient to an email.
     * 
     * @param to
     *            the recipient.
     */
    private void addTo(final EmailAddress to) throws EmailException {
        if (to.getName() != null) {
            this.emailObject.addTo(to.getEmail(), to.getName());
        } else {
            this.emailObject.addTo(to.getEmail());
        }
    }

    /**
     * Adds a recipient as cc an email.
     * 
     * @param cc
     *            the recipient.
     */
    private void addCc(final EmailAddress cc) throws EmailException {
        if (cc.getName() != null) {
            this.emailObject.addCc(cc.getEmail(), cc.getName());
        } else {
            this.emailObject.addCc(cc.getEmail());
        }
    }

    /**
     * Adds a recipient as cc an email.
     * 
     * @param bcc
     *            the recipient.
     */
    private void addBcc(final EmailAddress bcc) throws EmailException {
        if (bcc.getName() != null) {
            this.emailObject.addBcc(bcc.getEmail(), bcc.getName());
        } else {
            this.emailObject.addBcc(bcc.getEmail());
        }
    }

}
