package test01;

import javax.security.auth.DestroyFailedException;

public class SinglyLinkedListImpl<T> {
    private Node<T> head;
    private Node<T> tail;

    public void add(T element) {
        Node<T> node = new Node<>();
        node.setValue(element);
        System.out.println("Adding: " + element);

        /**
         * check if the list is empty
         */
        if (head == null) {
            //since there is only one element, both head and
            //tail points to the same object.
            head = node;
            tail = node;
        } else {
            //set current tail next link to new Node
            tail.setNextRef(node);
            //set tail as newly created Node
            tail = node;
        }
    }

    public void addAfter(T element, T after) {
        Node<T> tmpNode = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes..");

        /**
         * Traverse till given element
         */
        while (tmpNode != null) {
            if (tmpNode.compareTo(after) == 0) {
                //found the target Node, add after this Node
                refNode = tmpNode;
                break;
            }

            tmpNode = tmpNode.getNextRef();
        }

        if (refNode != null) {
            //add element after the target Node
            Node<T> newNode = new Node<>();
            newNode.setValue(element);
            newNode.setNextRef(tmpNode.getNextRef());
            if (tmpNode == tail) {
                tail = newNode;
            }
            tmpNode.setNextRef(newNode);

        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void deleteFront() {
        if (head == null) {
            System.out.println("Underflow...");
        }
        Node<T> tmpNode = head;
        head = tmpNode.getNextRef();
        if (head == null) {
            tail = null;
        }

        System.out.println("Deleted: " + tmpNode.getValue());
    }

    public void deleteAfter(T after) throws DestroyFailedException {
        Node<T> tmpNode = head;
        Node<T> refNode = null;
        System.out.println("Traversing to all nodes..");

        /**
         * Traverse till given element
         */
        while (tmpNode != null) {
            if (tmpNode.compareTo(after) == 0) {
                //found the target Node, add after this Node
                refNode = tmpNode;
                break;
            }
            tmpNode = tmpNode.getNextRef();
        }
        if (refNode != null) {
            tmpNode = refNode.getNextRef();
            refNode.setNextRef(tmpNode.getNextRef());
            if (refNode.getNextRef() == null) {
                tail = refNode;
            }

            tmpNode.destroy();

            System.out.println("Deleted: " + tmpNode.getValue());
        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void traverse() {
        Node<T> tmp = head;
        while (true) {
            if (tmp == null) {
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.getNextRef();
        }
    }

    public static void main(String a[]) throws DestroyFailedException {
        SinglyLinkedListImpl<Integer> sl = new SinglyLinkedListImpl<>();
        sl.add(3);
        sl.add(32);
        sl.add(54);
        sl.add(89);
        sl.addAfter(76, 54);
        sl.deleteFront();
        sl.deleteAfter(76);
        sl.traverse();
    }
}
