/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bigdecimalinterestcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class InterestCalculator {

    public static void main(String[] args) {
        //Need to define variables
        BigDecimal anIntRate = new BigDecimal("0");
        int numYears = 0;
        BigDecimal initialPrincipal = new BigDecimal("0");

        Scanner userInput = new Scanner(System.in);

        //CurrentBalance * (1 + (QuarterlyIntrestRate / 100))
        System.out.println("Please enter annual interest rate: ");
        anIntRate = userInput.nextBigDecimal();

        System.out.println("Please enter the number of years: ");
        numYears = userInput.nextInt();

        System.out.println("Please enter principal amount: ");
        initialPrincipal = userInput.nextBigDecimal();

        //System.out.println(calculateYearlyInterest(anIntRate, initialPrincipal) + " " + calculateYearEnd(anIntRate, initialPrincipal));
        BigDecimal storeIt = initialPrincipal;

        for (int i = 1; i <= numYears; i++) {

            System.out.print("Year Number: " + i);
            //starts at principal, interest added every year (every loop)
            System.out.printf("    Starting Principal: $%.2f   ", storeIt);
            //call static method with interest rate and storeIt
            System.out.printf("Interest earned for year: $%.2f    ", calculateYearlyInterest(anIntRate, storeIt));
            //add yearly interest to storeIt for next loop
            storeIt = storeIt.add(calculateYearlyInterest(anIntRate, storeIt));
            //print out year end value
            System.out.printf("Principal year end: $%.2f", storeIt);
            //next line
            System.out.println(" ");
        }
    }

//    public static BigDecimal calculateYearEnd(BigDecimal yearlyInterest, BigDecimal principal) {
//        BigDecimal four = new BigDecimal("4");
//        BigDecimal oneHundy = new BigDecimal("100");
//        return yearlyInterest.divide(four).divide(oneHundy).add(BigDecimal.ONE).pow(4).multiply(principal);
//    }

    public static BigDecimal calculateYearlyInterest(BigDecimal yearlyInterest, BigDecimal principal) {
        BigDecimal four = new BigDecimal("4");
        BigDecimal oneHundy = new BigDecimal("100");
        
        return yearlyInterest
                .divide(four)
                .divide(oneHundy)
                .add(BigDecimal.ONE)
                .pow(4)
                .multiply(principal)
                .subtract(principal);
    }
}
