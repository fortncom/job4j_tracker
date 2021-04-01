package ru.job4j.pojo;

import java.util.Date;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Gromov Anatoly Alexeyevich");
        student.setGroup("12-43");
        student.setDate(new Date());
        System.out.print("Student "
                + student.getFio());
        System.out.print(" studying in a group "
                + student.getGroup());
        System.out.print(" since "
                + student.getDate());
    }
}
