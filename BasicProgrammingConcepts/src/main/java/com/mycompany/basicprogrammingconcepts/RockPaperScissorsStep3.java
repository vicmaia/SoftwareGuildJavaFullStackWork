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
public class RockPaperScissorsStep3 {

    public static void main(String[] args) {

        //Declare Variables
        Scanner userInput = new Scanner(System.in);
        Random computerPlays = new Random();
        int userChoice = 0;
        int computerChoice = 0;
        int numRounds = 0;
        int numTies = 0;
        int numWins = 0;
        int compWins = 0;
        String outCome = "";

        //Prompt user, "How many rounds?"
        do {
            System.out.print("How many rounds would you like to play (1-10)?  ");
            numRounds = userInput.nextInt();
        } while (numRounds < 1 || numRounds > 10);

        //Move game to a for loop
        for (int i = 0; i < numRounds; i++) {
            //Prompt user, store their choice
            System.out.print("Rock (1), Paper (2), or Scissors (3)?  ");
            userChoice = userInput.nextInt();

            //Computer makes a random choice
            computerChoice = computerPlays.nextInt(3) + 1;

            //Logic to determine winner
            if (userChoice == 1) {
                if (computerChoice == 1) {
                    outCome = "Tie";
                    numTies++;
                } else if (computerChoice == 2) {
                    outCome = "Computer Win";
                    compWins++;
                } else if (computerChoice == 3) {
                    outCome = "User Win";
                    numWins++;
                }
            } else if (userChoice == 2) {
                if (computerChoice == 1) {
                    outCome = "User Win";
                    numWins++;
                } else if (computerChoice == 2) {
                    outCome = "Tie";
                    numTies++;
                } else if (computerChoice == 3) {
                    outCome = "Computer Win";
                    compWins++;
                }
            } else if (userChoice == 3) {
                if (computerChoice == 1) {
                    outCome = "Computer Win";
                    compWins++;
                } else if (computerChoice == 2) {
                    outCome = "User Win";
                    numWins++;
                } else if (computerChoice == 3) {
                    outCome = "Tie";
                    numTies++;
                }
            }
            //Print outcome of round
            System.out.println("Round:" + (i + 1));
            System.out.println("User chose: " + choiceName(userChoice));
            System.out.println("Computer chose: " + choiceName(computerChoice));
            System.out.println("Result of the round: " + outCome);
        }

        //Print final results
        System.out.println("Final results:");
        System.out.println("User Wins: " + numWins);
        System.out.println("Computer Wins: " + compWins);
        System.out.println("Ties: " + numTies);

        //Declare the winner!
        if (numWins > compWins) {
            System.out.println("You win!!!");
        } else if (compWins > numWins) {
            System.out.println("The computer won!!!");
        } else {
            System.out.println("We have a tie!!!");
        }
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
