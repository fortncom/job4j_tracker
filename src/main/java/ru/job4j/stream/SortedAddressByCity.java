package ru.job4j.stream;

import java.util.Comparator;

public class SortedAddressByCity implements Comparator<Address> {
    @Override
    public int compare(Address address1, Address address2) {
        return address1.getCity().compareTo(address2.getCity());
    }
}
