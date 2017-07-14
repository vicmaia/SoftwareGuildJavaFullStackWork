/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming8;

import java.util.Random;

/**
 *
 * @author n0252282
 */
public class BarelyControlledChaos {

    public static void main(String[] args) {

        String color = color(); // call color method here 
        String animal = animal(); // call animal method again here 
        String colorAgain = color(); // call color method again here 
        int weight = number(5, 200); // call number method, 
        // with a range between 5 - 200 
        int distance = number(10, 20); // call number method, 
        // with a range between 10 - 20 
        int number = number(10000, 20000); // call number method, 
        // with a range between 10000 - 20000 
        int time = number(2, 6); // call number method, 
        // with a range between 2 - 6            

        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
                + weight + "lb " + " miniature " + animal
                + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                + number + " " + colorAgain + " poppies for nearly "
                + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                + "let me tell you!");
    }

    public static String color() {
        Random rand = new Random();
        int number = rand.nextInt(5) + 1;

        switch (number) {
            case 1:
                return "red";
            case 2:
                return "orange";
            case 3:
                return "yellow";
            case 4:
                return "green";
            case 5:
                return "blue";
            default:
                return "none";
        }

    }

    public static String animal() {
        Random rand = new Random();
        int number = rand.nextInt(5) + 1;

        switch (number) {
            case 1:
                return "kangaroo";
            case 2:
                return "dog";
            case 3:
                return "rabbit";
            case 4:
                return "lamb";
            case 5:
                return "pigeon";
            default:
                return "none";
        }

    }

    public static int number(int lower, int upper) {
        Random rand = new Random();
        
        int number = lower + (int) (Math.random() * ((upper - lower) + 1));

        return number;
    }

}

