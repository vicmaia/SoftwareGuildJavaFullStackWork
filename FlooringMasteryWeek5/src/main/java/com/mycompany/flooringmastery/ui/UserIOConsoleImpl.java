/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);

    }

    @Override
    public int readInt(String prompt) {

        boolean success = false;
        while (!success) {
            success = true;
            try {
                return Integer.parseInt(readString(prompt));
            } catch (NumberFormatException e) {
                print("You entered in an invalid selection"); //sout "You entered in an invalid selection"
                success = false;
            }
        }
        return 0;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int returnedInt = 0;
        boolean success = false;

        while (!success) {
            success = true;
            try {
                do {
                    returnedInt = Integer.parseInt(readString(prompt));
                } while (returnedInt < min || returnedInt > max);
                return returnedInt;
            } catch (NumberFormatException e) {
                print("You entered in an invalid selection"); //sout "You entered in an invalid selection"
                success = false;
            }
        }
        return 0;
    }

    @Override
    public String readString(String prompt) {
        String stringIn = "";
        System.out.println(prompt);
        stringIn = sc.nextLine();
        return stringIn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean success = false;
        while (!success) {
            success = true;
            try {
                return new BigDecimal(readString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
                success = false;
            }
        }
        return new BigDecimal("0");
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean success = false;
        while (!success) {
            success = true;
            try {
                return LocalDate.parse(readString(prompt), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in the format MM/dd/yyyy.");
                success = false;
            }
        }
        return null;
    }
}
