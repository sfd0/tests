/**
 *
 */
package tests.simplepojo;

/**
 *
 */
public class Template {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Template [name=" + name + "]";
    }

    private String name = null;
}
