package ru.stqa.geometry.figures;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.a) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
