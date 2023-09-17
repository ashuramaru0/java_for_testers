package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        Assertions.assertEquals(25., s.area());
    }

   @Test
    void canCalculatePerimeter(){
       Assertions.assertEquals(20, Square.perimeter());
    }
    @Test
    void canCalculateTriangleArea(){
        Assertions.assertEquals(6, Triangle.triangleArea(3.,4.,5.));
    }

    @Test
    void canCalculatePerimeter1(){
        Assertions.assertEquals(12, Triangle.trianglePerimeter(3,4,5));
    }
}
