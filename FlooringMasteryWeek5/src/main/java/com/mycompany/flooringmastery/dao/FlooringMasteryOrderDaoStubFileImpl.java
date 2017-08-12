/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryOrderDaoStubFileImpl implements FlooringMasteryOrderDao {

    public LocalDate ordersDate;
    Integer nextOrderNumber = 400;

    //outer map - our in-mem db
    private Map<LocalDate, Map<Integer, Order>> ordersByDateMap = new HashMap<>();

    public FlooringMasteryOrderDaoStubFileImpl() {
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
        //System.out.println(order);
        //these two conditions are the same.  we need to put to a new inner map and then push to the outer map
        //If the big map has the date, we've already loaded the file, so we just need to add to big map
        //if big map does not have the date, we need to add it to the big map

        Order newOrder = ordersMap.put(order.getOrderNumber(), order);
        ordersByDateMap.put(orderDate, ordersMap);
        //writeOrderFile();
        return newOrder;
    }

    private Integer generateOrderNumber() throws FlooringMasteryPersistenceException {
        nextOrderNumber += 1;
        return nextOrderNumber;
    }

//    @Override
//    public Order getOrder(String orderID) throws FlooringMasteryPersistenceException {
//        loadInventory();
//        return orders.get(orderID);
//    }
    private void loadOrders() throws FlooringMasteryPersistenceException {
        Map<Integer, Order> ordersMap = new HashMap<>();

        LocalDate ld = LocalDate.parse("2010-01-01");

        //create test order 1
        Order testOrder1 = new Order();

        testOrder1.setOrderNumber(200);
        testOrder1.setCustomerName("Joe");
        testOrder1.setOrderDate(ld);

        Tax testTax = new Tax();
        testTax.setState("MI");
        testTax.setTaxRate(new BigDecimal("5.75"));
        testOrder1.setTaxRate(testTax);

        Product testProduct = new Product();
        testProduct.setProductType("Tile");
        testProduct.setCostPerSquareFoot(new BigDecimal("3.50"));
        testProduct.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        testOrder1.setProduct(testProduct);

        testOrder1.setArea(new BigDecimal("800"));
        //test order 2
        ld = LocalDate.parse("2010-01-10");

        //create test order
        Order testOrder2 = new Order();

        testOrder2.setOrderNumber(300);
        testOrder2.setCustomerName("Mary");
        testOrder2.setOrderDate(ld);

        Tax testTax2 = new Tax();
        testTax2.setState("OH");
        testTax2.setTaxRate(new BigDecimal("6.25"));
        testOrder2.setTaxRate(testTax);

        Product testProduct2 = new Product();
        testProduct2.setProductType("Carpet");
        testProduct2.setCostPerSquareFoot(new BigDecimal("2.25"));
        testProduct2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testOrder2.setProduct(testProduct);

        testOrder2.setArea(new BigDecimal("500"));

        //put orders into inner map
        ordersMap.put(testOrder1.getOrderNumber(), testOrder1);
        ordersByDateMap.put(testOrder1.getOrderDate(), ordersMap);
        
        ordersMap.put(testOrder2.getOrderNumber(), testOrder2);
        ordersByDateMap.put(testOrder2.getOrderDate(), ordersMap);

    }

private void writeOrderFile() throws FlooringMasteryPersistenceException {
        
    }

    @Override
        public Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        this.ordersDate = orderDate;
        if (ordersByDateMap.get(orderDate).containsKey(orderID)) {
            return ordersByDateMap.get(orderDate).remove(orderID);
        } else {
            return null;
        }
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
