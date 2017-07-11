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
public class PassingTheTuringTest {
//Create a program that uses taken user input in a conversation. 
//First ask the user for their name. Then display that name, 
//and tell them yours (or your AI's name!) 
//Ask them for their favorite color. 
//Then display that, but in a conversational way. 
//Do the same thing with favorite food and number, and then say goodbye!
//
//Make sure you use the right variable with the right user input!

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        String name;
        String color;
        String fruit;
        int myNumber = -7;
        int yourNumber;

        System.out.println("Hello there!");
        System.out.println("What's your name?");
        name = inputReader.nextLine();

        System.out.println("");
        System.out.println("Hi, " + name + " What's your favorite color? ");
        color = inputReader.nextLine();

        System.out.println("");
        System.out.println("Huh, " + color + "? Mine's Electric Lime.");
        System.out.println("");

        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + name + "?");
        fruit = inputReader.nextLine();

        System.out.println("");
        System.out.println("Really? " + fruit + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        yourNumber = parseInt(inputReader.nextLine());
        
        System.out.println("");
        System.out.println(yourNumber + " is a cool number, mine's " + myNumber + ".");
        System.out.println("Did you know " + yourNumber + " * " + myNumber + " is " + (myNumber * yourNumber) + "? That's a cool number too!");

    }
}
