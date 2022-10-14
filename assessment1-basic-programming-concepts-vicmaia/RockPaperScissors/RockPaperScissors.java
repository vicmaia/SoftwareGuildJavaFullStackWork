/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author vicmaia
 *
 * Rock, Papaer, Scissors.
 *
 * In this program, the user will play with a computer Rock, Papaer, Scissors.
 * It asks the users houw many rounds they want to play.
 * After the last round, the program anounces who is thw winner.
 * Then, the user has the option to play again.
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        //initialize variables
        int userWins = 0, computerWins = 0, ties = 0;
        boolean anotherRound = false, invalidResponse = false;

        //continue executing while user wants to play another round
        do{

            Scanner userInput = new Scanner(System.in);


            System.out.println("How many rounds do you want to play Rock, Paper, and Scissors?");
            System.out.println("Please enter number between 1 and 10");

            //receive user's integer input (number of rounds) and parse it to string to avoid bugs.
            int rounds = Integer.parseInt(userInput.nextLine());

            //if user's input is less than zero or more than 10, the programs will terminate.
            if (rounds < 0 || rounds > 10) {
                break;

            //otherwise, the program will iterate in a for loop until last round.
            } else {
                for (int i = 1; i <= rounds; i++) {
                    System.out.println("Enter 1 for Rock, 2 for Paper, or 3 for scissors.");

                    //receive integer input (user's choice) and parse it to string to avoid bugs.
                    int myChoice = Integer.parseInt(userInput.nextLine());



                    //Randomly generate computer's choice. Computer's choice can be any number from 1 to 3.
                    Random randomNumber = new Random();
                    int computerChoice = randomNumber.nextInt(3) + 1;


                    //Compute number of user's wins and computer's wins.
                    if ((myChoice == 1 && computerChoice == 2)||(myChoice == 2 && computerChoice == 3)||(myChoice == 3 && computerChoice == 1)) {
                        System.out.println("Computer chose paper. Computer won this round!");
                        computerWins++;
                    } else if ((myChoice == 1 && computerChoice == 3)||(myChoice == 2 && computerChoice == 1)|| (myChoice == 3 && computerChoice == 2)){
                        System.out.println("Computer chose scissors. You won won this round!");
                        userWins++;
                    } else if ((myChoice == 1 && computerChoice == 1)||(myChoice == 2 && computerChoice == 2)||(myChoice == 3 && computerChoice == 3)) {
                        System.out.println("Computer chose rock. It's a tie!");
                        ties++;
                    } else {
                        System.out.println("Invalid Number. Please try again.");
                        //for each time the user gives an invalid number, the loop will go back one index to allow user to play all rounds.
                        i--;
                    }
                }

                //show number of win, losses, ties, and result
                System.out.println("\nNumber of your wins: " + userWins);
                System.out.println("Number of computer wins: " + computerWins);
                System.out.println("Number of ties: " + ties);

                System.out.println("\nRESULT:");
                if (computerWins > userWins){
                    System.out.println("Computer Won!!");
                }
                else if (userWins > computerWins){
                    System.out.println("You  Won!!");
                }
                else{
                    System.out.println("It's a tie!!");
                }

                //go back to the beginning in case user wants to play again
                do{
                    System.out.println("\nWould you like to play again? (y/n)");
                    String userPlaysAgain = userInput.nextLine();
                    if(userPlaysAgain.equals("y")){
                        //playAgain = true;
                        userWins=0;
                        computerWins=0;
                        ties=0;
                        invalidResponse = false;
                    }
                    //if user does not want to play again, the program will terminate
                    else if (userPlaysAgain.equals("n")){
                        invalidResponse = false;
                        System.out.println("Thanks for Playing!");
                        System.exit(0);
                    }
                    //if user enters invalid response, it will ask if user wants to play again.
                    else {
                        System.out.println("You entered an invalid response. Please try again.");
                        invalidResponse = true;
                    }
                }while(invalidResponse);


            }
            }while (anotherRound = true);
        }
    }
