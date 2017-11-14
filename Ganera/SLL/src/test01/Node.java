package test01;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class Node<T> implements Comparable<T>, Destroyable {

    private T value;
    private Node<T> nextRef;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNextRef() {
        return nextRef;
    }

    public void setNextRef(Node<T> nextRef) {
        this.nextRef = nextRef;
    }

    @Override
    public int compareTo(T o) {
        if (o == this.value) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void destroy() throws DestroyFailedException {
        this.value = null;
        this.nextRef = null;
    }
}
