package tests.email.html;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

/**
 * a simple stack implementation based on <code>ArrayList</code>. Does not
 * implement the <code>Collection</code> interface but provides iterator access
 * (bottom-up). Iterators support element removal.
 *
 * @param <T>
 *            the element type.
 * @author heibel
 */
public class Stack<T> implements Iterable<T> {

    private final List<T> stackElements;

    /**
     * Creates an empty instance with a default capacity.
     */
    public Stack() {
        this.stackElements = new ArrayList<>();
    }

    /**
     * Creates an empty instance with a given initial capacity.
     * 
     * @param initialCapacity
     *            the initial capacity.
     */
    public Stack(final int initialCapacity) {
        this.stackElements = new ArrayList<>(initialCapacity);
    }

    /**
     * Creates an instance with initial values. The values are pushed upon the
     * stack in the given order.
     * 
     * @param values
     *            the values.
     */
    @SafeVarargs
    public Stack(final T... values) {
        this(values.length);
        for (final T value : values) {
            push(value);
        }
    }

    /**
     * Pushes an object on the stack.
     * 
     * @param t
     *            The object to be pushed.
     */
    public void push(final T t) {
        this.stackElements.add(t);
    }

    /**
     * Pops the top object from the stack.
     * 
     * @return Returns the former top object.
     * @throws EmptyStackException
     *             if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stackElements.remove(this.stackElements.size() - 1);
    }

    /**
     * Gets the top object without removing it.
     * 
     * @return Returns the top object.
     * @throws EmptyStackException
     *             if the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stackElements.get(this.stackElements.size() - 1);
    }

    /**
     * @return Returns <code>true</code> if and only if the stack is empty.
     */
    public boolean isEmpty() {
        return this.stackElements.isEmpty();
    }

    /**
     * Removes all elements from the stack.
     */
    public void clear() {
        this.stackElements.clear();
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return this.stackElements.iterator();
    }

    /**
     * @return Returns the current size of the stack.
     */
    public int size() {
        return this.stackElements.size();
    }

}
