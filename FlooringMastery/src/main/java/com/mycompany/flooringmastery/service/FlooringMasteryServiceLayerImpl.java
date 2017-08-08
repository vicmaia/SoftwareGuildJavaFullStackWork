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
    public Order editOrder(LocalDate orderDate, Order order) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveCurrentWork() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Order retrieveOrder(LocalDate orderDate, Integer orderID) throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

//    private BigDecimal currentMoney = new BigDecimal("0");
//
//    //Pass through method
//    @Override
//    public List<Item> getAllItems() throws FlooringMasteryPersistenceException {
//        return dao.getAllItems();
//    }
//
////Pass through method
//    @Override
//    public List<Item> getAllItemsFiltered() throws FlooringMasteryPersistenceException {
//        return dao.getAllItemsFiltered();
//    }
//
//    //Pass through method
//    @Override
//    public Item getItem(String itemID) throws FlooringMasteryPersistenceException {
//        return dao.getItem(itemID);
//    }
//    //Pass through method
//
//    @Override
//    public Change purchaseItem(String itemID) throws InsufficientFundsException, FlooringMasteryPersistenceException, ItemNotAvailableException {
//        Item itemToPurchase = dao.getItem(itemID);
//        BigDecimal oneHundred = new BigDecimal("100");
//
//        if (validateItem(itemID)) {
//            if (currentMoney.compareTo(itemToPurchase.getItemPrice()) >= 0) {
//                //we have the item in stock and we have enough money 
//                //Remaining money to integer
//                int remainingCash = currentMoney.subtract(itemToPurchase.getItemPrice()).multiply(oneHundred).intValueExact();
//                //reduce inventory by 1
//                makeSaleReduceInventory(itemID);
//                //get change
//                return giveChange(remainingCash);
//            } else {
//                throw new InsufficientFundsException(
//                        "Not enough money!  Cannot purchase " + itemToPurchase.getItemName() + ".");
//            }
//        } else {
//            throw new ItemNotAvailableException(
//                    "Quantity = 0, cannot purchase " + itemToPurchase.getItemName() + ".");
//        }
//    }
//
//    @Override
//    public Item makeSaleReduceInventory(String itemID) throws FlooringMasteryPersistenceException, ItemNotAvailableException {
//        Item removedItem = dao.makeSaleReduceInventory(itemID);
//        return removedItem;
//    }
//
//    private Boolean validateItem(String itemID) throws FlooringMasteryPersistenceException, ItemNotAvailableException {
//        Item item = dao.getItem(itemID);
//        if (item != null) {
//            if (item.getItemQuantity() <= 0) {
//                throw new ItemNotAvailableException(
//                        "Quantity = 0, cannot purchase " + item.getItemName() + ".");
//            } else {
//                return true;
//            }
//        } else {
//            throw new ItemNotAvailableException(
//                    "You must choose an item from the inventory.");
//        }
//    }
//
//    @Override
//    public Change giveChange(int remainingCash) throws FlooringMasteryPersistenceException {
//        //Update cash inserted
//        this.currentMoney = new BigDecimal("0");
//        //Let's make and return change
//        return new Change(remainingCash);
//    }
//
//    @Override
//    public Change cancelGiveChange() throws FlooringMasteryPersistenceException, InsufficientFundsException {
//        if (currentMoney.compareTo(new BigDecimal("0")) > 0) {
//            BigDecimal oneHundred = new BigDecimal("100");
//            int remainingCash = currentMoney.multiply(oneHundred).intValueExact();
//            return giveChange(remainingCash);
//        } else {
//            throw new InsufficientFundsException("No money to return change on.");
//        }
//
//    }
//
//    @Override
//    public BigDecimal getCurrentMoney() throws NumberFormatException {
//        return currentMoney;
//    }
//
//    @Override
//    public void setCurrentMoney(BigDecimal currentMoney) throws NumberFormatException {
//        if (currentMoney.compareTo(new BigDecimal("0")) > 0) {
//            this.currentMoney = this.currentMoney.add(currentMoney, MathContext.UNLIMITED);
//        } else {
//            throw new NumberFormatException("Money added must be greater than 0.");
//        }
//    }
}
