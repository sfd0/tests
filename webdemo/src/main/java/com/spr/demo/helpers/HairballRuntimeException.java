/**
 *
 */
package com.spr.demo.helpers;

/**
 *
 */
@SuppressWarnings("serial")
public class HairballRuntimeException extends RuntimeException {

    /**
     * @param message --
     */
    public HairballRuntimeException(final String message) {
        super(message);
    }

    /**
     * @param cause --
     */
    public HairballRuntimeException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message --
     * @param cause --
     */
    public HairballRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
