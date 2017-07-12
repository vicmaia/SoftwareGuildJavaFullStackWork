/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming5;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class HighRoller {

    public static void main(String[] args) {

        Random diceRoller = new Random();
        Scanner userInput = new Scanner(System.in);

        
        int numSides = 0;
        
        System.out.println("Please enter the desired number of sides: ");
        numSides = userInput.nextInt();
        
        int rollResult = diceRoller.nextInt(numSides) + 1;
        
        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a crit failure! Ouch.");
        }
        if (rollResult == 20) {
            System.out.println("You rolled a critical! Nice Job!");
        }
    }
}
