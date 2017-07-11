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
public class DoItBetter {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int miles, hotDogs, languages;

        System.out.println("Hey, you!  How many miles can you run?");
        miles = parseInt(inputReader.nextLine());
        System.out.println("Not bad!  I can run " + (miles * 2 + 1) + ".");
        System.out.println("");
       
        System.out.println("How many hotdogs can you eat?");
        hotDogs = parseInt(inputReader.nextLine());
        System.out.println("Not bad!  I can eat " + (hotDogs * 2 + 1) + ".");
        System.out.println("");
        
        System.out.println("How many languages do you know?");
        languages = parseInt(inputReader.nextLine());
        System.out.println("Not bad!  I know " + (languages * 2 + 1) + " languages.");        
    }
}
