package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s = new Square(4.0);
        double result = s.area();
        Assertions.assertEquals(25., result);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20, new Square(5.0).perimeter());
    }

    @Test
    void canCalculateTriangleArea() {
        Assertions.assertEquals(6, new Triangle(3., 4., 5.).triangleArea());
    }

    @Test
    void canCalculatePerimeter1() {
        Assertions.assertEquals(12, new Triangle(3, 4, 5).trianglePerimeter());
    }
}

