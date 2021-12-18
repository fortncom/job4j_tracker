package ru.job4j.tracker;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Fix PC", "1"}
        );
        Store tracker = new MemTracker();
        tracker.init();
        List<UserAction> actions = new ArrayList<>();
                actions.addAll(List.of(
                new CreateAction(new StubOutput()),
                new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        List<Item> items = new ArrayList<>();
        tracker.findAll(items::add);
        assertThat(items.get(0).getName(), is("Fix PC"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.init();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new ReplaceAction(out), new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.init();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new DeleteAction(out),
                new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), Matchers.is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Store tracker = new MemTracker();
        tracker.init();
        List<UserAction> actions = new ArrayList<>(
                List.of(new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                       + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindAll() {
        Store tracker = new MemTracker();
        tracker.init();
        Item item = tracker.add(new Item("First item"));
        Item item2 = tracker.add(new Item("Second item"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        Output out = new StubOutput();

        List<UserAction> actions = new ArrayList<>(
                List.of(new FindAllAction(out),
                new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Show all items ====" + System.lineSeparator()
                        + item + System.lineSeparator()
                        + item2 + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindById() {
        Store tracker = new MemTracker();
        tracker.init();
        Item item = tracker.add(new Item("new item"));
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        Output out = new StubOutput();

        List<UserAction> actions = new ArrayList<>(
                List.of(new FindByIdAction(out),
                new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find item by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find item by id ====" + System.lineSeparator()
                        + item + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find item by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindByName() {
        Store tracker = new MemTracker();
        tracker.init();
        Item item = tracker.add(new Item("new item"));
        Input in = new StubInput(
                new String[] {"0", "new item", "1"}
        );
        Output out = new StubOutput();

        List<UserAction> actions = new ArrayList<>(
                List.of(new FindByNameAction(out),
                new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find items by name ====" + System.lineSeparator()
                        + item + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"10", "0"}
        );
        Store tracker = new MemTracker();
        tracker.init();
        List<UserAction> actions = new ArrayList<>(
                List.of(new ExitAction(out)));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), Matchers.is(
                "Menu." + ln
                        + "0. Exit" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit" + ln
                )
        );
    }
}
