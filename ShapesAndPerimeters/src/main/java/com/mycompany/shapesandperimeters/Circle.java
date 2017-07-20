/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author n0252282
 */
public class Circle extends Shape {

    private double radius;

    public Circle() {
        this(3);
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        //pi * r squared
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double perimeter() {
        //2 * pi * r
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + '}';
    }

}
