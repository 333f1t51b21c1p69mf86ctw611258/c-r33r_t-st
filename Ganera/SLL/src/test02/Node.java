package test02;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class Node implements Destroyable {
    private int value;
    private Node nextRef = null;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextRef() {
        return nextRef;
    }

    public void setNextRef(Node nextRef) {
        if (this == nextRef)
            throw new IllegalArgumentException();

        this.nextRef = nextRef;
    }

    @Override
    public void destroy() throws DestroyFailedException {
        this.nextRef = null;
    }

    @Override
    public String toString() {
        return String.format("%d", this.value);
    }
}
