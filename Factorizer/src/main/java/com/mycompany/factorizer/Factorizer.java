/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.factorizer;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class Factorizer {

    public static void main(String[] args) {
        int sumOfFactors = 0, numberToFactor = 0, countFactors = 0;
        Scanner userInput = new Scanner(System.in);

        System.out.print("What number would you like to factor (positive integer only)? ");
        numberToFactor = userInput.nextInt();
        System.out.println("The factors of " + numberToFactor + " are:");

        for (int i = 1; i < numberToFactor; i++) {
            if (numberToFactor % i == 0) {
                System.out.println(i);
                sumOfFactors+= i;
                countFactors++;
            }
        }
        
        System.out.println(numberToFactor + " has " + (countFactors + 1) + " factors (including itself).");

        if (numberToFactor == sumOfFactors) {
            System.out.println(numberToFactor + " is a perfect number");
        } else {
            System.out.println(numberToFactor + " is NOT a perfect number");
        }

        if (sumOfFactors == 1) {
            System.out.println(numberToFactor + " is a prime number");
        } else {
            System.out.println(numberToFactor + " is NOT a prime number");
        }
    }
}
