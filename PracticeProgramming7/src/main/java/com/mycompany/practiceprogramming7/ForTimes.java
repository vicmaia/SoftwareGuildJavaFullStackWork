/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming7;

import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class ForTimes {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int storeNum = 0;

        System.out.print("What times table shall I recite?");
        storeNum = userInput.nextInt();

        for (int i = 1; i <= 15; i++) {
            System.out.println(i + " * " + storeNum + " is: " + (i * storeNum));
        }

    }
}
