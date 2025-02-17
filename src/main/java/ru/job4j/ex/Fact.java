package ru.job4j.ex;

public class Fact {
    public static void main(String[] args) {
        System.out.println(new Fact().calc(0));
        System.out.println(new Fact().calc(-2));
    }

    public int calc(int n) {
        int rsl = 1;
        if (n < 0) {
            throw new IllegalStateException("Argument must be greater than or equal to zero.");
        }
        for (int index = 1; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }
}
