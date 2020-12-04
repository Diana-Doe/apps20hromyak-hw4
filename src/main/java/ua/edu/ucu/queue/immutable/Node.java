package ua.edu.ucu.queue.immutable;

public class Node {
    private Object value;
    private Node next;

    public Node() { };

    public Node(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node n) {
        next = n;
    }
}
