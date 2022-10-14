/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author vicmaia
 * The program provides the maximum heart rate and target heart rate zone when user inputs age.
 */
public class HealthyHearts {
    public static void main(String[] args) {
        //declare age
        int age;
        Scanner userAge = new Scanner(System.in);

        System.out.print("What is your age? ");
        //user's input (age)
        age = userAge.nextInt();

        //round values to next integer and cast double values as shown on exercise example.
        int maximumHeartRate = Math.round(220 - age);
        int minHRZ = (int) Math.round(maximumHeartRate * .5);
        int  maxHRZ = (int) Math.round(maximumHeartRate * .85);

        //print results
        System.out.println("Your maximum heart rate should be " + maximumHeartRate + " beats per minute.");
        System.out.println("Your target HR Zone is " + minHRZ + " - " + maxHRZ + " beats per minute.");
    }

}