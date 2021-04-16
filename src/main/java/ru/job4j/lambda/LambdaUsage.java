package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaUsage {

    public static void main(String[] args) {
        String[] numbers = {
                "1. T",
                "2. Task",
                "11. Task"
        };
        Comparator<String> cmpDescSize  = (left, right) ->
                right.length() - left.length();
        Arrays.sort(numbers, cmpDescSize);
        System.out.println(Arrays.asList(numbers));
    }
}
