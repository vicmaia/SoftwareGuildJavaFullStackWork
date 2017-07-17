/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming2;

/**
 *
 * @author n0252282
 */
public class AllAboutMe {
//  Filename: AllAboutMe.java
//Write a program that has five variables. These variables should not all be the same type.
//These variables should have data that stores your name, your favorite food, how many pets you have, if you have ever eaten gnocchi, and the age when you learned to whistle.
//Print each piece of data out on it's own line using your shiny new variables!  
    public static void main(String[] args) {
    
    String name = "Ken G";
    String favFood = "Mexican";
    int numPets = 0;
    boolean gnocchi = false;
    int ageWhistle = 5;
    
        System.out.println("I am: " + name);
        System.out.println("My favorite food is: " + favFood);   
        System.out.println("I have: " + numPets + " pets");
        System.out.println("It is " + gnocchi +" that I have eaten gnocchi");
        System.out.println("When I was " + ageWhistle + " I learned to whistle");
    
    }

}
