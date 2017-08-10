/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class TestGenerateOrderID {

    public static void main(String[] args) {

        Scanner scanner = null;
        Integer nextOrderNumber = 0;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("OrderNumber.txt")));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        nextOrderNumber = scanner.nextInt() + 1;
        System.out.println(nextOrderNumber);
        scanner.close();

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("OrderNumber.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        out.println(nextOrderNumber);

        // force PrintWriter to write line to the file
        out.flush();

        // Clean up
        out.close();
    }

}
