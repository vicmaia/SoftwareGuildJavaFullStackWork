/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming3;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        String storage, planet, volcano, element;
        
        System.out.println("1,024 Gigabytes is equal to one what?");
        storage = inputReader.nextLine();
        
        System.out.println("In our solar system which is the only planet that rotates clockwise?");
        planet = inputReader.nextLine();
        
        System.out.println("The largest volcano ever discovered in our solar system is located on which planet?");
        volcano = inputReader.nextLine();
        
        System.out.println("What is the most abundant element in the earth's atmosphere?");
        element = inputReader.nextLine();
        
        System.out.println("Wow, 1,024 Gigabytes is a " + planet);
        System.out.println("I didn't know that the largest ever volcano was discovered on " + storage);
        System.out.println("That's amazing that " + volcano + " is the most abundant element in the atmosphere...");
        System.out.println(element + " is the only planet that rotates clockwise, neat!");
    }
}
