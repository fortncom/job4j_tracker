package ru.job4j.oop;

public interface Vehicle {

    static void main(String[] args) {
        Vehicle airplane = new Airplane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();
        Vehicle secondAirplane = new Airplane();
        Vehicle secondTrain = new Train();
        Vehicle secondBus = new Bus();
        Vehicle[] vehicles = new Vehicle[]{
                airplane, train, bus, secondAirplane, secondTrain, secondBus};
        for (Vehicle v : vehicles) {
            v.move();
        }
    }

    void move();
}
