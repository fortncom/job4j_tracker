package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    private final BasicDataSource pool = new BasicDataSource();
    private SqlTracker store;

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.h2.Driver");
        pool.setUrl("jdbc:h2:mem:testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;");
        pool.setUsername("");
        pool.setPassword("");
        pool.setMaxTotal(2);
        pool.getConnection().prepareStatement("create table if not exists items( "
                + "id serial primary key, "
                + "name text, "
                + "created timestamp);").executeUpdate();

        store = new SqlTracker(pool);
    }

    @After
    public void delete() throws SQLException {
        pool.getConnection().prepareStatement("drop table items;").executeUpdate();
    }

    @Test
    public void createItem() throws Exception {
        store.add(new Item("name"));
        assertThat(store.findByName("name").size(), is(1));
    }

    @Test
    public void replaceItem() throws Exception {
        Item item = store.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
        Item nItem = new Item(item.getId(), "name2", new Timestamp(System.currentTimeMillis()));
        store.replace(item.getId(), nItem);
        assertThat(store.findById(item.getId()), is(nItem));
    }

    @Test
    public void deleteItem() throws Exception {
        Item item = store.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
        boolean rsl = store.delete(item.getId());
        assertTrue(rsl);
    }

    @Test
    public void findByIdItem() throws Exception {
        Item item = store.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
        Item rsl = store.findById(item.getId());
        assertEquals(rsl, item);
    }

    @Test
    public void findByNameItem() throws Exception {
        Item item = store.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
        List<Item> rsl = store.findByName(item.getName());
        assertTrue(rsl.containsAll(List.of(item)));
    }
}
