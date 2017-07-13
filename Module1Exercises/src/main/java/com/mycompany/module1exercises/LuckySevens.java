/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.module1exercises;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class LuckySevens {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int bankRoll = 0;
        Random randomNum = new Random();
        int maxMoney = 0;
        int counter = 0;
        int highCounter = 0;

        System.out.print("How many dollars do you have? ");
        bankRoll = userInput.nextInt();
        maxMoney = bankRoll;

        while (bankRoll > 0) {
            int die1 = randomNum.nextInt(6) + 1;
            int die2 = randomNum.nextInt(6) + 1;

            if ((die1 + die2) == 7) {
                bankRoll += 4;
            } else {
                bankRoll -= 1;
            }
            
            counter++;
            
            if (maxMoney < bankRoll) {
                maxMoney = bankRoll;
                highCounter = counter;
            }
            
        }
        System.out.println("You are broke after " + counter + " rolls");
        System.out.println("You should have quit after " + highCounter + " rolls when you had $" + maxMoney);
    }
}
