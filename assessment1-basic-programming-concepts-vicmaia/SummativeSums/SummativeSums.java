/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vicmaia
 * Summative Sum: This program adds each item of the array together
 */
public class SummativeSums {

    public static void main (String[] args){

        //declare the three arrays
        int[] array1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] array2 = {999, -60, -77, 14, 160, 301};
        int[] array3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
                140, 150, 160, 170, 180, 190, 200, -99};

        //once the method is called, print the sum of each array
        System.out.println("#1 Array Sum: " + sumOfArray(array1));
        System.out.println("#2 Array Sum: " + sumOfArray(array2));
        System.out.println("#3 Array Sum: " + sumOfArray(array3));
    }

    //method that adds each integer of each array above
    public static int sumOfArray(int[] array){

        //declare variables for the loop
        int sum = 0;

        //loop takes the sum of the value of the current index and adds it with the value of the next index
        //until it reaches the end of array
        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];
        }

        return sum;
    }





}

