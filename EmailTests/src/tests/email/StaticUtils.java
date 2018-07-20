package tests.email;

/*
 * Created on Jun 22, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.lang.reflect.Array;

/**
 * @author sfd0 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class StaticUtils {

    /**
     *
     */
    private StaticUtils() {
        //
    }

    /**
     * Slice an array from the provided index. Uses arrayCopy, so no deep copy.
     *
     * @param <T>
     *            -- Array type.
     * @param src
     *            -- Source array.
     * @param fromIdx
     *            --Start index in source array.
     * @return -- New array.
     */
    public static <T> T[] slice(final T[] src, final int fromIdx) {
        return slice(src, fromIdx, src.length - fromIdx);
    }

    /**
     * Slice an array. Uses arrayCopy, so no deep copy.
     *
     * @param <T>
     *            -- Array type.
     * @param src
     *            -- Source array.
     * @param fromIdx
     *            --Start index in source array.
     * @param count
     *            -- Number of entries to copy.
     * @return -- New array.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] slice(final T[] src, final int fromIdx, final int count) {
        if (null == src || src.length < (count + fromIdx)) {
            throw new IllegalArgumentException(
                    "src array must be non-null and have at least count  + fromIdx entries");
        }
        final Class<?> clazz = src[0].getClass();
        final T[] arr = (T[]) Array.newInstance(clazz, count);
        System.arraycopy(src, fromIdx, arr, 0, count);
        return arr;
    }
}