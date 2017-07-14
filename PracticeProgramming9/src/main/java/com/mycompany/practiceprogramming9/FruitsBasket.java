/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming9;

import java.util.Arrays;

/**
 *
 * @author n0252282
 */
public class FruitsBasket {

    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};

        int countApples = 0;
        int countOranges = 0;

        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].compareTo("Apple") == 0) {
                System.out.println(i);
                countApples++;
            }
            if (fruit[i].compareTo("Orange") == 0) {
                System.out.println(i);
                countOranges++;
            }
        }
        String[] apples = new String[countApples];
        String[] oranges = new String[countOranges];

        System.out.println("");
        System.out.println("Total # of fruit in basket: " + fruit.length);
        System.out.println("");
        System.out.println("Number of apples: " + apples.length);
        System.out.println("");
        System.out.println("Number of oranges: " + oranges.length);

        int appleIndex = 0;
        int orangeIndex = 0;

        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].compareTo("Apple") == 0) {
                apples[appleIndex] = fruit[i];
                appleIndex++;

            }
            if (fruit[i].compareTo("Orange") == 0) {
                oranges[orangeIndex] = fruit[i];
                orangeIndex++;
            }
        }
//        for (String apple : apples){
//            System.out.println(apple);
//        }
    }
}
