package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftDep = left.split("/");
        String[] rightDep = right.split("/");
        int rsl = rightDep[0].compareTo(leftDep[0]);
        if (rsl == 0) {
           rsl = left.compareTo(right);
        }
        return rsl;
    }

}
