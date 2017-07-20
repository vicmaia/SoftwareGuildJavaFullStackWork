/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.userio;

/**
 *
 * @author n0252282
 */
public class UserIOApp {

    public static void main(String[] args) {
        UserIOImpl myUserIO = new UserIOImpl();

//        int myInt = myUserIO.readInt("Please enter a single integer: ");
//        System.out.println("You entered: " + myInt);
//
//        myInt = myUserIO.readInt("Please enter a single integer: ", 1, 10);
//        System.out.println("You entered: " + myInt);
//
//        String myString = myUserIO.readString("Please enter a String: ");
//        System.out.println("You entered: " + myString);
//        float myFloat = myUserIO.readFloat("Please enter a single float: ");
//        System.out.println("You entered: " + myFloat);
//
//        myFloat = myUserIO.readFloat("Please enter a single float: ", 1.5f, 10.5f);
//        System.out.println("You entered: " + myFloat);
//        double myDouble = myUserIO.readDouble("Please enter a single double: ");
//        System.out.println("You entered: " + myDouble);
//
//        myDouble = myUserIO.readDouble("Please enter a single double: ", 1.5, 10.5);
//        System.out.println("You entered: " + myDouble);
          myUserIO.print("Please enter a string: ");
 
    }
}
