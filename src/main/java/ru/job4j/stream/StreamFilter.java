package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(
                -24,
                46,
                32,
                35,
                -87
        ));
        List<Integer> rsl = numbers.stream()
                .filter(integer -> integer > 0)
                .collect(Collectors.toList());
        System.out.println(rsl);
    }
}
