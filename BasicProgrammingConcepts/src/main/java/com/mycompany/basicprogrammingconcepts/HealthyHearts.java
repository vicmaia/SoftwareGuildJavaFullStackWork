/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.basicprogrammingconcepts;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class HealthyHearts {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int age;
        int maxHeart, bottomTarget, topTarget;

        System.out.print("What is your age?  ");
        age = parseInt(inputReader.nextLine());

        maxHeart = 220 - age;
        //used Math.round to return int as shown in assignment
        bottomTarget = (int) Math.round(maxHeart * .5);
        topTarget = (int) Math.round(maxHeart * .85);

        System.out.println("Your maximum heart rate should be " + maxHeart + " beats per minute.");
        System.out.println("Your target HR Zone is " + bottomTarget + " - " + topTarget + " beats per minute.");
    }
}
