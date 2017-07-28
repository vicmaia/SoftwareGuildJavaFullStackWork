/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
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
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        loadInventory();
        return items.get(itemID);
    }

    @Override
    public Item removeItem(String studentId)
            throws VendingMachinePersistenceException {
        Item removedItem = items.remove(studentId);
        writeInventory();
        return removedItem;
    }

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(currentTokens[2]);

            //need to ParseInt to keep quantity as INT
            int quantityAsInteger = Integer.parseInt((currentTokens[3]));
            currentItem.setItemQuantity(quantityAsInteger);

            //put item into map
            items.put(currentItem.getItemID(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private void writeInventory() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save inventory data.", e);
        }

        List<Item> itemList = this.getAllItems();
        for (Item currentStudent : itemList) {
            // write the Item object to the file
            out.println(currentStudent.getItemID() + DELIMITER
                    + currentStudent.getItemName() + DELIMITER
                    + currentStudent.getItemQuantity() + DELIMITER
                    + currentStudent.getItemPrice());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
