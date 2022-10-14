/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author vicmaia
 * This programs provides genetics information about the user's dog.
 */
public class DogGenetics {

    public static void main(String[] args) {

        //declare values


        Random randomBreed = new Random();
        Scanner userInput = new Scanner(System.in);
        String dogName = "";

        int breedPercentage1 = 0, breedPercentage2 = 0, breedPercentage3 = 0, breedPercentage4 = 0, breedPercentage5 = 0;


        /**
        * Assign random values from 0 to 100 for one of the breed percentages
        * Then, assign another random value that is less than the previous one and repeat the process until we have
        * breedPercentage1 + breedPercentage2 + breedPercentage3 + breedPercentage4 + breedPercentage5 = 100
        */


        do {
            breedPercentage1 = randomBreed.nextInt(100) + 1;
            breedPercentage2 = randomBreed.nextInt(100 - breedPercentage1) + 1;
            breedPercentage3 = randomBreed.nextInt(100 - breedPercentage1 - breedPercentage2) + 1;
            breedPercentage4 = randomBreed.nextInt(100 - breedPercentage1 - breedPercentage2 - breedPercentage3) + 1;
            breedPercentage5 = 100 - breedPercentage1 - breedPercentage2 - breedPercentage3 - breedPercentage4;
        } while (breedPercentage1 == 0 && breedPercentage2 == 0 && breedPercentage3 == 0 && breedPercentage4 == 0 && breedPercentage5 == 0);

        //User as is asked to input dog's name;

        System.out.print("What is your dog's name? ");
        dogName = userInput.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");

        

        System.out.println("\n" + dogName + " is:");
        System.out.println();

        //Information about dog's genetics
        //Print the random values generated in the loop

        System.out.println(breedPercentage1 + "% St. Bernard");
        System.out.println(breedPercentage2 + "% Chihuahua");
        System.out.println(breedPercentage3 + "% Dramatic RedNosed Asian Pug");
        System.out.println(breedPercentage4 + "% Common Cur");
        System.out.println(breedPercentage5 + "% King Doberman");


        System.out.println("\nWow, that's QUITE the dog!");

    }
}