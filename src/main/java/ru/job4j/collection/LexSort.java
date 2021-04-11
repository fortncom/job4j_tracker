package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftNum = left.split(" ")[0].split("\\.");
        String[] rightNum = right.split(" ")[0].split("\\.");
        for (int i = 0; i < Math.min(leftNum.length, rightNum.length); i++) {
            int rsl = Integer.compare(Integer.parseInt(leftNum[i]),
            Integer.parseInt(rightNum[i]));
            if (rsl != 0) {
                return rsl;
            }
        }
        return Integer.compare(leftNum.length, rightNum.length);
    }
}
