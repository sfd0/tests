/**
 *
 */
package tests.demos.pojo;

/**
 *
 */
public class EmailRequest {

    /**
     * @return the email
     */
    public EmailData getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final EmailData email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmailRequest [email=" + email + "]";
    }

    private EmailData email = null;
}
