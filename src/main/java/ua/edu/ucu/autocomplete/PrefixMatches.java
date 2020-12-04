package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrii
 */
public class PrefixMatches {
    private static final int MIN_WORD = 2;
    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        if (strings == null) {
            return 0;
        }
        for (String s : strings) {
            if (s == null) {
                continue;
            }
            String[] words = s.split(" ");
            for (String word : words) {
                if (word.length() >= MIN_WORD && !trie.contains(s)) {
                    trie.add(new Tuple(word, word.length()));
                }
            }
        }
        return trie.size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        List<String> list = new ArrayList<>();
        Iterable<String> allWords = this.trie.wordsWithPrefix(pref);
        List<Integer> lengths = new ArrayList<>();
        for (String word : allWords) {
            if (!lengths.contains(word.length())) {
                if (lengths.size() == k) {
                    continue;
                }
                lengths.add(word.length());
            }
            list.add(word);
        }
        return list;
    }

    public int size() {
        return trie.size();
    }
}
