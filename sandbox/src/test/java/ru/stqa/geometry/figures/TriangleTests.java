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
}
