/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.service.NoOrderInventoryException;
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
public class FlooringMasteryOrderDaoFileImpl implements FlooringMasteryOrderDao {

    public String inventoryFile = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Order> orders = new HashMap<>();

    public FlooringMasteryOrderDaoFileImpl(String inventoryFile) {
        this.inventoryFile = inventoryFile;
    }

    public FlooringMasteryOrderDaoFileImpl() {
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        loadInventory();
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> getAllOrdersFiltered() throws FlooringMasteryPersistenceException {
        loadInventory();
        return orders
                .values()
                .stream()
                .filter(i -> i.getOrderQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrder(String orderID) throws FlooringMasteryPersistenceException {
        loadInventory();
        return orders.get(orderID);
    }

    @Override
    public Order makeSaleReduceInventory(String orderID) throws NoOrderInventoryException, FlooringMasteryPersistenceException {
        Order removedOrder = orders.get(orderID);
        if (removedOrder.getOrderQuantity() > 0) {
            removedOrder.setOrderQuantity(removedOrder.getOrderQuantity() - 1);
        } else {
            throw new NoOrderInventoryException("Not possible to reduce inventory below 0");
        }
        writeInventory();
        return removedOrder;
    }

    private void loadInventory() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order(currentTokens[0]);
            currentOrder.setOrderName(currentTokens[1]);

            //need to ParseInt to keep quantity as INT
            Integer quantityAsInteger = Integer.parseInt((currentTokens[2]));
            currentOrder.setOrderQuantity(quantityAsInteger);

            //String to store as BigDecimal in Order
            currentOrder.setOrderPrice(currentTokens[3]);

            //put order into map
            orders.put(currentOrder.getOrderID(), currentOrder);
        }
        // close scanner
        scanner.close();
    }

    private void writeInventory() throws FlooringMasteryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save inventory data.", e);
        }

        List<Order> orderList = this.getAllOrders();
        for (Order currentStudent : orderList) {
            // write the Order object to the file
            out.println(currentStudent.getOrderID() + DELIMITER
                    + currentStudent.getOrderName() + DELIMITER
                    + currentStudent.getOrderQuantity() + DELIMITER
                    + currentStudent.getOrderPrice());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    //Added for future use and for testing
    @Override
    public Order addOrder(String orderID, Order order) throws FlooringMasteryPersistenceException {
        Order newOrder = orders.put(orderID, order);
        writeInventory();
        return newOrder;
    }

    @Override
    public Order removeOrder(String orderID) throws FlooringMasteryPersistenceException {
        Order removedOrder = orders.remove(orderID);
        writeInventory();
        return removedOrder;
    }
}
