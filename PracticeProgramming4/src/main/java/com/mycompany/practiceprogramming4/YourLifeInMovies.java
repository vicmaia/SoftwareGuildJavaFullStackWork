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
public class YourLifeInMovies {

    public static void main(String[] args) {
        int age;
        Scanner userInput = new Scanner(System.in);

        System.out.println("Please enter the year you were born: ");
        age = userInput.nextInt();

        if (age < 2005) {
            System.out.println("Pixar's 'Up' came out half a decade ago.");
        }
        if (age < 1995) {
            System.out.println("The first Harry Potter came out over 15 years ago.");
        }
        if (age < 1985) {
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        if (age < 1975) {
            System.out.println("Jurassic Park release is closer to the lunar landing, than today.");
        }
        if (age < 1965) {
            System.out.println("MASH has been around for almost half a century!");
        }
    }
}
