/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming3;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class HealthyHearts {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int age, maxHeart;
        double bottomTarget, topTarget;

        System.out.println("What is your age?");
        age = parseInt(inputReader.nextLine());

        maxHeart = 220 - age;
        bottomTarget = maxHeart * .5;
        topTarget = maxHeart * .85;

        System.out.println("Your maximum heart rate should be " + maxHeart + " beats per minute.");
        System.out.println("Your target HR Zone is " + bottomTarget + " - " + topTarget + " beats per minute.");
    }
}
