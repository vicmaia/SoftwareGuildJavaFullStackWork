/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.service.NoOrderInventoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryOrderDao {

    private Map<String, Order> orders = new HashMap<>();

    public FlooringMasteryDaoStubImpl() throws FlooringMasteryPersistenceException{
        // Add some inventory
        Order order1 = new Order("1");
        order1.setOrderName("Taco");
        order1.setOrderPrice(".50");
        order1.setOrderQuantity(3);

        addOrder(order1.getOrderID(), order1);

        Order order2 = new Order("2");
        order2.setOrderName("Nacho");
        order2.setOrderPrice("1.50");
        order2.setOrderQuantity(0);

        addOrder(order2.getOrderID(), order2);
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> getAllOrdersFiltered() throws FlooringMasteryPersistenceException {
        return orders
                .values()
                .stream()
                .filter(i -> i.getOrderQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrder(String orderID) throws FlooringMasteryPersistenceException {
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
        return removedOrder;
    }

    //Added for future use and for testing
    @Override
    public Order addOrder(String orderID, Order order) throws FlooringMasteryPersistenceException {
        Order newOrder = orders.put(orderID, order);
        return newOrder;
    }

    @Override
    public Order removeOrder(String orderID) throws FlooringMasteryPersistenceException {
        Order removedOrder = orders.remove(orderID);
        return removedOrder;
    }
}
