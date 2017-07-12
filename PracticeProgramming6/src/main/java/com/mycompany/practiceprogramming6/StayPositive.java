/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming6;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class StayPositive {
    public static void main(String[] args) {
        int counter = 0;
        int userNumber;
        Scanner userInput = new Scanner(System.in);
        
        System.out.print("Please enter a number: ");
        userNumber = userInput.nextInt();
        
        int i = 0;
        
        do {            
            System.out.print(userNumber + " ");
            userNumber--;
            counter++;
            if (counter % 10 == 0) System.out.println("");
        } while (userNumber >= i);
    }
}
