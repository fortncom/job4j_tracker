package ru.job4j.collection;

import java.util.HashMap;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        HashMap<Integer, Character> hashMap = new HashMap<>();
        for (int i = 0; i < left.length(); i++) {
            hashMap.put(i, left.charAt(i));
        }
        for (int i = 0; i < right.length(); i++) {
            if (hashMap.containsValue(right.charAt(i))) {
                hashMap.values().remove(right.charAt(i));
            } else {
                return false;
            }
        }
            return true;
        }
}
