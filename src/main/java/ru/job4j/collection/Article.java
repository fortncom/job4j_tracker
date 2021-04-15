package ru.job4j.collection;

import java.util.*;

public class Article {
    public static boolean generateBy(String origin, String line) {
        HashSet<String> words = new HashSet<>();
        String[] s = line.split(" ");
        for (String s1 : s) {
            words.add(s1);
        }
        for (String word : words) {
            if (!origin.contains(word)) {
                return false;
            }
        }
        return true;
    }
}
