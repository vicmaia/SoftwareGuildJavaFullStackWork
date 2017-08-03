/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachinePersistenceException;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author n0252282
 */
public interface VendingMachineServiceLayer {

    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    List<Item> getAllItemsFiltered() throws VendingMachinePersistenceException;

    Item getItem(String itemID) throws VendingMachinePersistenceException;

    Change purchaseItem(String itemID) throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException;

    Item makeSaleReduceInventory(String itemID) throws VendingMachinePersistenceException, NoItemInventoryException;

    public void setCurrentMoney(BigDecimal moneyEntry);

    public BigDecimal getCurrentMoney();

    public Change giveChange(int remainingCash) throws VendingMachinePersistenceException;
    
    public Change cancelGiveChange() throws VendingMachinePersistenceException, InsufficientFundsException;

    //for future use
    //    void createItem (Item student) throws
    //            VendingMachineDuplicateIdException,
    //            InsufficientFundsException,
    //            VendingMachinePersistenceException;
}
