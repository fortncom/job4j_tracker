package ru.job4j.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < left.length(); i++) {
            if (hashMap.containsKey(left.charAt(i))) {
                hashMap.put(left.charAt(i), hashMap.get(left.charAt(i)) + 1);
            } else {
                hashMap.put(left.charAt(i), 1);
            }
        }

        for (int i = 0; i < right.length(); i++) {
            char character = right.charAt(i);
            if (hashMap.containsKey(character)) {
                if (hashMap.get(character) > 1) {
                    hashMap.put(character, hashMap.get(character) - 1);
                } else {
                    hashMap.put(character, 0);
                }
            } else {
                return false;
            }
        }
        hashMap.values().removeIf(i -> i == 0);
            return hashMap.isEmpty();
        }
}
