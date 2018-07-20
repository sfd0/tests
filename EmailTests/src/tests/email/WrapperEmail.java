package tests.email;

import org.apache.commons.mail.EmailException;

/**
 * @param <T>
 *            --
 */
public class WrapperEmail<T extends org.apache.commons.mail.Email> extends Email<T> {

    /**
     * @param email
     *            --
     */
    public WrapperEmail(final T email) {
        super(email);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.spr.flux.common.util.email.Email#getEmailToBeSent()
     */
    @Override
    public org.apache.commons.mail.Email getEmailToBeSent() throws EmailException {
        return getEmail();
    }
}
