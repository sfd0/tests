package tests.edflux.email;

/**
 * An email address with an optional clear text name.
 *
 * @author heibel
 */
public class EmailAddress {

    /**
     * Creates an instance with clear text name.
     * 
     * @param email
     *            the actual email address.
     * @param name
     *            the clear text name.
     */
    public EmailAddress(final String email, final String name) {
        this.email = email;
        this.name = name;
    }

    /**
     * Creates an instance without clear text name.
     * 
     * @param email
     *            the email address.
     */
    public EmailAddress(final String email) {
        this.email = email;
        this.name = null;
    }

    /**
     * @return Returns the email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return Returns the name, <code>null</code> if there is none.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " addr: " + email;
    }

    private final String email;

    private final String name;

}
