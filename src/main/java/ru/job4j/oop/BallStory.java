package ru.job4j.oop;

public class BallStory {

    public static void main(String[] args) {
        Ball ball = new Ball();
        Hare hare = new Hare();
        Fox fox = new Fox();
        Wolf wolf = new Wolf();
        hare.tryEat(ball);
        ball.tryRollAway(hare);
        wolf.tryEat(ball);
        ball.tryRollAway(wolf);
        fox.tryEat(ball);
    }
}
