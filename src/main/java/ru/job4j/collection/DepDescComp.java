package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftDep = left.split("/");
        String[] rightDep = right.split("/");
        int rsl = rightDep[0].compareTo(leftDep[0]);
        if (rsl == 0) {
            for (int i = 1; i < Math.min(leftDep.length, rightDep.length); i++) {
                rsl = leftDep[i].compareTo(rightDep[i]);
                if (rsl != 0) {
                    break;
                }
            }
            if (leftDep.length == 1 || rightDep.length == 1) {
                rsl = Integer.compare(leftDep.length, rightDep.length);
            }
        }
        return rsl;
    }

}
