package ru.job4j.ex;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;

        for (int index = 0; index < value.length; index++) {
            if (value[index].equals(key)) {
                rsl = index;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element is not present in array.");
        }
        return rsl;
    }

    public static void main(String[] args) {
        FindEl findEl = new FindEl();
        String[] elements = {"Item1", "Item2"};
        try {
            FindEl.indexOf(elements, "Element1");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
