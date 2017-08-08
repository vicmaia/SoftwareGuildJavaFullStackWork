/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ListChangeListener.Change;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryServiceLayer {

    List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException;

    Order createOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException;

    Order editOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException;

    Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException;

    void saveCurrentWork() throws FlooringMasteryPersistenceException;

    List<Product> getAllProducts() throws FlooringMasteryPersistenceException;

    List<Tax> retrieveTaxList() throws FlooringMasteryPersistenceException;

    Order retrieveOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException;

    void validateOrder(Order order) throws FlooringMasteryPersistenceException;

}
