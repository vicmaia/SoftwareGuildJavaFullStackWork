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
public class TheCount {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int start = 0, end = 0, increment = 0, counter = 0;

        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.print("Start at: ");
        start = userInput.nextInt();
        System.out.print("End at: ");
        end = userInput.nextInt();
        System.out.print("Count by: ");
        increment = userInput.nextInt();

        for (int i = 0; i < end; i += increment) {

            int total = start + i;

            System.out.print(total + " ");

            counter++;
            if (counter % 3 == 0) {
                System.out.println("- Ah ah ah!");
            }

        }
    }
}
