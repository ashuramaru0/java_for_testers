package ru.stqa.geometry.figures;

public record Triangle(double a,
    double b,
    double c) {
    public  double triangleArea(){
        double p = (this.a + this.b + this.c)/2;
        return Math.sqrt(p*(p-this.a)*(p-this.b)*(p-this.c));
    }
    public Triangle{
        if (a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a > b + c || b > a + c || c > a + b) {
            throw new IllegalArgumentException("The sum of any two sides must be no less than the third side");
        }
    }
    public double trianglePerimeter(){
        return this.a + this.b + this.c;
    }
}
