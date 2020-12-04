package ua.edu.ucu.tries;


import ua.edu.ucu.queue.Queue;

import java.util.Arrays;

public class RWayTrie implements Trie {
    private static final int R = 26;
    private static final int MIN_WORD = 2;
    private int size;
    public Node root;

    private class Node {
        private char value = '\0';
        private int flag = -1;
        private Node[] next = new Node[R];
        private Node parent = null;

        private int numChildren() {
            int counter = 0;
            for (Node node : next) {
                if (node != null) {
                    counter++;
                }
            }
            return counter;
        }

        private void setValue(char value) {
            this.value = value;
        }

        private void setFlag(int flag) {
            this.flag = flag;
        }
    }

    public String get() {
        Node cur = root;
        String st = "";
        while (cur.flag == -1) {
            for (Node node : cur.next) {
                if (node != null) {
                    if (node.value != '\0') {
                        st += node.value;
                        cur = node;
                        break;
                    }
                }
            }
        }
        return st;
    }

    public RWayTrie() {
        root = new Node();
    }

    @Override
    public void add(Tuple t) {
        if (t.term == null || t.term.isEmpty() || t.term.length() < MIN_WORD) {
            return;
        }
        Node cur = root;
        Node prev;
        int num;
        for (char c : t.term.toCharArray()) {
            num = (int) c - (int) 'a';
            if (cur.next[num] == null) {
                cur.next[num] = new Node();
            }
            cur.next[num].setValue(c);
            prev = cur;
            cur = cur.next[num];
            cur.parent = prev;
        }
        cur.setFlag(t.weight);
        size++;
    }

    @Override
    public boolean contains(String word) {
        if (word == null || word.isEmpty() || word.length() < MIN_WORD) {
            return false;
        }
        Node cur = root;
        int num = 0;
        for (char c : word.toCharArray()) {
            num = (int) c - (int) 'a';
            if (cur.next[num] == null) {
                return false;
            }
            cur = cur.next[num];
        }
        if (cur.flag != -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String word) {
        if (!this.contains(word)) {
            return false;
        }
        Node cur = root;
        int num;
        for (char c : word.toCharArray()) {
            num = (int) c - (int) 'a';
            cur = cur.next[num];
        }
        while (cur.parent != null) {
            /* if letter that goes before current is in some another word
               it will have more than 1 children (current letter and letter
               from another word. So we should delete only current letter and stop */
            if (cur.parent.numChildren() > 1) {
                num = (int) cur.value - (int) 'a';
                cur = cur.parent;
                cur.next[num] = null;
                break;
            }
            cur = cur.parent;
            cur.next = new Node[R];
        }
        size--;
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue queue = new Queue();
        if (s == null) {
            return null;
        }

        Node cur = root;
        int num;

        for (char c : s.toCharArray()) {
            num = (int) c - (int) 'a';
            if (cur.next[num] == null) {
                return null;
            }
            cur = cur.next[num];
        }
        if (s.isEmpty()) {
            this.collect(queue, cur, s);
        } else {
            this.collect(queue, cur, s.substring(0, s.length() - 1));
        }
        return queue;
    }

    public void collect(Queue q, Node cur, String s) {
        if (cur.numChildren() == 0) {
            q.enqueue(s + cur.value);
        } else {
            if (cur.flag != -1) {
                q.enqueue(s + cur.value);
            }
            for (int i = 0; i < R; i++) {
                if (cur.next[i] != null) {
                    collect(q, cur.next[i], s + cur.value);
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

}
