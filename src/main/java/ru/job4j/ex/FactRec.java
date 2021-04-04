package ru.job4j.ex;

public class FactRec {
    public static int calc(int n) {
        if (n < 0) {
            throw new IllegalStateException("Can't calculate factorial from negative number.");
        } else if (n == 1 || n == 0) {
            return 1;
        }
      return calc(n - 1) * n;
    }

    public static void main(String[] args) {
        int rsl = calc(3);
        System.out.println(rsl);
    }
}
