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
public class Square extends Shape {
    private double sideLength;
    
    public Square () {
        this(10);
    }
    public Square (double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    @Override
    public double area(){
        //sideLength^2
        return sideLength * sideLength;
    }
    @Override
    public double perimeter(){
        //sideLength*4
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Square{" + "sideLength=" + sideLength + '}';
    }
    
    
}
