package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HbmTrackerTest {

    private HbmTracker tracker;

    @Before
    public void setUp() throws SQLException {
        tracker = new HbmTracker();
        tracker.init();
    }

    @Test
    public void whenAdd() {
        Item rsl = tracker.add(new Item("test"));
        assertThat(tracker.findByName("test").get(0), is(rsl));
    }

    @Test
    public void whenReplace() {
        Item item = tracker.add(new Item("test"));
        item.setName("test 1");
        assertThat(tracker.replace(1, item), is(Boolean.TRUE));
        assertThat(tracker.findById(1), is(item));
    }

    @Test
    public void whenDelete() {
        Item item = tracker.add(new Item("test"));
        assertThat(tracker.delete(1), is(Boolean.TRUE));
        assertThat(tracker.findById(1) == null, is(Boolean.TRUE));
    }

    @Test
    public void whenFindAll() {
        Item one = tracker.add(new Item("test 1"));
        Item two = tracker.add(new Item("test 2"));
        assertThat(tracker.findAll(), is(List.of(one, two)));
    }

    @Test
    public void whenFindById() {
        Item item = tracker.add(new Item("test"));
        assertThat(tracker.findById(1), is(item));
    }

    @Test
    public void whenFindByName() {
        Item item = tracker.add(new Item("test"));
        assertThat(tracker.findByName("test"), is(List.of(item)));
    }
}