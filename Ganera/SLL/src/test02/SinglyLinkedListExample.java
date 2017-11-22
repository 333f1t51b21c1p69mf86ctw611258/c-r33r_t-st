package test02;

import java.util.Random;

public class SinglyLinkedListExample {
    private Node head = null;

    public Node getHead() {
        return head;
    }

    public Node add(int value) {
        Node newNode = new Node();
        newNode.setData(value);

        if (head == null) {
            head = newNode;
        } else {
            Node tail = getTail();

            tail.setNextNode(newNode);
        }

        return newNode;
    }

    private Node getTail() {
        Node tmpNode = head;
        while (tmpNode.getNextNode() != null) {
            tmpNode = tmpNode.getNextNode();
        }
        return tmpNode;
    }

    public void traverse() {
        Node tmpNode = head;

        while (tmpNode != null) {
            System.out.print(String.format("%5d", tmpNode.getData()));
            tmpNode = tmpNode.getNextNode();
        }
        System.out.println();
    }

    public Node findBefore(Node node) {
        Node tmpNode = head;

        while (tmpNode != null) {
            if (tmpNode.getNextNode() == node) {
                return tmpNode;
            }

            tmpNode = tmpNode.getNextNode();
        }

        return null;
    }

    public void swap2Nodes(Node iNode, Node jNode) {
        Node iBeforeNode = findBefore(iNode);
        Node jBeforeNode = findBefore(jNode);

        Node iAfterNode = iNode.getNextNode();
        Node jAfterNode = jNode.getNextNode();

        if (iBeforeNode != null) {
            iBeforeNode.setNextNode(jNode);
        }
        if (iAfterNode != jNode) {
            jNode.setNextNode(iAfterNode);
        } else { // Exception when swapping two items stand next to each other
            jNode.setNextNode(iNode);
        }

        if (jBeforeNode != iNode) { // Exception when swapping two items stand next to each other
            jBeforeNode.setNextNode(iNode);
        }
        iNode.setNextNode(jAfterNode);

        if (iNode == head) {
            head = jNode;
        }
    }

    public void sortAscending() {
        Node iNode = head, jNode, tmpNode;

        while (iNode != null) {
            jNode = iNode.getNextNode();

            while (jNode != null) {
                if (iNode.getData() > jNode.getData()) {
                    swap2Nodes(iNode, jNode);

                    tmpNode = iNode;
                    iNode = jNode;
                    jNode = tmpNode;
                }

                jNode = jNode.getNextNode();
            }

            iNode = iNode.getNextNode();
        }
    }

    public void sortAscendingInsertion() {
        Node tmpNode;
        int length = getListLength();

        for (int i = 1; i < length; i++) {
            tmpNode = getNodeByPosition(i);
            int j = i;

            while (j > 0 && getNodeByPosition(j - 1).getData() > tmpNode.getData()) {
                swap2Nodes(getNodeByPosition(j - 1), getNodeByPosition(j));
                j--;
            }
        }
    }

    public int getListLength() {
        Node tmpNode = head;
        int i = 0;
        while (tmpNode != null) {
            i++;
            tmpNode = tmpNode.getNextNode();
        }

        return i;
    }

    public Node getNodeByPosition(int position) {
        Node tmpNode = head;
        int tmpIndex = 0;

        while (tmpNode != null) {
            if (tmpIndex == position) {
                return tmpNode;
            }

            tmpNode = tmpNode.getNextNode();
            tmpIndex++;
        }

        return null;
    }

    public int getPositionByNode(Node node) {
        Node tmpNode = head;
        int position = 0;

        while (tmpNode != null) {
            if (tmpNode == node) {
                return position;
            }

            position++;
            tmpNode = tmpNode.getNextNode();
        }

        throw new IllegalArgumentException();
    }

    public void removeNode(Node node) {
        if (node == head) {
            head = node.getNextNode();
        } else {
            Node tmpNode = head.getNextNode();
            Node previousNode = head;

            while (tmpNode != null) {
                if (node == tmpNode) {
                    previousNode.setNextNode(node.getNextNode());
                    break;
                }
            }
        }
    }

    public void removeDuplicatedNode() {
        if (head.getNextNode() != null) {
            Node tmpNode = head.getNextNode();
            int previousValue = head.getData();
            Node previousNode = head;

            while (tmpNode != null) {
                if (tmpNode.getData() == previousValue) {
                    previousNode.setNextNode(tmpNode.getNextNode());
                } else {
                    previousNode = tmpNode;
                    previousValue = tmpNode.getData();
                }

                tmpNode = tmpNode.getNextNode();
            }
        }

    }

    public void showNodeBefore() {
        Node tmpNode = head;

        while (tmpNode != null) {
            System.out.println(findBefore(tmpNode));

            tmpNode = tmpNode.getNextNode();
        }
    }

    public void reverse() {
        Node currentNode = head;

        Node newNextRef = null;

        Node newPreviousNode = null;
        while (currentNode != null) {
            head = currentNode;

            newPreviousNode = currentNode.getNextNode();
            currentNode.setNextNode(newNextRef);

            newNextRef = currentNode;

            currentNode = newPreviousNode;
        }
    }

    private int getRandomNumber() {
        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        return n;
    }

    public void disorderList() {
        Node iNode = head;
        Node jNode;
        Node tmpNode;

        while (iNode != null) {
            jNode = iNode.getNextNode();

            while (jNode != null) {
                if (getRandomNumber() % 2 == 0) {
                    swap2Nodes(iNode, jNode);

                    tmpNode = iNode;
                    iNode = jNode;
                    jNode = tmpNode;
                }

                jNode = jNode.getNextNode();
            }

            iNode = iNode.getNextNode();
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
        example.add(2);
        example.add(5);
        example.add(1);
        example.add(99);

        example.traverse();

        example.swap2Nodes(node1, node2);

        System.out.println("After swapping two nodes:");
        example.traverse();

        System.out.println(example.getHead());

//        example.showNodeBefore();

        example.sortAscending();
        System.out.println("After sorting:");
        example.traverse();

        example.removeDuplicatedNode();
        System.out.println("After removing duplicated nodes:");
        example.traverse();

        example.reverse();
        System.out.println("After reversing:");
        example.traverse();

        System.out.println(example.getHead());

        System.out.println("List length: " + example.getListLength());

        System.out.println("Item: " + example.getNodeByPosition(1));

        System.out.println("Position: " + example.getPositionByNode(node1));

        example.disorderList();
        System.out.println("After disorder the list:");
        example.traverse();

        example.sortAscendingInsertion();
        System.out.println("After sorting with Insertion algorithm:");
        example.traverse();
    }
}
