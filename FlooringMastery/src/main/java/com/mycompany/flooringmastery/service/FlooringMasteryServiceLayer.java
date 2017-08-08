/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.mycompany.flooringmastery.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface FlooringMasteryServiceLayer {

    List<Item> getAllItems() throws FlooringMasteryPersistenceException;
    
    List<Item> getAllItemsFiltered() throws FlooringMasteryPersistenceException;

    Item getItem(String itemID) throws FlooringMasteryPersistenceException;

    Change purchaseItem(String itemID) throws InsufficientFundsException, FlooringMasteryPersistenceException, NoItemInventoryException;

    Item makeSaleReduceInventory(String itemID) throws FlooringMasteryPersistenceException, NoItemInventoryException;

    public void setCurrentMoney(BigDecimal moneyEntry);

    public BigDecimal getCurrentMoney();

    public Change giveChange(int remainingCash) throws FlooringMasteryPersistenceException;
    
    public Change cancelGiveChange() throws FlooringMasteryPersistenceException, InsufficientFundsException;

    //for future use
    //    void createItem (Item student) throws
    //            FlooringMasteryDuplicateIdException,
    //            InsufficientFundsException,
    //            FlooringMasteryPersistenceException;
}
