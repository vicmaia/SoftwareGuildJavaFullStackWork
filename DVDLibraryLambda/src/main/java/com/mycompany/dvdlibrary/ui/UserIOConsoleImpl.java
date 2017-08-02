/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.ui;

import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public double readDouble(String prompt) {
        return Double.parseDouble(readString(prompt));
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double returnedDouble;
        do {
            returnedDouble = Double.parseDouble(readString(prompt));
        } while (returnedDouble < min || returnedDouble > max);
        return returnedDouble;
    }

    @Override
    public float readFloat(String prompt) {
        return Float.parseFloat(readString(prompt));
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float returnedFloat;
        do {
            returnedFloat = Float.parseFloat(readString(prompt));
        } while (returnedFloat < min || returnedFloat > max);
        return returnedFloat;
    }

    @Override
    public int readInt(String prompt) {
        return Integer.parseInt(readString(prompt));
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int returnedInt;
        do {
            returnedInt = Integer.parseInt(readString(prompt));
        } while (returnedInt < min || returnedInt > max);
        return returnedInt;
    }

    @Override
    public long readLong(String prompt) {
        return Long.parseLong(readString(prompt));
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long returnedLong;
        do {
            returnedLong = Long.parseLong(readString(prompt));
        } while (returnedLong < min || returnedLong > max);
        return returnedLong;
    }

    @Override
    public String readString(String prompt) {
        String stringIn = "";
        System.out.println(prompt);
        stringIn = sc.nextLine();
        return stringIn;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        return LocalDate.parse(readString(prompt), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    @Override
    public LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
