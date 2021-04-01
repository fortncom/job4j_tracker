package ru.job4j.oop;

public class Doctor extends Profession {

    private int pressure;
    private Patient patient;

    public Doctor(String name, String surname,
                  String education, String birthday, Patient patient) {
        super(name, surname, education, birthday);
        this.patient = patient;
    }

    public int getPressure() {
        return pressure;
    }

    public Patient getPatient() {
        return patient;
    }

    public void measureBloodPressure() {

    }
}
