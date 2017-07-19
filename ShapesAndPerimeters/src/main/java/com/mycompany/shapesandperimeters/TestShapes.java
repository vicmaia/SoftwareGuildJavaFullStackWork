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
public class TestShapes {

    public static void main(String[] args) {
// Square       
        Square mySquare = new Square(8);

        System.out.println(mySquare.toString() + " Perimeter: " + mySquare.perimeter());
        System.out.println(mySquare.toString() + " Area: " + mySquare.area());
// Rectangle
        Rectangle myRectangle = new Rectangle(10, 5);

        System.out.println(myRectangle.toString() + " Perimeter: " + myRectangle.perimeter());
        System.out.println(myRectangle.toString() + " Area: " + myRectangle.area());

// Triangle
        Triangle myTriangle = new Triangle(5, 5, 5);

        System.out.println(myTriangle.toString() + " Perimeter: " + myTriangle.perimeter());
        System.out.println(myTriangle.toString() + " Area: " + myTriangle.area());

// Circle
        Circle myCircle = new Circle(3);
        System.out.println(myCircle.toString() + " Perimeter: " + myCircle.perimeter());
        System.out.println(myCircle.toString() + " Area: " + myCircle.area());
    }
}
