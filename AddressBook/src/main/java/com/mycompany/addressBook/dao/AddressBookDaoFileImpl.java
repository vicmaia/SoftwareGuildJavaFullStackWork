/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

import com.mycompany.addressBook.dto.AddressBookException;
import com.mycompany.addressBook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class AddressBookDaoFileImpl implements AddressBookDao {

    public static final String DVD_LIBRARY_FILE = "dvd_library_file.txt";
    public static final String DELIMITER = "::";

    private Map<String, Address> dvds = new HashMap<>();

    @Override
    public Address addDVD(String title, Address dvd) throws AddressBookException {
        Address newDVD = dvds.put(title, dvd);
        writeDVD();
        return newDVD;
    }

    @Override
    public List<Address> getAllDVDs() throws AddressBookException {
        loadDVDs();
        return new ArrayList<Address>(dvds.values());
    }

    @Override
    public Address getDVD(String title) throws AddressBookException {
        loadDVDs();
        return dvds.get(title);
    }

    @Override
    public Address removeDVD(String title) throws AddressBookException {
        Address removedDVD = dvds.remove(title);
        writeDVD();
        return removedDVD;
    }

    private void loadDVDs() throws AddressBookException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookException(
                    "-_- Could not load DVD Library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] currentTokens;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Address object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the Address title
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the Address title into the Address constructor
            Address currentDVD = new Address(currentTokens[0]);
            // Set the remaining vlaues on currentStudent manually
            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setRating(currentTokens[2]);
            currentDVD.setDirector(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setComment(currentTokens[5]);

            // Put currentStudent into the map using studentID as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private void writeDVD() throws AddressBookException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY_FILE));
        } catch (IOException e) {
            throw new AddressBookException(
                    "Could not save DVD data.", e);
        }

        List<Address> DVDList = this.getAllDVDs();
        for (Address currentDVD : DVDList) {
            // write the Student object to the file
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getRating() + DELIMITER
                    + currentDVD.getDirector() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getComment());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
