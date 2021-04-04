package ru.job4j.poly;

public class Bus implements Transport {

    private int passengers;
    private String[] locations = {"Санкт-Петербург", "Казань"};
    private String currentLocation = locations[0];

    @Override
    public void go() {
        if (currentLocation == locations[0]) {
            System.out.println("Автобус отправляется в город " + locations[1]);
        } else {
            System.out.println("Автобус отправляется в город " + locations[0]);
        }
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
