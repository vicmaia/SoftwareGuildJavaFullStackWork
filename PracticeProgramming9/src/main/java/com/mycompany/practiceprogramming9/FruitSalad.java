/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming9;

/**
 *
 * @author n0252282
 */
public class FruitSalad {

    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple", "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        String berry = "berry";
        String[] fruitSalad;
        int countFruits = 0;
        int appleTypes = 0;
        int berryTypes = 0;
        int orangeTypes = 0;

        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].contains("berry")) {
                countFruits++;
                berryTypes++;
                //System.out.println(fruit[i]);
            } else if (fruit[i].contains("Apple") && appleTypes < 3) {
                countFruits++;
                appleTypes++;
                //System.out.println(fruit[i]);
            } else if (fruit[i].contains("Orange") && orangeTypes < 2) {
                orangeTypes++;
                countFruits++;
                //System.out.println(fruit[i]);
            } else if (fruit[i].contains("Tomato") != true && fruit[i].contains("berry") != true
                    && fruit[i].contains("Apple") != true && fruit[i].contains("Orange") != true
                    && countFruits < 11) {
                countFruits++;
                //System.out.println(fruit[i]);
            }

        }
        fruitSalad = new String[countFruits];

        countFruits = 0;
        appleTypes = 0;
        orangeTypes = 0;
        berryTypes = 0;
        
        for (int i = 0; i < fruit.length; i++) {
            if (fruit[i].contains("berry")) {
                fruitSalad[countFruits] = fruit[i];
                countFruits++;
                berryTypes++;
            } else if (fruit[i].contains("Apple") && appleTypes < 3) {
                fruitSalad[countFruits] = fruit[i];
                countFruits++;
                appleTypes++;

            } else if (fruit[i].contains("Orange") && orangeTypes < 2) {
                fruitSalad[countFruits] = fruit[i];
                orangeTypes++;
                countFruits++;
                fruitSalad[countFruits] = fruit[i];
            } else if (fruit[i].contains("Tomato") != true && fruit[i].contains("berry") != true
                    && fruit[i].contains("Apple") != true && fruit[i].contains("Orange") != true
                    && countFruits < 11) {

                fruitSalad[countFruits] = fruit[i];
                countFruits++;
            }
        }
        for (String froot : fruitSalad) {
            System.out.println(froot);
        }

    }
}
