/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryOrderDaoFileImpl implements FlooringMasteryOrderDao {

    public String ordersFile = "Orders_";
    public static final String DELIMITER = ",";
    public LocalDate ordersDate;

    //outer map - our in-mem db
    private Map<LocalDate, Map<Integer, Order>> ordersByDateMap = new HashMap<>();

    public FlooringMasteryOrderDaoFileImpl(String orderFile) {
        this.ordersFile = orderFile;
    }

    public FlooringMasteryOrderDaoFileImpl() {
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        if (!ordersByDateMap.containsKey(orderDate)) {
            loadOrders();
        }
        return new ArrayList<>(ordersByDateMap.get(orderDate).values());
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException {
        Map<Integer, Order> ordersMap = new HashMap<>();
        this.ordersDate = orderDate;

        //if we can't load orders, it's a new date
        try {
            loadOrders();
            ordersMap.putAll(ordersByDateMap.get(orderDate));
        } catch (FlooringMasteryPersistenceException e) {
            //we'll create a new file if it doesn't exist
        }
        //if this is a new record, it shouldn't have an order number, but if it does (edit?), let's keep it
        if (order.getOrderNumber() == null) {
            order.setOrderNumber(generateOrderNumber());
        }
        //this is just for debugging
        System.out.println(order);
        //these two conditions are the same.  we need to put to a new inner map and then push to the outer map
        //If the big map has the date, we've already loaded the file, so we just need to add to big map
        //if big map does not have the date, we need to add it to the big map

        Order newOrder = ordersMap.put(order.getOrderNumber(), order);
        ordersByDateMap.put(orderDate, ordersMap);
        //writeOrderFile();
        return newOrder;
    }

    private Integer generateOrderNumber() throws FlooringMasteryPersistenceException {
        Scanner scanner = null;
        Integer nextOrderNumber = 0;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("OrderNumber.txt")));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        nextOrderNumber = scanner.nextInt() + 1;

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

        return nextOrderNumber;
    }

//    @Override
//    public Order getOrder(String orderID) throws FlooringMasteryPersistenceException {
//        loadInventory();
//        return orders.get(orderID);
//    }
    private void loadOrders() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        Map<Integer, Order> ordersMap = new HashMap<>();

        String ordersFileWithDate = ordersFile + ordersDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ordersFileWithDate)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    ":( Could not load order data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);

            Tax currentOrderTax = new Tax();
            currentOrderTax.setState(currentTokens[2]);
            currentOrderTax.setTaxRate(new BigDecimal(currentTokens[3]));

            currentOrder.setTaxRate(currentOrderTax);

            Product currentProduct = new Product();
            currentProduct.setProductType(currentTokens[4]);

            currentOrder.setArea(new BigDecimal(currentTokens[5]));

            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));

            currentOrder.setProduct(currentProduct);

            currentOrder.setOrderDate(ordersDate);

            //put order into inner map
            ordersMap.put(currentOrder.getOrderNumber(), currentOrder);
            ordersByDateMap.put(ordersDate, ordersMap);

        }
        // close scanner

        scanner.close();
    }

    private void writeOrderFile() throws FlooringMasteryPersistenceException {

        PrintWriter out;
        String ordersFileWithDate;

        List<LocalDate> filesList = new ArrayList<>(ordersByDateMap.keySet());
        for (LocalDate currentFileDate : filesList) {
            ordersFileWithDate = ordersFile + currentFileDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
            try {
                out = new PrintWriter(new FileWriter(ordersFileWithDate));
            } catch (IOException e) {
                throw new FlooringMasteryPersistenceException(
                        "Could not save order data.", e);
            }

            List<Order> orderList = new ArrayList<>(ordersByDateMap.get(currentFileDate).values());
            for (Order currentOrder : orderList) {
                // write the Order object to the file
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getTaxRate().getState() + DELIMITER
                        + currentOrder.getTaxRate().getTaxRate() + DELIMITER
                        + currentOrder.getProduct().getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getProduct().getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getProduct().getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTaxTotal() + DELIMITER
                        + currentOrder.getTotalCost());
                // force PrintWriter to write line to the file
                out.flush();
            }
            // Clean up
            out.close();
        }
    }

    @Override
    public Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        return ordersByDateMap.get(orderDate).remove(orderID);
    }

    @Override
    public Order getOrderByDate(Integer orderID, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        //catch npe return null to service call
        this.ordersDate = orderDate;
        if (ordersByDateMap.get(orderDate).containsKey(orderID)) {
            return ordersByDateMap.get(orderDate).get(orderID);
        } else {
            return null;
        }
    }

    @Override
    public void saveCurrentOrder() throws FlooringMasteryPersistenceException {
        writeOrderFile();
    }

}
