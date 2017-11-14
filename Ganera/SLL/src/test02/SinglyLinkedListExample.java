package test02;

public class SinglyLinkedListExample {
    private Node head = null;
    private Node tail = null;

    public Node getHead() {
        return head;
    }

    public Node add(int value) {
        Node newNode = new Node();
        newNode.setValue(value);

        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.setNextRef(newNode);
            tail = newNode;
        }

        return newNode;
    }

    public void traverse() {
        Node tmpNode = head;

        while (tmpNode != null) {
            System.out.print(String.format("%d ", tmpNode.getValue()));
            tmpNode = tmpNode.getNextRef();
        }
        System.out.println();
    }

    public Node findBefore(Node node) {
        Node tmpNode = head;

        while (tmpNode != null) {
            if (tmpNode.getNextRef() == node) {
                return tmpNode;
            }

            tmpNode = tmpNode.getNextRef();
        }

        return null;
    }

    public void swap2Nodes(Node iNode, Node jNode) {
        Node iBeforeNode = findBefore(iNode);
        Node jBeforeNode = findBefore(jNode);

        Node iAfterNode = iNode.getNextRef();
        Node jAfterNode = jNode.getNextRef();

        if (iBeforeNode != null) {
            iBeforeNode.setNextRef(jNode);
        }
        if (iAfterNode != jNode) {
            jNode.setNextRef(iAfterNode);
        } else { // Exception when swapping two items stand next to each other
            jNode.setNextRef(iNode);
        }

        if (jBeforeNode != iNode) { // Exception when swapping two items stand next to each other
            jBeforeNode.setNextRef(iNode);
        }
        iNode.setNextRef(jAfterNode);

        if (iNode == head) {
            head = jNode;
        }
    }

    public void sortAscending() {
        Node iNode = head, jNode, tmpNode;

        while (iNode != null) {
            jNode = iNode.getNextRef();

            while (jNode != null) {
                if (iNode.getValue() > jNode.getValue()) {
                    swap2Nodes(iNode, jNode);

                    tmpNode = iNode;
                    iNode = jNode;
                    jNode = tmpNode;
                }

                jNode = jNode.getNextRef();
            }

            iNode = iNode.getNextRef();
        }
    }

    public void showNodeBefore() {
        Node tmpNode = head;

        while (tmpNode != null) {
            System.out.println(findBefore(tmpNode));

            tmpNode = tmpNode.getNextRef();
        }
    }

    public static void main(String args[]) {
        SinglyLinkedListExample example = new SinglyLinkedListExample();
        Node node1 = example.add(10);
        Node node2 = example.add(14);
        example.add(2);
        example.add(5);
        example.add(1);
        example.add(99);

        example.traverse();

        example.swap2Nodes(node1, node2);

        example.traverse();

        System.out.println(example.getHead());

//        example.showNodeBefore();

        example.sortAscending();

        example.traverse();
    }
}
