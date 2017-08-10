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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryOrderDaoTestFileImpl implements FlooringMasteryOrderDao {

    public String ordersFile = "Orders_";
    public static final String DELIMITER = ",";
    public LocalDate ordersDate;

    //outer map
    private Map<LocalDate, HashMap<Integer, Order>> ordersByDateMap = new HashMap<>();
    //inner map
    private Map<Integer, Order> ordersMap = new HashMap<>();

    public FlooringMasteryOrderDaoTestFileImpl(String inventoryFile) {
        this.ordersFile = inventoryFile;
    }

    public FlooringMasteryOrderDaoTestFileImpl() {
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        loadOrders();
        return new ArrayList<>(ordersMap.values());
        //return new ArrayList<>(orders.get(orderDate).values());
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        try {
            loadOrders();
        } catch (FlooringMasteryPersistenceException e) {
            ordersMap.clear();
        }
        Integer orderNumber = generateOrderNumber(orderDate);
        order.setOrderNumber(orderNumber);
        System.out.println(order);
        Order newOrder = ordersMap.put(order.getOrderNumber(), order);
        writeOrderFile();
        return newOrder;
    }

    private Integer generateOrderNumber(LocalDate orderDate) throws FlooringMasteryPersistenceException {
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
        String ordersFileWithDate;
        ordersFileWithDate = ordersFile + ordersDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ordersFileWithDate)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
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
        }
        // close scanner
        scanner.close();
    }

    private void writeOrderFile() throws FlooringMasteryPersistenceException {

        PrintWriter out;
        String ordersFileWithDate;
        ordersFileWithDate = ordersFile + ordersDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        try {
            out = new PrintWriter(new FileWriter(ordersFileWithDate));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save order data.", e);
        }

        List<Order> orderList = new ArrayList<>(ordersMap.values());
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

    @Override
    public Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        Order removedOrder = ordersMap.remove(orderID);
        writeOrderFile();
        return removedOrder;
    }

    @Override
    public Order getOrderByDate(Integer orderID, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        loadOrders();
        return ordersMap.get(orderID);
    }

    @Override
    public void saveCurrentOrder() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
