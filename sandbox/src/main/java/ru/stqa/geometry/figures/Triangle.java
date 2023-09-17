package ru.stqa.geometry.figures;

public record Triangle(double a,
    double b,
    double c) {
    public  double triangleArea(){
        double p = (this.a + this.b + this.c)/2;
        return Math.sqrt(p*(p-this.a)*(p-this.b)*(p-this.c));
    }

    public double trianglePerimeter(){
        return this.a + this.b + this.c;
    }
}
