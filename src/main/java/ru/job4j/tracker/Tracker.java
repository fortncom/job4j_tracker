package ru.job4j.tracker;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item findByName(String key) {
        Item rsl = null;
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            if (item.getName() == key) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        int sizeSecondArray = 0;
        Item[] itemsWithoutNull = new Item[size];
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                itemsWithoutNull[sizeSecondArray++] = items[i];
            }
        }
        return itemsWithoutNull;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items[index] = item;
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items[index] = null;
        if (index != size - 1) {
            System.arraycopy(items, index + 1, items,
                    index, size - 1 - index);
            items[size - 1] = null;
            size--;
        }
        return true;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index] != null && items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

}
