/**
 *
 */
package tests.demos.annotatedpojo;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 *
 */
public class Attachment {

    /**
     * @return the url
     */
    public String getUrlX() {
        return url;
    }

    /**
     * @param urlPhooey
     *            the url to set
     */
    @JsonSetter("url")
    public void setUrlX(final String urlPhooey) {
        this.url = urlPhooey;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Attachment [url=" + url + "]";
    }

    private String url = null;
}
