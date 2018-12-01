package com.ofs.training;

public class Circle extends Shape {

    private static final float pi = 3.14f;

    @Override
    public double area(float radius) {
        return pi * radius * radius;
    }

    @Override
    public double perimeter(float radius) {
        return 2 * pi * radius;
    }
}
