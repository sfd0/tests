/**
 *
 */
package tests.demos.pojo;

/**
 *
 */
public class RequesterName {

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
        return "RequesterName [name=" + name + "]";
    }

    private String name = null;
}
