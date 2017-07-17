/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.objectinstantiation;

/**
 *
 * @author n0252282
 */
public class App {

    public static void main(String[] args) {
        //prior to adding static, had to instantiate method

        //Adder myAdder = new Adder();
        //int sum = myAdder.add(5,4);
        //static method is associated with class, not instance of class
        
        double myPi = Adder.PI;
        
        int sum = Adder.add(5, 4);
        
        

        System.out.println("The sum is " + sum);
    }
}
