/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming6;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class BewareTheKraken {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String yesNo = "no";
        Random randomizer = new Random();

        String fish = "";

        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        while (depthDivedInFt < 36200) {
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");
            
            int num = randomizer.nextInt(3) + 1;
            switch (num) {
                case 1:
                    fish = "Carp!";
                    break;
                case 2:
                    fish = "Tuna!";
                    break;
                case 3:
                    fish = "Blowfish!";
                    break;
            }
            System.out.println("We saw a " + fish);

            System.out.println("Do you want to stop?");
            yesNo = userInput.nextLine();

            if (yesNo.matches("yes")) {
                break;
            }
            if (depthDivedInFt >= 20000) {
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}
