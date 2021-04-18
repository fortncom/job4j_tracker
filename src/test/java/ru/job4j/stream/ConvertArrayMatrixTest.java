package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertArrayMatrixTest {

    @Test
    public void convertToList() {
        Integer[][] input = new Integer[][] {
                new Integer[] {1, 1, 2 },
                new Integer[] {3, 41, 52 },
                new Integer[] {61, 71, 82 }
        };

        List<Integer> output = List.of(
                1, 1, 2, 3, 41, 52, 61, 71, 82
        );
        ConvertArrayMatrix arrayMatrix = new ConvertArrayMatrix();
        List<Integer> rsl = arrayMatrix.arrayMatrixToList(input);
        assertThat(output, is(rsl));
    }

}
