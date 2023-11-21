package ru.na.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void cantCalculatePerimeter() {
        Assertions.assertEquals(42.0, new Triangle(13.0, 14.0, 15.0).perimeter());
    }

    @Test
    void cantCalculateSemiperimeter() {
        Assertions.assertEquals(21.0, new Triangle(13.0, 14.0, 15.0).semiperimeter());
    }

    @Test
    void cantCalculateArea() {
        Assertions.assertEquals(84.0, new Triangle(13.0, 14.0, 15.0).area());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-13.0, 14.0, 15.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void testEquality() {
        var x = 13.0;
        var y = 14.0;
        var z = 15.0;
        var g1 = new Triangle(x, y, z);
        var g2 = new Triangle(x, z, y);
        var g3 = new Triangle(y, x, z);
        var g4 = new Triangle(z, x, y);
        var g5 = new Triangle(y, z, x);
        var g6 = new Triangle(z, y, x);
        Assertions.assertEquals(g1, g2);
        Assertions.assertEquals(g1, g3);
        Assertions.assertEquals(g1, g4);
        Assertions.assertEquals(g1, g5);
        Assertions.assertEquals(g1, g6);
        Assertions.assertEquals(g2, g3);
        Assertions.assertEquals(g2, g4);
        Assertions.assertEquals(g2, g5);
        Assertions.assertEquals(g2, g6);
        Assertions.assertEquals(g3, g4);
        Assertions.assertEquals(g3, g5);
        Assertions.assertEquals(g3, g6);
        Assertions.assertEquals(g4, g5);
        Assertions.assertEquals(g4, g5);
        Assertions.assertEquals(g5, g6);
    }
}





