/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplefileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class SimpleFileIO {

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("our-names.txt"));
        out.println("Ken");
        out.println("Kyle");
        //out.println("a third line in my file...");
        out.flush();
        out.close();

        Scanner sc = new Scanner(
                new BufferedReader(new FileReader("our-names.txt")));
// go through the file line by line
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }
    }
}
