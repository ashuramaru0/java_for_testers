package ru.stqa.geometry.figures;

public class Triangle {
    public static double triangleArea(double a, double b, double c){
        double p = (a + b + c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public static double trianglePerimeter (double a, double b, double c){
        return a + b + c;
    }
}
