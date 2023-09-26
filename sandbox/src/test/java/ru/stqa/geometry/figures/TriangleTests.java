package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.Triangle;

public class TriangleTests {
    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(1, 3, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }
    @Test
    void cannotCreateRectangleWithNegativeSide(){
        try{
            new Rectangle(-5., 3);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }
    @Test
    void testEquality(){
        var t1 = new Triangle(5.0,4.0,3.0);
        var t2 = new Triangle(5.0,4.0, 3.0);
        Assertions.assertEquals(t1,t2);
    }
    @Test
    void testEquality2(){
        var t1 = new Triangle(5.0,4.0,4.0);
        var t2 = new Triangle(5.0,3.0, 3.0);
        Assertions.assertEquals(t1,t2);
    }
    @Test
    void testNonEquality(){
        var t1 = new Triangle(5.0,4.0,3.0);
        var t2 = new Triangle(5.0,5.0, 3.0);
        Assertions.assertNotEquals(t1,t2);
    }
    @Test
    void testPassed(){
        var t1 = new Triangle(3.0,5.0,4.0);
        var t2 = new Triangle(5.0,4.0, 3.0);
        Assertions.assertTrue(t1.equals(t2));
    }
}
