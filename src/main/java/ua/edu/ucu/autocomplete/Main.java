package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Tuple;

public class Main {
    public static void main(String[] args) {
        PrefixMatches pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

//        for (String i : result) {
//            System.out.println(i);
//        }
//
//
//        RWayTrie t = new RWayTrie();
//        Tuple s = new Tuple("abc", 3);
//        Tuple p = new Tuple("abce", 3);
//        Tuple a = new Tuple("abcd", 3);
//        Tuple c = new Tuple("abcde", 6);
//        Tuple k = new Tuple("abcdef", 7);
//        t.add(s);
//        t.add(a);
//        t.add(p);
//        t.add(c);
//        t.add(k);
//        for (String i : t.words()) {
//            System.out.println(i);
//        }
    }
}
