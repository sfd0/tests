/**
 *
 */
package tests.demos.annotatedpojo;

import com.fasterxml.jackson.annotation.JsonSetter;

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
     * @param namePhooey
     *            the name to set
     */
    @JsonSetter("name")
    public void setNameX(final String namePhooey) {
        this.name = namePhooey;
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
