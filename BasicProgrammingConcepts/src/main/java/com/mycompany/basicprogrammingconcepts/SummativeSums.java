/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.basicprogrammingconcepts;

/**
 *
 * @author n0252282
 */
public class SummativeSums {

    public static void main(String[] args) {
        int[] array1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] array2 = {999, -60, -77, 14, 160, 301};
        int[] array3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};

        //call methods and print return values
        System.out.println("Array Sum: " + sumInts(array1));
        System.out.println("Array Sum: " + sumInts(array2));
        System.out.println("Array Sum: " + sumInts(array3));

    }

    //method to sum arrays
    public static int sumInts(int[] arrayToAdd) {
        int sum = 0;
        for (int i = 0; i < arrayToAdd.length; i++) {
            sum += arrayToAdd[i];
        }
        return sum;
    }
}
