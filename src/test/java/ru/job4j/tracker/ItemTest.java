package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ItemTest {

    @Test
    public void whenSortItemByName() {
        Item item = new Item(1, "firstItem");
        Item item2 = new Item(3, "bug");
        Item item3 = new Item(5, "test1");
        List<Item> result = Arrays.asList(item, item2, item3);
        result.sort(new SortByNameItem());
        List<Item> expected = Arrays.asList(item2, item, item3);
        assertThat(result, is(expected));
    }

    @Test
    public void whenReverseSortItemById() {
        Item item = new Item(1, "firstItem");
        Item item2 = new Item(3, "bug");
        Item item3 = new Item(5, "test1");
        List<Item> result = Arrays.asList(item, item2, item3);
        result.sort(new SortByIdReversItem());
        List<Item> expected = Arrays.asList(item3, item2, item);
        assertThat(result, is(expected));
    }

}