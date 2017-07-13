/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.module1exercises;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class InterestCalculator {

    public static void main(String[] args) {
        //Need to define variables
        double anIntRate = 0;
        int numYears = 0;
        double initialPrincipal = 0;
        
        Scanner userInput = new Scanner(System.in);
        
        //CurrentBalance * (1 + (QuarterlyIntrestRate / 100))
        System.out.println("Please enter annual interest rate: ");
        anIntRate = userInput.nextDouble();
        
        System.out.println("Please enter the number of year: ");
        numYears = userInput.nextInt();
        
        System.out.println("Please enter principal amount: ");
        initialPrincipal = userInput.nextDouble();
        double yearSum = initialPrincipal;
        
        //Need to sue scanner to get user input
        //For loop with a nested loop (of some kind) to perform calcs and produce incremental output.
        
        //The output should include the following for each year:
        //
        //The year number
        //The principal at the beginning of the year
        //The total amount of interest earned for the year
        //The principal at the end of the year
        
        for (int i = 1 ; i <= numYears; i++) {
            double quarterlySum = 0;
            double storeYearSum = yearSum;
            
            System.out.print("Year Number: " + i);
            System.out.printf("    Starting Principal: $%.2f   ", yearSum );
            
            
            for (int j = 1; j <= 4; j++) {
                
                quarterlySum = (yearSum + quarterlySum) * (1 + ((anIntRate * 0.25) / 100));
                //Use for debugging:
                //System.out.print ("Quarterly Sum: " + (quarterlySum - yearSum) + "     ");
                quarterlySum = quarterlySum - yearSum;
            }
           
            yearSum += quarterlySum;
            
            System.out.printf("Interest earned for the year: $%.2f    ",(yearSum - storeYearSum));
            System.out.printf("Principal at end of year: $%.2f", yearSum);
            System.out.println(" ");
        }
    }
}
