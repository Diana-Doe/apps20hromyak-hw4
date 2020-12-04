package ua.edu.ucu.queue;

import ua.edu.ucu.queue.immutable.ImmutableLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Queue implements Iterable<String> {
    private ImmutableLinkedList queue;

    public Queue() {
        queue = new ImmutableLinkedList();
    }

    public Object peek() {
        return queue.getFirst();
    }

    public Object dequeue() {
        Object first = peek();
        queue = queue.removeFirst();
        return first;
    }

    public void enqueue(Object e) {
        queue = queue.addLast(e);
    }

    @Override
    public Iterator<String> iterator() {
        Iterator<String> it = new Iterator<String>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < queue.size() && queue.toArray()[currentIndex] != null;
            }

            @Override
            public String next() {
                return (String) queue.toArray()[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
