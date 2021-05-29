package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store tracker = new SqlTracker();
        tracker.init();
        Item item = new Item();
        item.setName("test1");
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplace() {
        Store tracker = new SqlTracker();
        tracker.init();
        Item bug = new Item();
        bug.setName("Bug");
        Item addedItem = tracker.add(bug);
        int id = addedItem.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store tracker = new SqlTracker();
        tracker.init();
        Item bug = new Item();
        bug.setName("Bug");
        Item addedItem = tracker.add(bug);
        int id = addedItem.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}
