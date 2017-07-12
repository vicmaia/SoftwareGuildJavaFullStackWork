/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming4;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class GuessMe {
    public static void main(String[] args) {
        int secretNumber = 7;
        int guessedNumber;
        Scanner userInput = new Scanner(System.in);
        
        System.out.print("Please guess a number: ");
        guessedNumber = userInput.nextInt();
        
        if (guessedNumber == secretNumber) {
            System.out.println("Wow, nice guess! That was it!");
        } else if (guessedNumber < secretNumber) {
            System.out.println("Ha, nice try - too low! I chose " + secretNumber);
        } else {
            System.out.println("Too bad, way too high. I chose " + secretNumber);
        }
    }
}
