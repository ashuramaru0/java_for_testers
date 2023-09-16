package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        Assertions.assertEquals(25., Square.area(5.));
    }

    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(20, Square.perimeter(5.));
    }
}
