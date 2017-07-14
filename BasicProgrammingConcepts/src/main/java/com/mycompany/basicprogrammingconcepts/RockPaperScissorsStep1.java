/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.basicprogrammingconcepts;

import java.util.Random;
import java.util.Scanner;

/**
 * @author n0252282
 */
public class RockPaperScissorsStep1 {

    public static void main(String[] args) {

        //Declare Variables
        Scanner userInput = new Scanner(System.in);
        Random computerPlays = new Random();
        int userChoice = 0;
        int computerChoice = 0;
        String outCome = "";

        //Prompt user, store their choice
        System.out.println("Rock (1), Paper (2), or Scissors (3)?");
        userChoice = userInput.nextInt();

        //Computer makes a random choice
        computerChoice = computerPlays.nextInt(3) + 1;

        //Logic to determine winner
        if (userChoice == 1) {
            if (computerChoice == 1) {
                outCome = "Tie";
            } else if (computerChoice == 2) {
                outCome = "Computer Win";
            } else if (computerChoice == 3) {
                outCome = "User Win";
            }
        } else if (userChoice == 2) {
            if (computerChoice == 1) {
                outCome = "User Win";
            } else if (computerChoice == 2) {
                outCome = "Tie";
            } else if (computerChoice == 3) {
                outCome = "Computer Win";
            }
        } else if (userChoice == 3) {
            if (computerChoice == 1) {
                outCome = "Computer Win";
            } else if (computerChoice == 2) {
                outCome = "User Win";
            } else if (computerChoice == 3) {
                outCome = "Tie";
            }
        }
        //Print outcome
        System.out.println("User chose: " + choiceName(userChoice));
        System.out.println("Computer chose: " + choiceName(computerChoice));
        System.out.println("Result of the round: " + outCome);
    }

    //Method to convert int to String names (rock, paper, scissors)
    public static String choiceName(int x) {
        switch (x) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";
            default:
                return "";
        }
    }
}
