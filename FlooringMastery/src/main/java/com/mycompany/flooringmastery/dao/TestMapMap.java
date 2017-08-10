/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
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
public class TestMapMap {

    public static void main(String[] args) {
        //outer map
        Map<LocalDate, Map<Integer, Order>> ordersByDateMap = new HashMap<LocalDate, Map<Integer, Order>>();
        //inner map
        Map<Integer, Order> ordersMap = new HashMap<>();

        Order newOrder1 = new Order();

        newOrder1.setOrderNumber(1);
        newOrder1.setCustomerName("Ken");
        newOrder1.setOrderDate(LocalDate.now());
        newOrder1.setArea(new BigDecimal("100"));

        Order newOrder2 = new Order();

        newOrder2.setOrderNumber(2);
        newOrder2.setCustomerName("Linden");
        newOrder2.setOrderDate(LocalDate.now().plusDays(10));
        newOrder2.setArea(new BigDecimal("1000"));

        
        ordersMap.put(newOrder1.getOrderNumber(), newOrder1);
        System.out.println(ordersMap.get(1));
        ordersByDateMap.put(newOrder1.getOrderDate(), ordersMap);
        System.out.println(ordersByDateMap.get(newOrder1.getOrderDate()));

        Map<Integer, Order> ordersMap1 = new HashMap<>();
        ordersMap1.put(newOrder2.getOrderNumber(), newOrder2);
        System.out.println(ordersMap1.get(2));
        ordersByDateMap.put(newOrder2.getOrderDate(), ordersMap1);
        System.out.println(ordersByDateMap.keySet());

        List<LocalDate> filesList = new ArrayList<>(ordersByDateMap.keySet());
        for (LocalDate currentFileDate : filesList) {
            System.out.println(currentFileDate);
            List<Order> records;
            //after debugging, everything goes in perfectly, but both records come out for records, want to filter on date
            records = new ArrayList<>(ordersByDateMap.get(currentFileDate).values());
            for (Order currentRecord : records) {
                System.out.println(currentRecord);
            }
        }
    }

}
