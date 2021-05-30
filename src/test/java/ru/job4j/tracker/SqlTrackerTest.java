package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream(
                "app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("psql.driver"));
            return DriverManager.getConnection(
                    config.getProperty("psql.url.test"),
                    config.getProperty("psql.login"),
                    config.getProperty("psql.password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
            Item nItem = new Item(item.getId(), "name2", new Timestamp(System.currentTimeMillis()));
            tracker.replace(item.getId(), nItem);
            assertThat(tracker.findById(item.getId()), is(nItem));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item(1, "name", new Timestamp(System.currentTimeMillis())));
            boolean rsl = tracker.delete(item.getId());
            assertTrue(rsl);
        }
    }

}
