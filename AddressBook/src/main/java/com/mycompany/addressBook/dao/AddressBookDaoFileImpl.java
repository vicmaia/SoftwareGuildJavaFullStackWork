/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressBook.dao;

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

    public static final String ADDRESS_LIBRARY_FILE = "address_library_file.txt";
    public static final String DELIMITER = "::";

    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookException {
        Address newAddress = addresses.put(lastName, address);
        writeAddress();
        return newAddress;
    }

    @Override
    public List<Address> getAllAddresses() throws AddressBookException {
        loadAddresss();
        return new ArrayList<Address>(addresses.values());
    }

    @Override
    public Address getAddress(String lastName) throws AddressBookException {
        loadAddresss();
        return addresses.get(lastName);
    }

    @Override
    public Address removeAddress(String lastName) throws AddressBookException {
        Address removedAddress = addresses.remove(lastName);
        writeAddress();
        return removedAddress;
    }

    private void loadAddresss() throws AddressBookException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ADDRESS_LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookException(
                    "-_- Could not load Address Library data into memory.", e);
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
        // Go through ROSTER_FILE line by line, decoding each line into an 
        // address object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Address object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the Address lastName
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the Address lastName into the Address constructor
            Address currentAddress = new Address(currentTokens[0]);
            // Set the remaining vlaues on currentStudent manually
            currentAddress.setFirstName(currentTokens[1]);
            currentAddress.setStreetAddress(currentTokens[2]);
            currentAddress.setCity(currentTokens[3]);
            currentAddress.setState(currentTokens[4]);
            currentAddress.setZip(currentTokens[5]);

            // Put currentStudent into the map using studentID as the key
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
        // close scanner
        scanner.close();
    }

    private void writeAddress() throws AddressBookException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ADDRESS_LIBRARY_FILE));
        } catch (IOException e) {
            throw new AddressBookException(
                    "Could not save Address data.", e);
        }

        List<Address> AddressList = this.getAllAddresses();
        for (Address currentAddress : AddressList) {
            // write the Student object to the file
            out.println(currentAddress.getLastName() + DELIMITER
                    + currentAddress.getFirstName() + DELIMITER
                    + currentAddress.getStreetAddress() + DELIMITER
                    + currentAddress.getCity() + DELIMITER
                    + currentAddress.getState() + DELIMITER
                    + currentAddress.getZip());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
