package ru.job4j.poly;

public class Bus implements Transport {

    private int passengers;

    @Override
    public void go() {

    }

    @Override
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public double refuel(int fuel) {
        return fuel * 46.32;
    }
}
