package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertArrayMatrix {

    public List<Integer> arrayMatrixToList(Integer[][] inputArray) {
        return Stream.of(inputArray)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
