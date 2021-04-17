package ru.job4j.stream;

import org.junit.Test;
import ru.job4j.collection.LexSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortedAddressByCityTest {

    @Test
    public void sortAddress2andAddress3andAddress1() {
        Address address1 = new Address("Tver", "Pobedy", 17, 1);
        Address address2 = new Address("Kirov", "Mira", 10, 43);
        Address address3 = new Address("Korolev", "Lenina", 21, 112);
        List<Address> input = new ArrayList<>(List.of(
                address1,
                address2,
                address3
        ));
        List<Address> output = List.of(
                address2,
                address3,
                address1
        );

        input.sort(new SortedAddressByCity());
        assertThat(input, is(output));
    }
}