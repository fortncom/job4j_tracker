package ru.job4j.oop;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void when22to82then6() {
        int expected = 6;
        Point point = new Point(2, 2);
        Point point2 = new Point(8, 2);
        double out = point.distance(point2);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void when13to56then5() {
        int expected = 5;
        Point point = new Point(1, 3);
        Point point2 = new Point(5, 6);
        double out = point.distance(point2);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void whenX2Y4Z6ToX6Y2Z10Then6() {
        int expected = 6;
        Point point = new Point(2, 4, 6);
        Point point2 = new Point(6, 2, 10);
        double out = point.distance3d(point2);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void whenX0Y0Z0ToX0Y0Z0Then0() {
        int expected = 0;
        Point point = new Point(0, 0, 0);
        Point point2 = new Point(0, 0, 0);
        double out = point.distance3d(point2);
        Assert.assertEquals(expected, out, 0.01);
    }

}