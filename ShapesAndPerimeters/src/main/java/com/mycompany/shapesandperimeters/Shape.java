package com.mycompany.shapesandperimeters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author n0252282
 */
public abstract class Shape {

    protected String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    protected abstract double area();
    protected abstract double perimeter();
}
