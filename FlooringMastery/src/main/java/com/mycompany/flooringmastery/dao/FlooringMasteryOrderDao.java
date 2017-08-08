/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.service.NoItemInventoryException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryOrderDao {

    List<Order> getAllOrders(LocalDate orderDate) throws FlooringMasteryPersistenceException;

    List<Order> getOrderByDate(Integer orderID, LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    Order createOrder(LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    Order removeOrder(Integer orderID, LocalDate orderDate) throws FlooringMasteryPersistenceException;
    
    void saveCurrentOrder () throws FlooringMasteryPersistenceException;

}
