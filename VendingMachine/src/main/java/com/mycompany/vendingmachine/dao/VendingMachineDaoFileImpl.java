/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.NoItemInventoryException;
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
import java.util.stream.Collectors;

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
    public List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException {
        loadInventory();
        return items
                .values()
                .stream()
                .filter(i -> i.getItemQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        loadInventory();
        return items.get(itemID);
    }

    @Override
    public Item makeSaleReduceInventory(String itemID) throws NoItemInventoryException, VendingMachinePersistenceException {
        Item removedItem = items.get(itemID);
        if (removedItem.getItemQuantity() > 0) {
            removedItem.setItemQuantity(removedItem.getItemQuantity() - 1);
        } else {
            throw new NoItemInventoryException("Not possible to reduce inventory below 0");
        }
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

            //need to ParseInt to keep quantity as INT
            Integer quantityAsInteger = Integer.parseInt((currentTokens[2]));
            currentItem.setItemQuantity(quantityAsInteger);

            //String to store as BigDecimal in Item
            currentItem.setItemPrice(currentTokens[3]);

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
