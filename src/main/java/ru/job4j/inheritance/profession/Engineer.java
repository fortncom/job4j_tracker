package ru.job4j.inheritance.profession;

public class Engineer extends Profession {

    private Computer computer;

    public Engineer(String name, String surname,
                    String education, String birthday) {
        super(name, surname, education, birthday);
    }

    private void softwareCustomization(Computer computer) {

    }

}
