package ru.job4j.oop;

public class Battery {

    private int load;

    public Battery(int load) {
        this.load = load;
    }

    public void exchange(Battery another) {
        another.load += this.load;
        this.load = 0;
    }

    public static void main(String[] args) {
        Battery batteryHigh = new Battery(80);
        Battery batteryLow = new Battery(10);
        batteryLow.exchange(batteryHigh);
        System.out.println("batteryHigh load: " + batteryHigh.load);
        System.out.println("batteryLow load: " + batteryLow.load);
    }
}
