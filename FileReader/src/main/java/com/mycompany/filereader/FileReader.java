/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class FileReader {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new java.io.FileReader("our-names.txt")));
// go through the file line by line
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }
    }
}
