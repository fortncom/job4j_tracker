package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {

    @Test
    public void whenCollectAddressesOfProfiles() {
        Address address1 = new Address("Kirov", "Mira", 10, 43);
        Address address2 = new Address("Korolev", "Lenina", 21, 112);
        Address address3 = new Address("Tver", "Pobedy", 17, 1);

        List<Profile> profiles = List.of(
            new Profile(address1),
            new Profile(address2),
            new Profile(address3)
        );
        Profiles prof = new Profiles();
        List<Address> rsl = prof.collect(profiles);

        List<Address> expected = List.of(
                address1,
                address2,
                address3
        );
        assertThat(rsl, is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddressEqualsNull() {
        Address address1 = new Address("Kirov", "Mira", 10, 43);
        Address address2 = null;
        Address address3 = new Address("Tver", "Pobedy", 17, 1);

        List<Profile> profiles = List.of(
                new Profile(address1),
                new Profile(address2),
                new Profile(address3)
        );
        Profiles prof = new Profiles();
        List<Address> rsl = prof.collect(profiles);

        List<Address> expected = List.of(
                address1,
                address2,
                address3
        );
    }

    @Test
    public void whenDuplicateAddress() {
        Address address1 = new Address("Kirov", "Mira", 10, 43);
        Address address2 = new Address("Korolev", "Lenina", 21, 112);
        Address address3 = new Address("Tver", "Pobedy", 17, 1);
        Address address4 = new Address("Tver", "Pobedy", 17, 1);

        List<Profile> profiles = List.of(
                new Profile(address1),
                new Profile(address2),
                new Profile(address3),
                new Profile(address4)
        );
        Profiles prof = new Profiles();
        List<Address> rsl = prof.collect(profiles);

        List<Address> expected = List.of(
                address1,
                address2,
                address3
        );
        assertThat(rsl, is(expected));
    }
}
