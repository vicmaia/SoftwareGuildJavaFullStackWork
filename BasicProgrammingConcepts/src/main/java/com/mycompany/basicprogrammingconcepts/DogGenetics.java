/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.basicprogrammingconcepts;

import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class DogGenetics {

    public static void main(String[] args) {

        Random diceRoller = new Random();
        Scanner userInput = new Scanner(System.in);
        String dogName = "";

        int percent1 = 0, percent2 = 0, percent3 = 0, percent4 = 0, percent5 = 0;

        do {
            percent1 = diceRoller.nextInt(100) + 1;
            percent2 = diceRoller.nextInt(100 - percent1) + 1;
            percent3 = diceRoller.nextInt(100 - percent1 - percent2) + 1;
            percent4 = diceRoller.nextInt(100 - percent1 - percent2 - percent3) + 1;
            percent5 = 100 - percent1 - percent2 - percent3 - percent4;
        } while (percent1 == 0 && percent2 == 0 && percent3 == 0 && percent4 == 0 && percent5 == 0);

        System.out.print("What is your dog's name? ");
        dogName = userInput.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        System.out.println("");

        System.out.println(dogName + " is:");
        System.out.println("");

        System.out.println(percent1 + "%" + " St. Bernard");
        System.out.println(percent2 + "%" + " Chihuahua");
        System.out.println(percent3 + "%" + " Dramatic RedNosed Asian Pug");
        System.out.println(percent4 + "%" + " Common Cur");
        System.out.println(percent5 + "%" + " King Doberman");

    }
}
