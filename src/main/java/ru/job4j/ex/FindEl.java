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

    public static boolean sent(String value, String[] abuses) throws ElementAbuseException {
        for (String abus : abuses) {
            if (abus == value) {
                throw new ElementAbuseException("Message contains forbidden words");
            }
        }
        return true;
    }

    public static void process(String[] values, String key, String[] abuses) {
        try {
            if (indexOf(values, key) != -1) {
                sent(key, abuses);
            }
        } catch (ElementAbuseException ea) {
            ea.printStackTrace();
        } catch (ElementNotFoundException en) {
            en.printStackTrace();
        }
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
