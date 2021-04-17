package ru.job4j.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class PhoneDictionary {

    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> isName = person -> person.getName().contains(key);
        Predicate<Person> isSurname = person -> person.getSurname().contains(key);
        Predicate<Person> isPhone = person -> person.getPhone().contains(key);
        Predicate<Person> isAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = isName.or(isSurname).or(isPhone).or(isAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
