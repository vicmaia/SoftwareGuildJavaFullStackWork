/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming3;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class BiggerBetterAdder {
    public static void main (String[] args) {
        String first,second,third;
        
        int x = 5;
        int y = 10;
        int z = 20;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Enter your first number: ");
        first = inputReader.nextLine();
        
        System.out.println("Enter your second number: ");
        second = inputReader.nextLine();
        
        System.out.println("Enter your third number: ");
        third = inputReader.nextLine();
        
        x = parseInt(first);
        y = parseInt(second);
        z = parseInt(third);
        
        
        System.out.println("First numer is: " + x + " Second number is: " + y + " Third number is: " + z);
        System.out.println("The total is: " + (x + y + z));
    }
}
