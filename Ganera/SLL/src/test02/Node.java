package test02;

public class Node {
    private int data;
    private Node nextNode = null;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        if (this == nextNode)
            throw new IllegalArgumentException();

        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return String.format("%d", this.data);
    }
}
