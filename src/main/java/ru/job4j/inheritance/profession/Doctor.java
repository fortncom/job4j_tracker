package ru.job4j.inheritance.profession;

public class Doctor extends Profession {

    private int pressure;
    private Patient patient;
    private Diagnosis diagnosis;

    public Doctor(String name, String surname,
                  String education, String birthday, Patient patient) {
        super(name, surname, education, birthday);
        this.patient = patient;
    }

    public Diagnosis heal(Patient pacient) {
        return new Diagnosis();
    }

    public Patient getPatient() {
        return patient;
    }

    public void measureBloodPressure() {

    }
}
