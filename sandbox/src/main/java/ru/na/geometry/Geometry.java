package ru.na.geometry;

import ru.na.geometry.figures.Square;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
//        squares.forEach(Square::printSquareArea);

        Consumer<Square> print = square -> {
            Square.printSquareArea(square);
            Square.printPerimeter(square);
        };
        squares.peek(Square::printSquareArea).forEach(Square::printPerimeter);

//        Square.printSquareArea(new Square(7.0));
//        Square.printSquareArea(new Square(5.0));
//        Square.printSquareArea(new Square(3.0));
//
//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);
//
//        Triangle.printTriangleePerimeter(new Triangle(13.0, 14.0, 15.0));
    }
}

