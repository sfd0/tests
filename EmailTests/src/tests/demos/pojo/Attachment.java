/**
 *
 */
package tests.demos.pojo;

/**
 *
 */
public class Attachment {

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
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
