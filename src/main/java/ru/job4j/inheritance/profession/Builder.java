package ru.job4j.inheritance.profession;

public class Builder {

    private Draw draw;

    private Draw createDraw() {
        return new Draw();
    }
}
