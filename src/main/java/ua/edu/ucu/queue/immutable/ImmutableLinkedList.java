package ua.edu.ucu.queue.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private final Node start;
    private final Node end;
    private int size;

    public ImmutableLinkedList() {
        start = null;
        end = null;
        size = 0;
    }

    public ImmutableLinkedList(Object el) {
        Node newEl = new Node(el);
        start = newEl;
        end = newEl;
        size = 1;
    }

    public ImmutableLinkedList(Object[] arr) {
        if (arr.length == 0) {
            start = null;
            end = null;
            size = 0;
            return;
        }
        start = new Node(arr[0]);
        Node cur = start;
        size = 1;
        for (int i = 1; i < arr.length; i++) {
            Node newEl = new Node(arr[i]);
            cur.setNext(newEl);
            cur = newEl;
            size++;
        }
        end = cur;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        if (start == null) {
            return new ImmutableLinkedList(e);
        }
        Object[] newArr = Arrays.copyOf(toArray(), size + 1);
        newArr[size] = e;
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = toArray();
        Object[] newArr = Arrays.copyOf(arr, arr.length + 1);
        for (int i = 0; i < newArr.length; i++) {
            if (index > i) {
                newArr[i] = arr[i];
            } else if (index == i) {
                newArr[i] = e;
            } else {
                newArr[i] = arr[i - 1];
            }
        }
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        if (start == null) {
            return new ImmutableLinkedList(c);
        }
        Object[] newArr = new Object[size + c.length];
        Object[] arr = toArray();
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        System.arraycopy(c, 0, newArr, arr.length, c.length);
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        Object[] arr = toArray();
        Object[] newArr = new Object[arr.length + c.length];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(c, 0, newArr, index, c.length);
        System.arraycopy(arr, index, newArr,
                index + c.length, arr.length - index);
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public Object get(int index) {
        Object[] arr = toArray();
        return arr[index];
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        if (size == 1 && index == 0) {
            return new ImmutableLinkedList();
        }
        if (index < 0 || index > size || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = toArray();
        Object[] newArr = new Object[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index,
                arr.length - index - 1);
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        Object[] newArr = toArray();
        newArr[index] = e;
        return new ImmutableLinkedList(newArr);
    }

    @Override
    public int indexOf(Object e) {
        if (size == 0) {
            return -1;
        }
        Node cur = start;
        for (int i = 0; i < size; i++) {
            if (e.equals(cur.getValue())) {
                return i;
            }
            cur = cur.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArr = new Object[size];
        Node cur = start;
        for (int i = 0; i < size; i++) {
            newArr[i] = cur.getValue();
            cur = cur.getNext();
        }
        return newArr;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        String str = Arrays.toString(toArray());
        return str.substring(1, str.length() - 1);
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        if (start == null) {
            throw new IndexOutOfBoundsException();
        }
        return start.getValue();
    }

    public Object getLast() {
        if (start == null) {
            throw new IndexOutOfBoundsException();
        }
        return end.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size - 1);
    }
}
