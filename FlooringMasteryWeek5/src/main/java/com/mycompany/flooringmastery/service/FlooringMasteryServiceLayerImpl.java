/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryOrderDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dao.FlooringMasteryProductDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mycompany.flooringmastery.dto.Order;
import com.mycompany.flooringmastery.dto.Product;
import com.mycompany.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author n0252282
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryOrderDao orderDao;
    FlooringMasteryProductDao productDao;
    FlooringMasteryTaxDao taxDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao orderDao, FlooringMasteryProductDao productDao, FlooringMasteryTaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        return orderDao.getAllOrdersByDate(orderDate);
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException {
        //set TaxRate on Order object (both state and rate)
        order.setTaxRate(retrieveTax(order.getTaxRate().getState()));
        //set Product info on Order object
        order.setProduct(getSingleProduct(order.getProduct().getProductType()));
        //set Date on Order object...just because -or- maybe instead of passing separate date object
        order.setOrderDate(orderDate);
        //create the order
        return orderDao.createOrder(orderDate, order);
    }

    @Override
    public Order editOrder(Order orderToEdit, Order editedOrder) throws FlooringMasteryPersistenceException {
        //delete the original order
        removeOrder(orderToEdit.getOrderDate(), orderToEdit.getOrderNumber());

        //Process and create the edited order
        //Fill in edited order details       
        //set TaxRate on Order object (both state and rate)
        editedOrder.setTaxRate(retrieveTax(editedOrder.getTaxRate().getState()));
        //set Product info on Order object
        editedOrder.setProduct(getSingleProduct(editedOrder.getProduct().getProductType()));

        //create the order
        return orderDao.createOrder(editedOrder.getOrderDate(), editedOrder);
    }

    @Override
    public Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        return orderDao.removeOrder(orderDate, orderID);
    }

    @Override
    public void saveCurrentWork() throws FlooringMasteryPersistenceException {
        orderDao.saveCurrentOrder();
    }

    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> retrieveTaxList() throws FlooringMasteryPersistenceException {
        return taxDao.getAllTaxes();
    }

    @Override
    public Order retrieveOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException, NoOrderFoundException {
        if (orderDao.getOrderByDate(orderID, orderDate) != null) {
            return orderDao.getOrderByDate(orderID, orderDate);
        } else {
            throw new NoOrderFoundException("That order is not available to edit.  Please choose again.");
        }
    }

    @Override
    public void validateOrder(Order order) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getSingleProduct(String productType) throws FlooringMasteryPersistenceException {
        return productDao.getProduct(productType);
    }

    @Override
    public Tax retrieveTax(String state) throws FlooringMasteryPersistenceException {
        return taxDao.getTax(state);
    }
}
