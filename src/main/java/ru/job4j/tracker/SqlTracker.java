package ru.job4j.tracker;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());
    private final BasicDataSource pool;

    public SqlTracker() {
        pool = new BasicDataSource();
    }

    public SqlTracker(BasicDataSource pool) {
        this.pool = pool;
    }

    public void init() {
        Properties config = new Properties();
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream(
                "app.properties")) {
            config.load(in);
            Class.forName(config.getProperty("psql.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(config.getProperty("psql.driver"));
        pool.setUrl(config.getProperty("psql.url"));
        pool.setUsername(config.getProperty("psql.login"));
        pool.setPassword(config.getProperty("psql.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    @Override
    public Item add(Item item) {
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                "insert into items(name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, item.getCreated());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                    return item;
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
        throw new IllegalStateException("Could not create new user");
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                             "update items set name = ?,  created = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, item.getCreated());
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
            return result;
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                             "delete from items where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return result;
    }

    @Override
    public void findAll(Observe<Item> observe) {
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                     "select * from items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created")
                    );
                    observe.receive(item);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                             "select * from items where name = ?")) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created")
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =  cn.prepareStatement(
                             "select * from items where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        pool.close();
    }

}
