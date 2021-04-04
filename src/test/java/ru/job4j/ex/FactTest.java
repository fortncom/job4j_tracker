package ru.job4j.ex;

import org.junit.Test;

public class FactTest {

    @Test(expected = IllegalStateException.class)
    public void whenNegativeNumberThenFinish() {
        Fact fact = new Fact();
        fact.calc(-1);
    }

}